package com.util.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

public class TypeReferenceTest {
    public static void main(String[] args) throws IOException {

        PersonOne person = new PersonOne("ABC", 20L);
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(person);
        System.out.println(json);


        PersonOne p1 = mapper.readValue(json, PersonOne.class);

        TypeReference<PersonOne> typeReference = new TypeReference<PersonOne>() {};

        PersonOne p2 = mapper.readValue(json, typeReference);

        System.out.println(p1);
        System.out.println(p2);

    }



}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PersonOne {
    String name;
    Long age;
}