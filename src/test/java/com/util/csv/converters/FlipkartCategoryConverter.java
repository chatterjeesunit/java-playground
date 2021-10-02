package com.util.csv.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by "Sunit Chatterjee" created on 31/05/20
 */
public class FlipkartCategoryConverter extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<String> categories = null;
        try {
            categories = objectMapper.readValue(value, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedList<String> categoryLinkedList = categories.stream().findFirst().map(str ->
                Arrays.stream(str.split(" >> ")).collect(Collectors.toCollection(LinkedList::new))
        ).orElse(null);

        return categoryLinkedList;
    }
}
