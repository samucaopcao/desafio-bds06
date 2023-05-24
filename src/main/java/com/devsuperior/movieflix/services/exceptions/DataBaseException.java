package com.devsuperior.movieflix.services.exceptions;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String mensage) {
        super(mensage);
    }
}
