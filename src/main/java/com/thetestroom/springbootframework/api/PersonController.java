package com.thetestroom.springbootframework.api;

import com.thetestroom.springbootframework.model.Person;
import com.thetestroom.springbootframework.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
/*
The @RestController tag allows us to use methods in this class exposed as REST endpoints
 */
/*
The @RequestMapping("path') allows us to tell Spring what endpoint this rest controller is listening to
 */
/*
A controller class should only contain all the endpoints needed
 */
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    /*
    The @PostMapping annotation is used on a method and is used to tell Spring that this method is used
    as a post.
     */
    /*
    The  @RequestBody tag in the method parameter is used to tell Spring that we will take the request body
    that comes in and try to convert it into a Person object.
     */
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    /*
    The @GetMapping annotation is used on a method and is used to tell Spring that this method is used
    as a Get.
     */
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    /*
    The @PathVariable("id") is used to tell spring to pull in a url parameter that is passed in an endpoint.
    The @GetMapping(path = "{id}") is used to hit this endpoint when passing in an ID in the endpoint URL.
     */
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
