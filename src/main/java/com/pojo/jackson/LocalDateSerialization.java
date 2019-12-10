package com.pojo.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateSerialization {

    public static void main(String[] args) throws IOException {

        Person person = new Person(
                "Sunit",
                LocalDate.of(2019,01,31),
                LocalDateTime.of(2019, 01, 31, 13, 35, 50));

        ObjectMapper objectMapperOne = new ObjectMapper();
        objectMapperOne.registerModule(new JavaTimeModule());
        objectMapperOne.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        serializeAndDeserialize(person, objectMapperOne);

    }

    private static void serializeAndDeserialize(Person person, ObjectMapper objectMapper) throws IOException {

        String value = objectMapper.writeValueAsString(person);

        System.out.println(value);

        Person deserializedzPerson = objectMapper.readValue(value, Person.class);

        System.out.println(deserializedzPerson);
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    String name;
    LocalDate date;
    LocalDateTime dateTime;
}