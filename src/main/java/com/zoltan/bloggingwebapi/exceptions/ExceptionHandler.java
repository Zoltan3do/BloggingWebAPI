package com.zoltan.bloggingwebapi.exceptions;

import com.zoltan.bloggingwebapi.payloads.ErrorRespDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRespDTO handleNotFound(NotFoundException nf) {
        return new ErrorRespDTO(nf.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDTO badRequestHandler(BadRequestException br) {
        return new ErrorRespDTO(br.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorRespDTO unauthorizedHandler(UnauthorizedException u) {
        return new ErrorRespDTO(u.getMessage(), LocalDateTime.now());
    }

}
