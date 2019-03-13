package hello;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(ids="com.example:springcloudcontract-poc-producer:+:stubs:8100",
                         stubsMode= StubsMode.LOCAL)
//@AutoConfigureStubRunner(ids="com.example:springcloudcontract-poc-producer:+:stubs:8100",
//                        repositoryRoot="https://nexus.cardinalhealth.net/repository/",
//                        stubsMode= StubsMode.REMOTE)
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
}