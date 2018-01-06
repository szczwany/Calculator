package com.szczwany.calculator.Utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class Response
{
    public static ResponseEntity<?> noContent()
    {
        return ResponseEntity.noContent().build();
    }

    public static ResponseEntity<?> ok()
    {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> okBody(T t)
    {
        return ResponseEntity.ok().body(t);
    }

    public static ResponseEntity<Long> created(String path, Long id)
    {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(id);
    }
}
