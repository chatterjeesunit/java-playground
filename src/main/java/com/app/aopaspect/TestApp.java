package com.app.aopaspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by sunitc on 9/25/17.
 */
@SpringBootApplication
public class TestApp {

    public static void main(String args[]) {

        ConfigurableApplicationContext context = SpringApplication.run(TestApp.class, args);

        PersonService personService = context.getBean("personService", PersonService.class);

        Person person = new Person("Sunit", "Chatterjee");

        personService.modifyPersonName(person);
    }
}



