package com.thamer.forest_fire_simulation.models.exceptions;

import com.thamer.forest_fire_simulation.models.enums.ResponseMessage;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomizedException extends Exception {

    private String message = ResponseMessage.INTERNAL_ERROR_MESSAGE.toString();

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CustomizedException() {
    }

    public CustomizedException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}