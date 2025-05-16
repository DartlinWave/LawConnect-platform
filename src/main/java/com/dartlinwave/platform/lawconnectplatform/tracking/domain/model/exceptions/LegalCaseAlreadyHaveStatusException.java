package com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.exceptions;

public class LegalCaseAlreadyHaveStatusException extends RuntimeException{

    public LegalCaseAlreadyHaveStatusException(String message) {
        super(message);
    }
}
