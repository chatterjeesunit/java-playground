package com.play.util.csv.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

public @NoArgsConstructor
class ListConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(value, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
