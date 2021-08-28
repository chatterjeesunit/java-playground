package com.play.algorithms_practice.stack_queues;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterTest {

    @Test
    void shouldbeAbleToEnqueueAnyAnimal() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        shelter.enqueue(new Dog("D1"));
        shelter.enqueue(new Dog("D2"));
        shelter.enqueue(new Cat("C1"));
        shelter.enqueue(new Cat("C2"));
        shelter.enqueue(new Dog("D3"));
        shelter.enqueue(new Cat("C3"));
        shelter.enqueue(new Cat("C4"));

        assertThat(shelter.totalAnimals()).isEqualTo(7);
        assertThat(shelter.totalCats()).isEqualTo(4);
        assertThat(shelter.totalDogs()).isEqualTo(3);

    }

    @Test
    void shouldbeAbleToGetOldestAnimal() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        shelter.enqueue(new Dog("D1"));
        shelter.enqueue(new Dog("D2"));
        shelter.enqueue(new Cat("C1"));
        shelter.enqueue(new Dog("D3"));
        shelter.enqueue(new Cat("C2"));
        shelter.enqueue(new Cat("C3"));
        shelter.enqueue(new Cat("C4"));

        Animal animal1 = shelter.dequeueAny();
        assertThat(animal1.getName()).isEqualTo("D1");
        assertThat(animal1).isInstanceOf(Dog.class);

        Animal animal2 = shelter.dequeueAny();
        assertThat(animal2.getName()).isEqualTo("D2");
        assertThat(animal2).isInstanceOf(Dog.class);

        Animal animal3 = shelter.dequeueAny();
        assertThat(animal3.getName()).isEqualTo("C1");
        assertThat(animal3).isInstanceOf(Cat.class);

        Animal animal4 = shelter.dequeueAny();
        assertThat(animal4.getName()).isEqualTo("D3");
        assertThat(animal4).isInstanceOf(Dog.class);
    }


    @Test
    void shouldbeAbleToGetOldestAnimalEvenIfOnlyDogsAreThere() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        shelter.enqueue(new Dog("D1"));
        shelter.enqueue(new Dog("D2"));

        Animal animal1 = shelter.dequeueAny();
        assertThat(animal1.getName()).isEqualTo("D1");
        assertThat(animal1).isInstanceOf(Dog.class);

        Animal animal2 = shelter.dequeueAny();
        assertThat(animal2.getName()).isEqualTo("D2");
        assertThat(animal2).isInstanceOf(Dog.class);
    }

    @Test
    void shouldbeAbleToGetOldestAnimalEvenIfOnlyCatsAreThere() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        shelter.enqueue(new Cat("C1"));
        shelter.enqueue(new Cat("C2"));

        Animal animal1 = shelter.dequeueAny();
        assertThat(animal1.getName()).isEqualTo("C1");
        assertThat(animal1).isInstanceOf(Cat.class);

        Animal animal2 = shelter.dequeueAny();
        assertThat(animal2.getName()).isEqualTo("C2");
        assertThat(animal2).isInstanceOf(Cat.class);
    }

    @Test
    void shouldThrowErrorOnDequeAnimalIfNoAnimalsPresent() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> shelter.dequeueAny());
        assertThat(runtimeException.getMessage()).isEqualTo("No Dogs or Cats Available");
    }

    @Test
    void shouldThrowErrorOnDequeDogIfNoDogsPresent() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> shelter.dequeueDog());
        assertThat(runtimeException.getMessage()).isEqualTo("No Dogs Available");
    }

    @Test
    void shouldThrowErrorOnDequeCatIfNoDogsPresent() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> shelter.dequeueCat());
        assertThat(runtimeException.getMessage()).isEqualTo("No Cats Available");
    }


    @Test
    void shouldbeAbleToGetOldestDog() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        shelter.enqueue(new Dog("D1"));
        shelter.enqueue(new Dog("D2"));
        shelter.enqueue(new Cat("C1"));
        shelter.enqueue(new Dog("D3"));
        shelter.enqueue(new Cat("C2"));
        shelter.enqueue(new Cat("C3"));
        shelter.enqueue(new Cat("C4"));

        Animal animal1 = shelter.dequeueDog();
        assertThat(animal1.getName()).isEqualTo("D1");
        assertThat(animal1).isInstanceOf(Dog.class);

        Animal animal2 = shelter.dequeueDog();
        assertThat(animal2.getName()).isEqualTo("D2");
        assertThat(animal2).isInstanceOf(Dog.class);

        Animal animal3 = shelter.dequeueDog();
        assertThat(animal3.getName()).isEqualTo("D3");
        assertThat(animal3).isInstanceOf(Dog.class);
    }


    @Test
    void shouldbeAbleToGetOldestCat() {
        AnimalShelter<Animal> shelter = new AnimalShelter<>();

        shelter.enqueue(new Dog("D1"));
        shelter.enqueue(new Dog("D2"));
        shelter.enqueue(new Cat("C1"));
        shelter.enqueue(new Dog("D3"));
        shelter.enqueue(new Cat("C2"));
        shelter.enqueue(new Cat("C3"));
        shelter.enqueue(new Cat("C4"));

        Animal animal1 = shelter.dequeueCat();
        assertThat(animal1.getName()).isEqualTo("C1");
        assertThat(animal1).isInstanceOf(Cat.class);

        Animal animal2 = shelter.dequeueCat();
        assertThat(animal2.getName()).isEqualTo("C2");
        assertThat(animal2).isInstanceOf(Cat.class);

        Animal animal3 = shelter.dequeueCat();
        assertThat(animal3.getName()).isEqualTo("C3");
        assertThat(animal3).isInstanceOf(Cat.class);

        Animal animal4 = shelter.dequeueCat();
        assertThat(animal4.getName()).isEqualTo("C4");
        assertThat(animal4).isInstanceOf(Cat.class);
    }
}