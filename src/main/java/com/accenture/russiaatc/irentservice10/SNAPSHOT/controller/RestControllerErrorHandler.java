package com.accenture.russiaatc.irentservice10.SNAPSHOT.controller;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerErrorHandler {

    @ExceptionHandler(BusinessRuntimeException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessRuntimeException ex){
        log.error(ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto(ex.getErrorCodeEnum().getCode(), ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getErrorCode().getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorDto handleAllException(Throwable ex){
        log.error(ex.getMessage(), ex);
        return new ErrorDto(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }


}
