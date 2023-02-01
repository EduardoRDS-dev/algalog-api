package com.algaworks.algalog.api.exeception_handler;

import com.algaworks.algalog.api.exceptions.BusinessException;
import com.algaworks.algalog.api.exceptions.ClientNotFoudException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(@NotNull MissingPathVariableException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return super.createResponseEntity(null, headers, HttpStatus.NOT_FOUND, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, HttpStatusCode status, @NotNull WebRequest request) {
        List<Field> fields = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> fields.add(new Field(((FieldError) objectError)
                .getField(), objectError.getDefaultMessage()))
        );

        return super.handleExceptionInternal(
                ex,
                new BodyForExceptionResponse(OffsetDateTime.now(), status.value(), "Um ou mais compos estão inválidos!", fields),
                headers, HttpStatus.BAD_REQUEST, request
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        BodyForExceptionResponse responseBody = new BodyForExceptionResponse(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), null
        );
        return super.createResponseEntity(responseBody, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ClientNotFoudException.class)
    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoudException ex, WebRequest request) {
        BodyForExceptionResponse responseBody = new BodyForExceptionResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), null
        );
        return super.createResponseEntity(responseBody, HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }

}
