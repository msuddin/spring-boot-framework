package com.thetestroom.springbootframework.api;

import com.thetestroom.springbootframework.model.Person;
import com.thetestroom.springbootframework.service.PersonService;

public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void addPerson(Person person) {
        personService.addPerson(person);
    }
}
