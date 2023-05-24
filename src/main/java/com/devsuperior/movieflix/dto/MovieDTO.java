package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTO {

    private Long id;
    private String title;
    private String subTitle;
    private Integer year;
    private String imgUrl;
    private String synopsis;
    private GenreDTO genreDTO;

    public MovieDTO() { }

    public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
    }

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.subTitle = movie.getSubTitle();
        this.year = movie.getYear();
        this.imgUrl = movie.getImgUrl();
        this.synopsis = movie.getSynopsis();
    }

    public MovieDTO(Movie movie, GenreDTO genreDTO) {
        this(movie);
        this.genreDTO = new GenreDTO(genreDTO.getId(), genreDTO.getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public GenreDTO getGenre() {
        return genreDTO;
    }

    public void setGenre(GenreDTO genreDTO) {
        this.genreDTO = genreDTO;
    }
}
