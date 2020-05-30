package com.play.util.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sunitc on 3/5/18.
 */
public class ArrayString {
    public static void main(String[] args) throws IOException {

        String one = "<div contenteditable=\"true\" id=\"structure_html40601\">";
        String two = "<td style=\"width: 50.0000%;\">";
        
        List data = Arrays.asList(one, two);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);

        System.out.println(json);
        List<String> datalist = mapper.readValue(json, new TypeReference<List<String>>(){});

        System.out.println(datalist);
    }
}
