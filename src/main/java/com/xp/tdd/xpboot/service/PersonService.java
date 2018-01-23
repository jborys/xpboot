package com.xp.tdd.xpboot.service;

import org.springframework.stereotype.Service;

import com.xp.tdd.xpboot.model.Person;
import com.xp.tdd.xpboot.repository.PersonRespository;

@Service
public class PersonService {
	
	PersonRespository personRepo;
	
	PersonService(PersonRespository personRepo) {
		this.personRepo = personRepo;
	}

	public Person getPersonDetails(String firstName, String lastName) {
		Person person = personRepo.findPersonByFirstNameAndLastName(firstName, lastName);
		if (person == null)  {
			throw new PersonNotFoundException();
		}
		return person;
	}

}
