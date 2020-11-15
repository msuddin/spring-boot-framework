package com.thetestroom.springbootframework.dao;

import com.thetestroom.springbootframework.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
/*
Use the @Repository tag to tell Spring that this class needs to be instantiated as a bean for other classes.
Can also say @Component but @Repository says that this is a DAO component.
You can optionally give it a name if the interface is implemented multiple times.
 */
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> db = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        db.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return db;
    }
}
