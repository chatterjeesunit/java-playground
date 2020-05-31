package com.play.util.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by sunitc on 30 May 2020.
 *
 * Check Test - CsvToBeanWithCsvMapperTest.java, for more details on how to use this class and creating converters, etc
 *
 * To understand how this works check out this blog - https://developerpod.com/2020/05/31/read-csv-file-to-java-bean-using-open-csv/
 */

public class CSVToJavaBeanUsingOpenCSV<T> {

    private String fileName;
    private char csvSeperator;
    private Class<T> targetClass;

    public CSVToJavaBeanUsingOpenCSV(String fileName, char csvSeperator, Class<T> targetClass) {
        this.fileName = fileName;
        this.csvSeperator = csvSeperator;
        this.targetClass = targetClass;
    }

    public List<T> convert() {
        Reader reader = getReader();
        CsvToBean<T> csvReader = new CsvToBeanBuilder(reader)
                .withType(targetClass)
                .withSeparator(csvSeperator)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();

        List<T> results = csvReader.parse();

        Iterator<T> iterator = csvReader.iterator();

        Stream<T> stream = csvReader.stream();


        return results;
    }

    public List<T> convert(Map<String, String> columnNameMappings) {
        Reader reader = getReader();

        HeaderColumnNameTranslateMappingStrategy mappingStrategy = new HeaderColumnNameTranslateMappingStrategy();
        mappingStrategy.setColumnMapping(columnNameMappings);
        mappingStrategy.setType(targetClass);

        CsvToBean<T> csvReader = new CsvToBeanBuilder(reader)
                .withType(targetClass)
                .withSeparator(csvSeperator)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withMappingStrategy(mappingStrategy)
                .build();

        List<T> results = csvReader.parse();

        return results;
    }

    private Reader getReader() {
        InputStream inputStream = CSVToJavaBeanUsingOpenCSV.class.getClassLoader().getResourceAsStream(this.fileName);
        return new InputStreamReader(inputStream);
    }


}


