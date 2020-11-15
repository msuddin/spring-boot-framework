package com.thetestroom.springbootframework.dao;

import com.thetestroom.springbootframework.model.Person;

import java.util.UUID;

// Contains the implementation to connect to a DB
// The operations allowed on this person (CRUD)
public interface PersonDao {

    int insertPerson (UUID id, Person person);

    // Will generate the UUDI for us at random
    default int insertPerson (Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
}
