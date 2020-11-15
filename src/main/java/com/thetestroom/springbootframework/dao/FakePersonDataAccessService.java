package com.thetestroom.springbootframework.dao;

import com.thetestroom.springbootframework.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
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

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return db.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        // Is personMaybe is empty then list was empty i.e. person was not found
        if (personMaybe.isEmpty()) {
            return 0;
        }
        // Other remove the person
        db.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatedPerson) {
        // First we select the person by id
        return selectPersonById(id)
                .map(person -> {
                    // Then we get the index value of the person in the DB
                    int indexOfPersonToUpdate = db.indexOf(person);
                    // Check to see if we found something
                    if (indexOfPersonToUpdate >= 0) {
                        // If we did then we replace that person
                        db.set(indexOfPersonToUpdate, new Person(id, updatedPerson.getName()));
                        return 1;
                    }
                    return 0;
                })
                // If the first call to selectByPersonById return nothing then we return 0
                .orElse(0);
    }
}
