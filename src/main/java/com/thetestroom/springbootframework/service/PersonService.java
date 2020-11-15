package com.thetestroom.springbootframework.service;

import com.thetestroom.springbootframework.dao.PersonDao;
import com.thetestroom.springbootframework.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
/*
Use the  @Service tag to tell Spring that this is a service.
Can also use @Component here but @Service is much more clearer.
A service class should also contain all the logic
 */
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    /*
    Looks for a Bean that matches the parameters in the method and automatically instantiates them.
    If you autowire an interface, then you must provide some identifier as there could be multiple
    implementations of a single interface. This is where you would use the @Qualifier tag.
     */
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }
}
