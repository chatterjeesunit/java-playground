package com.app.person.rest;

import com.app.person.domain.Person;
import com.app.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * Created by sunitc on 9/25/17.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE, path = "/")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person createdPerson = personService.createPerson(person);
            return ResponseEntity.created(new URI("/employee/" + createdPerson.getId())).body(createdPerson);
        }catch(Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") String personId,  @RequestBody Person person) {
        try {
            person.setId(personId);
            Person updatedPerson = personService.updatePerson(person);
            return ResponseEntity.ok(updatedPerson);
        }catch(Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/")
    @ResponseBody
    public List<Person> findPersonByName() {
        return personService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/name/{arg}")
    @ResponseBody
    public List<Person> findPersonByName(@PathVariable(value = "arg") String name) {
        return personService.findPersonByName(name);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/city/{arg}")
    @ResponseBody
    public List<Person> findPersonByCity(@PathVariable(value = "arg") String name) {
        return personService.findPersonByCity(name);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}")
    @ResponseBody
    public Person getPerson(@PathVariable(value = "id") String id) {
        return personService.getPerson(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable(value = "id") String id) {
        personService.deletePerson(id);
        return "Employee with id = " + id + " deleted successfully";
    }



    @RequestMapping(method = RequestMethod.DELETE, path = "/all")
    @ResponseBody
    public String deleteAll() {
        personService.deleteAll();
        return "All employees deleted successfully";
    }


}
