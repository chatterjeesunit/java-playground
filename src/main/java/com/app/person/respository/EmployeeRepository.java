package com.app.person.respository;

import com.app.person.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunitc on 9/25/17.
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Person, String> {

    public List<Person> findAll();

    public List<Person> findPersonByFirstNameOrLastNameIgnoreCase(String name);

    public List<Person> findPersonByCityIgnoreCase(String city);
}


