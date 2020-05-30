package com.play.util.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by sunitc on 30 May 2020.
 *
 * Check Test - CsvToBeanWithCsvMapperTest.java, for more details on how to use this class.
 */

@AllArgsConstructor
@RequiredArgsConstructor
public class CSVToJavaBeanUsingOpenCSV<T> {

    @NonNull
    private String fileName;
    @NonNull
    private char csvSeperator;
    @NonNull
    private Class<T> targetClass;

    private Map<String, String> columnNameMappings;

    public List<T> convert() {
        InputStream inputStream = CSVToJavaBeanUsingOpenCSV.class.getClassLoader().getResourceAsStream(this.fileName);

        Reader reader = new InputStreamReader(inputStream);

        CsvToBeanBuilder csvToBeanBuilder = new CsvToBeanBuilder(reader)
                .withType(targetClass)
                .withSeparator(csvSeperator)
                .withIgnoreLeadingWhiteSpace(true);

        if(columnNameMappings != null && !columnNameMappings.isEmpty()) {
            HeaderColumnNameTranslateMappingStrategy mappingStrategy = new HeaderColumnNameTranslateMappingStrategy();
            mappingStrategy.setColumnMapping(columnNameMappings);
            mappingStrategy.setType(targetClass);
            csvToBeanBuilder = csvToBeanBuilder.withMappingStrategy(mappingStrategy);
        }


        CsvToBean csvReader = csvToBeanBuilder.build();

        List<T> results = csvReader.parse();

        return results;
    }



}


