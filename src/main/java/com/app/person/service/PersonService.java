package com.app.person.service;

import com.app.person.annotation.CustomPermissionChecker;
import com.app.person.domain.Person;
import com.app.person.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunitc on 9/25/17.
 */

@Service
public class PersonService {

    @Autowired
    private EmployeeRepository repository;

    @CustomPermissionChecker(className = "User.class", permissionList = "Create,View")
    public Person createPerson(Person person) {
        return repository.save(person);
    }

    @CustomPermissionChecker(className = "User.class", permissionList = "Create,View")
    public Person updatePerson(Person person) {
        return repository.save(person);
    }


    public List<Person> findPersonByName(String name) {
        return repository.findPersonByFirstNameOrLastNameIgnoreCase(name);
    }

    public List<Person> findPersonByCity(String city) {
        return repository.findPersonByCityIgnoreCase(city);
    }

    public Person getPerson(String id) {
        return repository.findOne(id);
    }

    public void deletePerson(String id) {
        repository.delete(id);
    }

    @CustomPermissionChecker(className = "User.class", permissionList = "Delete")
    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Person> findAll() {
        return repository.findAll();
    }
}
