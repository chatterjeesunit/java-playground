package com.play.corejava.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 6/20/18.
 */
public class MapGroupBy {


    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(new Person[]{
                new Person("John", "SFO"),
                new Person("Nilesh", "Pune"),
                new Person("Pranali", "Mumbai"),
                new Person("Sunit", "SFO"),
                new Person("Reshma", "Pune"),
                new Person("Mayur", "Pune"),
                new Person("Shilpa", "Mumbai"),
                new Person("Ida", "SFO"),
                new Person("Rohan", "Mumbai"),
                new Person("Anushri", "SFO"),
                new Person("Avanti", "Pune"),
                new Person("Mukul", "Mumbai"),
                new Person("Prerarna", "Pune"),
                new Person("Pushpak", "Pune"),
        });

        System.out.println(persons.stream().map(p -> p.toString()).collect(Collectors.joining("\n")));

        Collections.sort(persons);

        System.out.println("************************************************");

        System.out.println(persons.stream().map(p -> p.toString()).collect(Collectors.joining("\n")));

        System.out.println("************************************************");

        Map result = persons.stream().collect(Collectors.groupingBy(
                Person::getCity, LinkedHashMap::new, Collectors.toList()));



        System.out.println(result);
    }

    static class Person implements Comparable<Person> {

        private String firstName;
        private String city;

        public Person(String firstName, String city) {
            this.firstName = firstName;
            this.city = city;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public int compareTo(Person o) {
            int cityCompare = this.city.compareTo(o.city);
            if (cityCompare == 0) return this.firstName.compareTo(o.firstName);
            return cityCompare;
        }

        @Override
        public String toString() {
            return "City=" + city + " , Name = " + firstName;
        }
    }

}



