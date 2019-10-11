package com.pojo.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

public class LocalDateSerialization {

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person("Sunit", LocalDate.of(2019,01,31));

        serializeOne(person);

        serializeTwo(person);

    }

    private static void serializeOne(Person person) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String value = objectMapper.writeValueAsString(person);

        System.out.println(value);
    }

    private static void serializeTwo(Person person) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(person);

        System.out.println(value);
    }
}


@Data
@AllArgsConstructor
class Person {
    String name;
    LocalDate date;
}