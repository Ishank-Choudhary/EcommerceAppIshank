package com.ecommerce.sb_ecom_project.exception;

public class APIException extends RuntimeException{
    private static final long seriealVersionUID=1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
