package com.play.util.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.play.util.csv.converters.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by "Sunit Chatterjee" created on 31/05/20
 */
public class FlipkartProduct {

    @CsvBindByName(column = "uniq_id")
    private String id;

    @CsvBindByName(column = "product_name")
    private String name;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "product_url")
    private String productUrl;

    @CsvBindByName(column = "retail_price")
    private BigDecimal retailPrice;

    @CsvBindByName(column = "discounted_price")
    private BigDecimal discountedPrice;

    @CsvCustomBindByName(column = "images", converter = ListConverter.class)
    private List<String> images;

    @CsvCustomBindByName(column = "crawl_timestamp", converter = ZoneDateTimeConverter.class)
    private ZonedDateTime addedOn;


    @CsvCustomBindByName(column = "product_rating", converter = FlipkartRatingConverter.class)
    private Integer ratings;


    @CsvCustomBindByName(column = "product_category_tree", converter = FlipkartCategoryConverter.class)
    private LinkedList<String> categories;


    @CsvCustomBindByName(column = "product_specifications", converter = FlipkartProductSpecificationConverter.class)
    private Map<String, String> productSpecs;

}
