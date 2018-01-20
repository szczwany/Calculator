package com.szczwany.calculator.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectConverter
{
    private static ObjectMapper mapper = new ObjectMapper();

    public static String convertToJson(Object obj) throws JsonProcessingException
    {
        return mapper.writeValueAsString(obj);
    }
}
