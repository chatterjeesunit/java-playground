package com.corejava.enumtest.test;

import java.lang.reflect.Method;

/**
 * Created by sunitc on 6/26/17.
 */
public class EnumReflectionTest {

    public static void main(String[] args) {

        try {
            Class classz = Class.forName("com.pojo.reflection.enumtest.pkg1.StatusEnum");
            Method valueOf = classz.getMethod("getEnum", Integer.class);
            Object value = valueOf.invoke(null, 3);
            System.out.println(value);
        } catch ( ReflectiveOperationException e) {
           e.printStackTrace();
        }
    }
}
