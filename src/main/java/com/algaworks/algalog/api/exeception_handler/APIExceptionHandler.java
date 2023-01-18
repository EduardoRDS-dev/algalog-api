package com.algaworks.algalog.api.exeception_handler;

import com.algaworks.algalog.api.exceptions.BusinessException;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.createResponseEntity(null, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Field> fields = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> fields.add(new Field(((FieldError) objectError).getField(), objectError.getDefaultMessage())));
        return super.handleExceptionInternal(ex, new BodyForExceptionResponse(LocalDateTime.now(), status.value(), "Um ou mais compos estão inválidos!", fields), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(RuntimeException ex, WebRequest request) {
        BodyForExceptionResponse responseBody = new BodyForExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return super.createResponseEntity(responseBody, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }
}
