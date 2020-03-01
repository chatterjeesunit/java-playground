package com.pojo.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestTreeMap {

    public static void main(String[] args) {

        Map<TestObject, String> map = Map.of(
                new TestObject(112L, LocalDate.now()), "Dummy01",
                new TestObject(111L, LocalDate.now()), "Dummy02"
        );

        Comparator<TestObject> comparator1 = new Comparator<TestObject>() {
            @Override
            public int compare(TestObject o1, TestObject o2) {
                int cmp = o1.getDate().compareTo(o2.getDate());

                if(cmp == 0) return o1.getId().compareTo(o2.getId());

                return cmp;
            }
        };

        Comparator<TestObject> comparator2 = Comparator.comparing(TestObject::getDate);

        TreeMap<TestObject, String> tree1 = new TreeMap<>(comparator1);
        tree1.putAll(map);

        System.out.println(tree1);

        TreeMap<TestObject, String> tree2 = new TreeMap<>(comparator2);
        tree2.putAll(map);

        System.out.println(tree2);

    }








}


@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class TestObject {
    Long id;
    LocalDate date;
}