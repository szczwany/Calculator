package com.szczwany.calculator.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class Response
{
    public static ResponseEntity<?> statusNoContent()
    {
        return ResponseEntity.noContent().build();
    }

    public static ResponseEntity<?> statusOk()
    {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> statusOkWithBody(T t)
    {
        return ResponseEntity.ok().body(t);
    }

    public static ResponseEntity<Long> statusCreated(String path, Long id)
    {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(id);
    }
}
