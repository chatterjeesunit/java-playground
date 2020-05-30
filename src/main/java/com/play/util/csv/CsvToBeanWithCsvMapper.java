package com.play.util.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by sunitc on 30 May 2020.
 *
 * Check Test - CsvToBeanWithCsvMapperTest.java, for more details on how to use this class.
 */

@AllArgsConstructor
public class CsvToBeanWithCsvMapper<T> {

    private String fileName;
    private char csvSeperator;
    private Class<T> targetClass;

    public List<T> convert() throws IOException {
        InputStream inputStream = CsvToBeanWithCsvMapper.class.getClassLoader().getResourceAsStream(this.fileName);
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(this.csvSeperator).withHeader();


        List<T> response = (List<T>) mapper
            .readerFor(this.targetClass)
            .with(schema)
            .readValues(inputStream)
            .readAll();

       return response;
    }
}


