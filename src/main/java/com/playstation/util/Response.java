package com.playstation.util;

import com.playstation.exception.SourceNotFoundException;

public class Response<T> {
    private T payload;

    private RuntimeException error;

    public Response() { }

    public Response(T payload, RuntimeException error) {
        this.payload = payload;
        this.error = error;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public RuntimeException getError() {
        return error;
    }

    public void setError(RuntimeException error) {
        this.error = error;
    }

    public void throwErrorIfUnsuccessful() {
        if (error != null)
            throw error;
    }

    public void setErrorToSourceNotFound() {
        error = new SourceNotFoundException();
    }
}
