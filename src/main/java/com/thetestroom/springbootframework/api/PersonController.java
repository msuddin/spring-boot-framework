package com.thetestroom.springbootframework.api;

import com.thetestroom.springbootframework.model.Person;
import com.thetestroom.springbootframework.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")
@RestController
/*
The @RestController tag allows us to use methods in this class exposed as REST endpoints
 */
/*
The @RequestMapping("path') allows us to tell Spring what endpoint this rest controller is listening to
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
}
