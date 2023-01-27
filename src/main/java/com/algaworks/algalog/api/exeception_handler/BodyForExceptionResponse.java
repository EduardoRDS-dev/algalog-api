package com.algaworks.algalog.api.exeception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record
BodyForExceptionResponse(
        OffsetDateTime timeStamp,
        Integer httpStatus,
        String title,
        List<Field> fields
) {
}
