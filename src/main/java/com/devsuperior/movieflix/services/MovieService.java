package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDTO> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        Movie movie = movieOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDTO(movie, new GenreDTO(movie.getGenre()));
    }

    @Transactional
    public List<ReviewDTO> findReviewsFromMovieId(Long idMovie) {
        Optional<Movie> movieOptional = movieRepository.findById(idMovie);
        Movie movie = movieOptional.orElseThrow(() -> new ResourceNotFoundException("Movie not found!"));
        return movie.getReviews().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public Page<MovieGenreDTO> findMoviesByGenre(Long genreId, Pageable pageable) {
        Long genreIdFinal = genreId == 0 ? null : genreId;
        Page<Movie> movie = movieRepository.findMovieByGenre(genreIdFinal, pageable);
        return movie.map(x -> new MovieGenreDTO(x));
    }
}
