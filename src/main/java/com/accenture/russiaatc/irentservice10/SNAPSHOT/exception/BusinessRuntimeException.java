package com.accenture.russiaatc.irentservice10.SNAPSHOT.exception;

import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException{
    private ErrorCodeEnum errorCodeEnum;

    public BusinessRuntimeException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.getMessage(), cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public BusinessRuntimeException(ErrorCodeEnum errorCodeEnum, Throwable cause, Object... args) {
        super(errorCodeEnum.getMessage(args), cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public BusinessRuntimeException(ErrorCodeEnum errorCodeEnum, Object... args) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCode(){
        return errorCodeEnum;
    }

}
