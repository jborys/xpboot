package com.xp.tdd.xpboot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.xp.tdd.xpboot.model.Person;
import com.xp.tdd.xpboot.service.PersonNotFoundException;
import com.xp.tdd.xpboot.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Test
	public void getPerson_shouldReturnPersonDetails()	throws Exception {
		given(personService.getPersonDetails(anyString(), anyString())).willReturn(new Person("Scott", "Creamer"));
		
		mockMvc.perform(get("/person/Scott/Creamer")).andExpect(status().isOk())
			.andExpect(jsonPath("firstName").value("Scott"))
			.andExpect(jsonPath("lastName").value("Creamer"));
	}
	
	@Test
	public void getPerson_notFound() throws Exception  {
		given(personService.getPersonDetails(anyString(), anyString())).willThrow(new PersonNotFoundException());
		
		mockMvc.perform(get("/person/Scott/Creamer")).andExpect(status().isNotFound());
	}
}
