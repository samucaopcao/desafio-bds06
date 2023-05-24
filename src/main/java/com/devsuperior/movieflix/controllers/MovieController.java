package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.findById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @GetMapping("/{idMovie}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsFromMovieId(@PathVariable Long idMovie) {
        List<ReviewDTO> reviews = movieService.findReviewsFromMovieId(idMovie);
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping
    public ResponseEntity<Page<MovieGenreDTO>> findMoviesByGenre(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieGenreDTO> movies = movieService.findMoviesByGenre(genreId, pageable);
        return ResponseEntity.ok().body(movies);
    }
}
