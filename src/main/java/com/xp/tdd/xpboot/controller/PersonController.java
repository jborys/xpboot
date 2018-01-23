package com.xp.tdd.xpboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xp.tdd.xpboot.model.Person;
import com.xp.tdd.xpboot.service.PersonNotFoundException;
import com.xp.tdd.xpboot.service.PersonService;

@RestController
public class PersonController {

	private PersonService personService;
	
	public PersonController(PersonService personService)	{
		this.personService = personService;
	}

	@GetMapping("/person/{firstName}/{lastName}")
	public Person getPserson(@PathVariable String firstName, @PathVariable String lastName)  {
		return personService.getPersonDetails(firstName, lastName);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void personNotFoundHandler(PersonNotFoundException ex) {
				
	}
}
