package ru.alfastrah.embedi.util;

import java.time.LocalDateTime;

public class ErrorBody {

    private String error;
    private LocalDateTime timestamp = LocalDateTime.now();

    public static ErrorBody withMessage(String message) {
        return new ErrorBody(message);
    }

    public ErrorBody(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
