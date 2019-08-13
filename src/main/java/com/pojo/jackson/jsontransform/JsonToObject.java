package com.pojo.jackson.jsontransform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.jackson.jsontransform.model.SearchCriteria;

import java.io.IOException;

/**
 * Created by sunitc on 7/28/17.
 */
public class JsonToObject {

    public static void main(String[] args) throws IOException {

        String input = "{\"conditionGroups\":[{\"conditions\":[{\"fieldName\":\"status\",\"operator\":\"IN\",\"value\":null,\"multiValue\":[\"PUBLISHED\"]}],\"joinOperator\":\"OR\"}],\"joinOperator\":\"AND\",\"sortOrder\":{\"fieldName\":\"createDate\",\"sortBy\":\"DESC\"},\"pageNumber\":1,\"pageSize\":10}";
//        InputStream stream = JsonToObject.class.getClassLoader().getResourceAsStream("condition.json");
        ObjectMapper mapper = new ObjectMapper();

        SearchCriteria criteria = mapper.readValue(input, SearchCriteria.class);
        System.out.println(criteria);


    }
}
