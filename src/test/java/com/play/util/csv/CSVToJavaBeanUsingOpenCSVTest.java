package com.play.util.csv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CSVToJavaBeanUsingOpenCSVTest {

    @Test
    void shouldBeAbleToReadCSVWhenHeaderNamesMatch() {

        CSVToJavaBeanUsingOpenCSV<ProductOne> converter =
                new CSVToJavaBeanUsingOpenCSV<>(
                "csv/product-sample-1.csv", ',', ProductOne.class);

        List<ProductOne> results = converter.convert();

        assertThat(results.size()).isEqualTo(3);

        assertThat(results)
                .extracting("id", "name", "retailPrice", "discountedPrice", "brand")
                .containsExactlyInAnyOrder(
                        tuple("1000", "FabHomeDecor Fabric Double Sofa Bed",	BigDecimal.valueOf(32157),	BigDecimal.valueOf(22646), "FabHomeDecor"),
                        tuple("1001", "AW Bellies",	BigDecimal.valueOf(999), BigDecimal.valueOf(499), "AW"),
                        tuple("1002", "Alisha Solid Women's Cycling Shorts", BigDecimal.valueOf(699), BigDecimal.valueOf(267), "Alisha")
                );
    }


    @Test
    void shouldReadNullValuesWhenHeaderNamesDoNotMatch() {

        CSVToJavaBeanUsingOpenCSV<ProductOne> converter =
                new CSVToJavaBeanUsingOpenCSV<>(
                        "csv/product-sample-2.csv", ',', ProductOne.class);

        List<ProductOne> results = converter.convert();

        assertThat(results.size()).isEqualTo(3);

        assertThat(results)
                .extracting("id", "name", "retailPrice", "discountedPrice", "brand")
                .containsExactlyInAnyOrder(
                        tuple(null, null, null, null, "FabHomeDecor"),
                        tuple(null, null, null, null, "AW"),
                        tuple(null, null, null, null, "Alisha")
                );
    }


    @Test
    void shouldBeAbleToReadCSVUsingHeaderNamesMappingWhenHeaderNamesDoNotMatch() {


        Map<String, String> columnMappings = Map.of(
                "uniq_id", "id",
                "product_name", "name",
                "retail_price", "retailPrice",
                "discounted_price", "discountedPrice",
                "brand", "brand"
        );

        CSVToJavaBeanUsingOpenCSV<ProductOne> converter =
                new CSVToJavaBeanUsingOpenCSV<>(
                        "csv/product-sample-2.csv", ',', ProductOne.class, columnMappings);

        List<ProductOne> results = converter.convert();

        assertThat(results.size()).isEqualTo(3);

        assertThat(results)
                .extracting("id", "name", "retailPrice", "discountedPrice", "brand")
                .containsExactlyInAnyOrder(
                        tuple("1000", "FabHomeDecor Fabric Double Sofa Bed",	BigDecimal.valueOf(32157),	BigDecimal.valueOf(22646), "FabHomeDecor"),
                        tuple("1001", "AW Bellies",	BigDecimal.valueOf(999), BigDecimal.valueOf(499), "AW"),
                        tuple("1002", "Alisha Solid Women's Cycling Shorts", BigDecimal.valueOf(699), BigDecimal.valueOf(267), "Alisha")
                );
    }



    @Test
    void shouldThrowErrorIfNoConverterSpecifiedForNonPrimitiveTypes() {

        Map<String, String> columnMappings = Map.of(
                "uniq_id", "id",
                "product_name", "name",
                "retail_price", "retailPrice",
                "discounted_price", "discountedPrice",
                "brand", "brand",
                "crawl_timestamp", "added_on",
                "images", "images"
        );

        CSVToJavaBeanUsingOpenCSV<ProductOne> converter =
                new CSVToJavaBeanUsingOpenCSV<>(
                        "csv/product-sample-3.csv", ',', ProductOne.class, columnMappings);


        Assertions.assertThrows(RuntimeException.class, () -> converter.convert());
    }

    @Test
    void shouldBeAbleToConvertCSVToComplexBeanUsingConverterAnnotations() {

        CSVToJavaBeanUsingOpenCSV<ProductTwo> converter =
                new CSVToJavaBeanUsingOpenCSV<>(
                        "csv/product-sample-3.csv", ',', ProductTwo.class);


        List<ProductTwo> results = converter.convert();

        assertThat(results.size()).isEqualTo(3);

        ZoneId zoneId = ZoneId.of(ZoneOffset.of("+0000").getId());

        assertThat(results)
                .extracting("id", "name", "retailPrice", "discountedPrice", "brand", "addedOn", "images")
                .containsExactlyInAnyOrder(
                        tuple("1000", "FabHomeDecor Fabric Double Sofa Bed",
                            BigDecimal.valueOf(32157),	BigDecimal.valueOf(22646),
                            "FabHomeDecor",
                            ZonedDateTime.of(LocalDateTime.of(2016, 03, 25, 21, 59, 23), zoneId),
                            List.of("http://image1.jpg", "http://image2.jpg", "http://image3.jpg")),

                        tuple("1001", "AW Bellies",	BigDecimal.valueOf(999), BigDecimal.valueOf(499), "AW",
                            ZonedDateTime.of(LocalDateTime.of(2016, 03, 26, 22, 59, 23), zoneId),
                            List.of("http://image4.jpg")),

                        tuple("1002", "Alisha Solid Women's Cycling Shorts", BigDecimal.valueOf(699), BigDecimal.valueOf(267), "Alisha",
                            ZonedDateTime.of(LocalDateTime.of(2016, 03, 27, 23, 59, 23), zoneId),
                            List.of())
                );
    }


}