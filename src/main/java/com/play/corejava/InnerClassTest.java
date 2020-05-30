package com.play.corejava;

import java.util.*;

public class InnerClassTest {
    public static void main(String[] args) {
        class Inner {
            final Integer i6 =6;
            Integer i7 = 7;

            Inner() {
                System.out.println(i6 + i7);
            }
        }
    }

}