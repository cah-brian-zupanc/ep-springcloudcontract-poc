package hello;

import hello.controller.PersonRestController;
import hello.model.Person;
import hello.service.PersonService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringcloudcontractPocProducerApplication.class)
public abstract class AnotherBaseClass {

        @Autowired
        PersonRestController personRestController;

        @MockBean
        PersonService personService;

        @Before public void setup() {
                RestAssuredMockMvc.standaloneSetup(personRestController);

                Mockito.when(personService.findPersonById(1L))
                                .thenReturn(new Person(1L, "Daniel", "Adams"));
        }

}
