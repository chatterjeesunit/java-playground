package com.pojo.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 2/20/18.
 */
public class FlatMapTest2 {

    public static void main(String[] args) {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();

        map1.put("id0001", "ABC" );
        map1.put("name0001", "abc" );

        map2.put("id0002", "XYZ" );
        map2.put("name0002", "xyz" );


        map3.put("id0003", "KLM" );
        map3.put("name0003", "klm" );


        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);

        System.out.println(list);


        Object result =
                list.stream().flatMap(map ->
                        map.entrySet().stream().filter(entry -> entry.getKey().startsWith("id"))
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(result
        );
    }
}
