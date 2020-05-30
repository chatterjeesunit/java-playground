package com.play.util.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunitc on 6/8/17.
 */
public class MapToObject {



    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Long.valueOf(323232l));
        map.put("firstName", "Sunit" );
        map.put("lastName", "Chatterjee" );
        map.put("joiningDate", new Date() );
        map.put("lookingOut", true );

        System.out.println(map);

        ObjectMapper mapper = new ObjectMapper();
        MyObject obj = mapper.convertValue(map, MyObject.class);

        System.out.println(obj);
    }
}

