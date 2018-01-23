package com.xp.tdd.xpboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.xp.tdd.xpboot.model.Person;
import com.xp.tdd.xpboot.service.PersonService;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PersonIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @MockBean
    private PersonService service;
    
    @Before
    public void setup()  {
    		given(this.service.getPersonDetails(anyString(), anyString()))
    			.willReturn(new Person("Scott", "Creamer"));
    }

    @Test
    public void getPerson_returnsPersonDetails() throws Exception {
        //Arrange

        //Act
        ResponseEntity<Person> response = this.restTemplate.getForEntity("/person/Scott/Creamer", Person.class);

        //Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstName()).isEqualTo("Scott");
        assertThat(response.getBody().getLastName()).isEqualTo("Creamer");
    }

}
