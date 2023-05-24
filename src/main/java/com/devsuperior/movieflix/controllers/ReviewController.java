package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> findAllPaged(Pageable pageable) {
        Page<ReviewDTO> reviewDTOS = reviewService.findAllPaged(pageable);
        return ResponseEntity.ok().body(reviewDTOS);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> insertReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO reviewInserted = reviewService.insert(reviewDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reviewDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(reviewInserted);

    }
}
