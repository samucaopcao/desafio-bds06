package com.devsuperior.movieflix.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensage) {
        super(mensage);
    }
}
