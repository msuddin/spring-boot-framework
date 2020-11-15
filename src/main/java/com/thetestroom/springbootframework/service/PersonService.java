package com.thetestroom.springbootframework.service;

import com.thetestroom.springbootframework.dao.PersonDao;
import com.thetestroom.springbootframework.model.Person;

public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }
}
