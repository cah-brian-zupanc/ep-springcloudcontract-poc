package hello.controller;

import hello.model.Person;
import hello.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonRestController {

        private final PersonService personService;

        public PersonRestController(PersonService personService) {
                this.personService = personService;
        }

        @GetMapping("/person/{id}")
        public Person findPersonById(@PathVariable("id") Long id) {
                return personService.findPersonById(id);
        }

        @GetMapping("/person/{firstName}/{lastName}")
        public Optional<Person> findPersonById(@PathVariable("firstName") String firstName,
                                               @PathVariable("lastName") String lastName) {
                return personService.findPersonByName(firstName,lastName);
        }

        @GetMapping("/persons/{gender}")
        public List<Person> findPersonsByGender(@PathVariable("gender") String gender) {
                return personService.findPersonByGender(gender);
        }
}