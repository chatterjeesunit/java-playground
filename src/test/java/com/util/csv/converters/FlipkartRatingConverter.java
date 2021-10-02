package com.util.csv.converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FlipkartRatingConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        Integer ratings = null;
        try {
            int i = Integer.parseInt(value);
            ratings = Integer.valueOf(i);
        } catch (NumberFormatException ex ) {
            //Do Nothing
        }

        return ratings;
    }
}
