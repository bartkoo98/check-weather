package com.github.weatherforecast.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
class ExceptionDto {

    private String message;
    private Integer code;
    private LocalDateTime timestamp;

    public ExceptionDto(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }
}
