package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthService authService;

    public Page<ReviewDTO> findAllPaged(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviews.map(x -> new ReviewDTO(x));
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO reviewDTO) {
        Review review = new Review();
        copyDtoToEntity(review, reviewDTO);
        review = reviewRepository.save(review);
        return new ReviewDTO(review);
    }

    private void copyDtoToEntity(Review entity, ReviewDTO dto) {
        entity.setText(dto.getText());
        entity.setMovie(new Movie(dto.getMovieId(), null, null, null, null, null));
        entity.setUser(authService.authenticated());
    }
}
