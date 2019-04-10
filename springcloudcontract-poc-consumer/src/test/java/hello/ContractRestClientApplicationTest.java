package hello;

import hello.model.Person;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureStubRunner(ids="com.example:springcloudcontract-poc-producer:+:stubs:8100",
//                         stubsMode= StubsMode.LOCAL)
//Configure Nexus
//
@AutoConfigureStubRunner(ids="com.example:springcloudcontract-poc-producer:+:stubs:8100",
                        repositoryRoot="https://nexus.cardinalhealth.net/repository/",
                        stubsMode= StubsMode.REMOTE)
public class ContractRestClientApplicationTest {

        @Test
        public void get_person_from_service_contract() {

                // given:
                RestTemplate restTemplate = new RestTemplate();

                // when:
                ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity("http://localhost:8100/person/1", Person.class);

                // then:
                BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
                BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
                BDDAssertions.then(personResponseEntity.getBody().getFirstName()).isEqualTo("Daniel");
                BDDAssertions.then(personResponseEntity.getBody().getLastName()).isEqualTo("Adams");

        }

        @Test
        public void get_person_from_name_service_contract() {

                // given:
                RestTemplate restTemplate = new RestTemplate();

                // when:
                ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity("http://localhost:8100/person/daniel/adams", Person.class);

                // then:
                BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
                BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
                BDDAssertions.then(personResponseEntity.getBody().getFirstName()).isEqualTo("Daniel");
                BDDAssertions.then(personResponseEntity.getBody().getLastName()).isEqualTo("Adams");

        }

        @Test
        public void get_person_from_gender_service_contract() {

                // given:
                RestTemplate restTemplate = new RestTemplate();

                // when:
                ResponseEntity<List<Person>> personsResponseEntity = restTemplate.exchange(
                        "http://localhost:8100/persons/male",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Person>>(){});
                System.out.println(personsResponseEntity.getBody().toString());

                // then:
                List<Person> tester = personsResponseEntity.getBody();

                BDDAssertions.then(personsResponseEntity.getStatusCodeValue()).isEqualTo(200);
                BDDAssertions.then(tester.get(0).getFirstName()).isEqualTo("Daniel");
                BDDAssertions.then(tester.get(0).getLastName()).isEqualTo("Adams");
                BDDAssertions.then(tester.get(0).getGender()).isEqualTo("male");
                BDDAssertions.then(tester.get(1).getFirstName()).isEqualTo("Thomas");
                BDDAssertions.then(tester.get(1).getLastName()).isEqualTo("Halter");
                BDDAssertions.then(tester.get(1).getGender()).isEqualTo("male");
                BDDAssertions.then(tester.get(2).getFirstName()).isEqualTo("Tim");
                BDDAssertions.then(tester.get(2).getLastName()).isEqualTo("Dobransky");
                BDDAssertions.then(tester.get(2).getGender()).isEqualTo("male");

        }


}