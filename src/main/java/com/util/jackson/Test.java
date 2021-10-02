package com.util.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class Test {

    @Getter
    @ToString
    @NoArgsConstructor
    static class Dummy {
        private String uuid;
        private LocalDate date;

        public Dummy(String uuid, LocalDate date) {
            this.uuid = uuid;
            this.date = date;
        }
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);



        Dummy dummyObj = new Dummy(UUID.randomUUID().toString(), LocalDate.now());

        String jsonStr = mapper.writeValueAsString(dummyObj);
        System.out.println("Serialized JSON = " + jsonStr);

        String json = "{\"uuid\":\"57dc8666-7d78-4a8b-b607-1cea0fad5104\",\"date\":\"2020-01-01\"}";

        Dummy deserializedObj = mapper.readValue(json, Dummy.class);
        System.out.println(deserializedObj);
    }
}
