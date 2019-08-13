package com.app.aopaspect;

import org.springframework.stereotype.Service;

/**
 * Created by sunitc on 9/25/17.
 */

@Service
public class PersonService {


    @CustomAnnotation(beanName = "personService")
    public Person modifyPersonName(Person person) {
        person.setFirstName(person.getFirstName().toUpperCase());
        person.setLastName(person.getLastName().toUpperCase());

        return person;
    }

}
