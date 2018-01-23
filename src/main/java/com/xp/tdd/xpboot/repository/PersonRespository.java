package com.xp.tdd.xpboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xp.tdd.xpboot.model.Person;

@Repository
public interface PersonRespository extends CrudRepository<Person, Long> {

	public Person findPersonByFirstNameAndLastName(String firstName, String lastName);

}
