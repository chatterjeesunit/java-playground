package com.util.csv.converters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by "Sunit Chatterjee" created on 31/05/20
 */
public class FlipkartProductSpecificationConverter extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonValue = value.replaceAll("=>", ":");

        ProductSpecification productSpecification = null;
        try {
            productSpecification = objectMapper.readValue(jsonValue, ProductSpecification.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Map<String, String> productSpecsMap = productSpecification.getProductSpecification()
                .stream()
                .filter(kv -> kv.getKey() != null)
                .collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue));

        return productSpecsMap;
    }
}



@Data
class ProductSpecification {
    @JsonProperty("product_specification")
    private List<KeyValuePair> productSpecification;
}

@Data
class KeyValuePair {
    private String key;
    private String value;
}