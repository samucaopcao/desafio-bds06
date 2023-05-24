package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;

public class ReviewDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    private String text;
    private Long movieId;
    private UserDTO user;

    public ReviewDTO() {}

    public ReviewDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.text = review.getText();
        this.movieId = review.getMovie().getId();
        this.user = new UserDTO(review.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserDTO getUser() {
        return user;
    }


}
