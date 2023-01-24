package com.algaworks.algalog.api.exeception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record
BodyForExceptionResponse(
        LocalDateTime timeStamp,
        Integer httpStatus,
        String title,
        List<Field> fields
) {
}
