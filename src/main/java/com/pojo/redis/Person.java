package com.pojo.redis;

import java.io.Serializable;

/**
 * Created by sunitc on 4/17/18.
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private Address address;


    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}

class Address implements Serializable {
    private String city;
    private String stateCode;
    private String country;

    public Address(String city, String stateCode, String country) {
        this.city = city;
        this.stateCode = stateCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}