package hello.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import hello.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

        private final Map<Long, Person> personMap;

        public PersonService() {
                personMap = new HashMap<>();
                personMap.put(1L, new Person(1L, "Daniel", "Adams", "male"));
                personMap.put(2L, new Person(2L, "Thomas", "Halter", "male"));
                personMap.put(3L, new Person(3L, "Tim", "Dobransky", "male"));
                personMap.put(4L, new Person(4L, "Michelle", "Sinkovich", "female"));
        }

        public Person findPersonById(Long id) {
                return personMap.get(id);
        }

        public Optional<Person> findPersonByName(String firstName, String lastName) {

            return personMap.values().stream().filter(person ->
                        person.getFirstName().equalsIgnoreCase(firstName) &&
                        person.getLastName().equalsIgnoreCase(lastName)).findFirst();
        }

        public List<Person> findPersonByGender(String gender) {
                return personMap.values().stream().filter(person -> person.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
        }
}