package com.play.util.csv;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CsvToBeanWithCsvMapperTest {


    @Test
    void shouldBeAbleToReadCSVAndConvertToListOfProducts() throws IOException {

        CsvToBeanWithCsvMapper<ProductOne> converter = new CsvToBeanWithCsvMapper<ProductOne>(
                "csv/product-sample-1.csv", ',', ProductOne.class);

        List<ProductOne> results = converter.convert();

        assertThat(results.size()).isEqualTo(3);

        assertThat(results)
                .extracting("id", "name", "retailPrice", "discountedPrice", "brand")
                .containsExactlyInAnyOrder(
                        tuple("1000", "Double Sofa Bed",	BigDecimal.valueOf(32157),	BigDecimal.valueOf(22646), "FabHomeDecor"),
                        tuple("1001", "AW Bellies",	BigDecimal.valueOf(999), BigDecimal.valueOf(499), "AW"),
                        tuple("1002", "Women's Cycling Shorts", BigDecimal.valueOf(699), BigDecimal.valueOf(267), "Alisha")
                );

    }


    @Test
    void shouldThrowExceptionWhenHeaderNamesDontMatch() throws IOException {

        CsvToBeanWithCsvMapper<ProductOne> converter = new CsvToBeanWithCsvMapper<ProductOne>(
                "csv/product-sample-2.csv", ',', ProductOne.class);

        Exception exception = Assertions.assertThrows(
                UnrecognizedPropertyException.class,
                () -> converter.convert());

        assertThat(exception.getMessage()).contains("Unrecognized field \"uniq_id\"");
    }
}