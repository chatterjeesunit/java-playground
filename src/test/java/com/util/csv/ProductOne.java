package com.util.csv;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class ProductOne {

    private String id;
    private String name;
    private String brand;
    private BigDecimal retailPrice;
    private BigDecimal discountedPrice;
    private List<String> images;
    private ZonedDateTime added_on;
}


