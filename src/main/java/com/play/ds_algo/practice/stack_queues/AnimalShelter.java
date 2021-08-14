package com.play.ds_algo.practice.stack_queues;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Optional;

/**
 * An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis.
 * People must adopt either the"oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
 * They cannot select which specific animal they would like.
 *
 * Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat.
 * You may use the built-in Linked list data structure.
 */
public class AnimalShelter<T extends Animal> {
    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int counter = 0;


    public int totalAnimals() {
        return dogs.size() + cats.size();
    }

    public int totalDogs() {
        return dogs.size();
    }

    public int totalCats() {
        return cats.size();
    }

    public void enqueue(Animal animal) {
        counter++;
        animal.setOrder(counter);
        if(animal instanceof Dog) {
            dogs.addLast((Dog) animal);
        } else {
            cats.addLast((Cat) animal);
        }
    }

    public Dog dequeueDog() {
        if(dogs.isEmpty()) {
            throw new RuntimeException("No Dogs Available");
        }
        return dogs.removeFirst();
    }

    public Cat dequeueCat() {
        if(cats.isEmpty()) {
            throw new RuntimeException("No Cats Available");
        }
        return cats.removeFirst();
    }

    public Animal dequeueAny() {
        if(cats.isEmpty() && dogs.isEmpty()) {
            throw new RuntimeException("No Dogs or Cats Available");
        }

        int catOrder = Optional.ofNullable(cats.peek()).map(Animal::getOrder).orElse(Integer.MAX_VALUE);
        int dogOrder = Optional.ofNullable(dogs.peek()).map(Animal::getOrder).orElse(Integer.MAX_VALUE);
        if(catOrder < dogOrder) {
            return dequeueCat();
        }

        return dequeueDog();
    }


}

@Getter
@RequiredArgsConstructor
abstract class Animal {
    @Setter
    protected int order;
    @NonNull
    protected String name;
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Dog{" + name + '}';
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Cat{" + name + '}';
    }
}