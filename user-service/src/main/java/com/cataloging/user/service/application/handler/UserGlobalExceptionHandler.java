package com.cataloging.user.service.application.handler;


import com.cataloging.application.handler.ErrorDTO;
import com.cataloging.application.handler.GlobalExceptionHandler;
import com.cataloging.user.service.domain.core.exception.UserDomainException;
import com.cataloging.user.service.domain.core.exception.UserNotFoundException;
import com.cataloging.exception.ApplicationPrivilegesException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {UserDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(UserDomainException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ApplicationPrivilegesException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDTO handleException(ApplicationPrivilegesException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder().code(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(exception.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage()).build();
    }
}
