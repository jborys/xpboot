package com.xp.tdd.xpboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.xp.tdd.xpboot.model.Person;
import com.xp.tdd.xpboot.repository.PersonRespository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	@Mock
	private PersonRespository personRepo;
	
	private PersonService personService;
	
	@Before
	public void setUp() throws Exception  {
		personService = new PersonService(personRepo);
	}
	
	@Test
	public void testGetPersonDetails() {
		given(personRepo.findPersonByFirstNameAndLastName("Scott", "Creamer")).willReturn(new Person("Scott", "Creamer"));
		
		Person person = personService.getPersonDetails("Scott", "Creamer");
		
		assertThat(person.getFirstName()).isEqualTo("Scott");
		assertThat(person.getLastName()).isEqualTo("Creamer");
	}

	@Test(expected = PersonNotFoundException.class)
	public void getPersonDetails_shouldThrowNotFoundException() throws Exception {
		given(personRepo.findPersonByFirstNameAndLastName("Scott", "Creamer")).willReturn(null);
		
		personService.getPersonDetails("Scott", "Creamer");
	}
	
}
