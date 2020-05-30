package com.play.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sunitc on 6/15/17.
 */
public class PersonComparatorTest {

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Abraham", 30, "Sales"));
        personList.add(new Person("Donald", 35, "Marketing"));
        personList.add(new Person("John", 30, "Sales"));
        personList.add(new Person("Aanie", 50, "Sales"));
        personList.add(new Person("David", 45, "Marketing"));

        Collections.sort(personList);

        personList.forEach(p -> System.out.println(p));
    }
}


class Person implements Comparable<Person> {
    private String name;
    private String department;
    private int age;

    public Person(String name, int age, String department) {
        this.name = name;
        this.department = department;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        if (o == null) return 1;

        int dCompare = this.department.compareTo(o.department);
        if (dCompare == 0) {
            int aCompare = Integer.compare(this.age, o.age);
            if (aCompare == 0)
                return this.name.compareTo(o.name);
            else
                return aCompare;
        } else return dCompare;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                '}';
    }
}