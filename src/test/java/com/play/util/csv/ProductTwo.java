package com.play.util.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.play.util.csv.converters.ListConverter;
import com.play.util.csv.converters.ZoneDateTimeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductTwo {

    @CsvBindByName(column = "uniq_id")
    private String id;

    @CsvBindByName(column = "product_name")
    private String name;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "retail_price")
    private BigDecimal retailPrice;

    @CsvBindByName(column = "discounted_price")
    private BigDecimal discountedPrice;

    @CsvCustomBindByName(column = "images", converter = ListConverter.class)
    private List<String> images;

    @CsvCustomBindByName(column = "crawl_timestamp", converter = ZoneDateTimeConverter.class)
    private ZonedDateTime addedOn;
}


