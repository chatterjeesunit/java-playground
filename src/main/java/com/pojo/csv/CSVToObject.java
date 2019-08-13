package com.pojo.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sunitc on 6/5/17.
 */
public class CSVToObject {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = CSVToObject.class.getClassLoader().getResourceAsStream("topic_initial_data.csv");

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(',').withHeader();

        mapper
            .readerFor(TopicImportData.class)
            .with(schema)
            .readValues(inputStream)
            .readAll()
            .forEach(o -> {
                System.out.println(o);
            });
    }
}
