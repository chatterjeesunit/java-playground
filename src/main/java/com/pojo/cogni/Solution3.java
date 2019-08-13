package com.pojo.cogni;

/**
 * Created by sunitc on 4/3/18.
 */
public class Solution3 {

    public static void main(String[] args) {
        FoodFactory myFoods = new FoodFactory();
        Food food1 = myFoods.getFood("FastFood");
        Food food2 = myFoods.getFood("Fruit");
        System.out.println("My name is: " + food1.getClass().getName());
        System.out.println("My name is: " + food2.getClass().getName());
        System.out.println("Our superclass is: "
                + food1.getClass().getSuperclass().getName());
        food1.serveFood();
        food2.serveFood();
    }
}

class Food {
    public void serveFood() {
        System.out.println("I'm serving " + getClass().getName());
    }
}

class FastFood extends Food{}
class Fruit extends Food{}

class FoodFactory extends Food {
    public Food getFood(final String foodName) {
        switch (foodName) {
            case "FastFood" : return new FastFood();
            case "Fruit": return new Fruit();
            default: throw new IllegalArgumentException("Unsupported Fruit type");
        }
    }
}