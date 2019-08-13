package com.pojo.cogni;

/**
 * Created by sunitc on 4/3/18.
 */
class Human {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public Human clone() {
        return new Human(this.name);
    }

}
