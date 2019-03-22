package hello;

import hello.controller.PersonRestController;
import hello.model.Person;
import hello.service.PersonService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringcloudcontractPocProducerApplication.class)
public abstract class BaseClass {

        @Autowired
        PersonRestController personRestController;

        @MockBean
        PersonService personService;

        @Before public void setup() {
                RestAssuredMockMvc.standaloneSetup(personRestController);

                Person person1 = new Person(1L, "Daniel", "Adams", "male");
                Person person2 = new Person(2L, "Thomas", "Halter", "male");
                Person person3 = new Person(3L, "Tim", "Dobransky", "male");

                List<Person> list1 = new ArrayList<>();
                list1.add(person1);
                list1.add(person2);
                list1.add(person3);

                Mockito.when(personService.findPersonById(1L))
                                .thenReturn(person1);

                Mockito.when(personService.findPersonByName("daniel","adams"))
                        .thenReturn(Optional.of(person1));

                Mockito.when(personService.findPersonByGender("male"))
                        .thenReturn(list1);
        }

}
