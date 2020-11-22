package com.thetestroom.springbootframework.api;

import com.thetestroom.springbootframework.model.Person;
import com.thetestroom.springbootframework.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @Test
    public void shouldBeAbleToCreateAPerson() {
        // Given
        String testName = "Batman";
        List<Person> people = new ArrayList<Person>();
        people.add(new Person(UUID.randomUUID(), testName));

        // When
        when(personService.getAllPeople()).thenReturn(people);
        String personName = personController.getAllPeople().stream()
                .findFirst().get().getName();

        // Then
        assertThat(personName, is(testName));
    }
}
