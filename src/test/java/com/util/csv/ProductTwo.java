package com.util.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.util.csv.converters.ListConverter;
import com.util.csv.converters.ZoneDateTimeConverter;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

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


