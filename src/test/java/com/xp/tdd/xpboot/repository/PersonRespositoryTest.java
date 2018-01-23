package com.xp.tdd.xpboot.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.xp.tdd.xpboot.model.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRespositoryTest {

	@Autowired
	private PersonRespository repository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindPersonByFirstNameAndLastName() {
		Person savedPerson = entityManager.persistFlushFind(new Person("Scott", "Creamer"));
		Person person = repository.findPersonByFirstNameAndLastName("Scott", "Creamer");
		
		Assertions.assertThat(person.getFirstName()).isEqualTo(savedPerson.getFirstName());
		Assertions.assertThat(person.getLastName()).isEqualTo(savedPerson.getLastName());
	}

}
