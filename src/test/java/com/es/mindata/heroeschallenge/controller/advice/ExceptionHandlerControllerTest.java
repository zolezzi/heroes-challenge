package com.es.mindata.heroeschallenge.controller.advice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.es.mindata.heroeschallenge.controller.response.BasicResponse;
import com.es.mindata.heroeschallenge.exception.HeroNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerControllerTest {
	
	@Mock
	private HeroNotFoundException heroNotFoundException;
	
	@InjectMocks
	  private ExceptionHandlerController exceptionHandlerController;
	
	@Test
	public void testHandleNotFoundHero(){
		ResponseEntity<BasicResponse> response = exceptionHandlerController.handleNotFoundException(heroNotFoundException);
		assertThat(response, is(notNullValue()));
		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
		assertThat(response.getBody().getMessage(), is("Not Found"));
		assertThat(response.getBody().getError(), is(Boolean.TRUE));
	}
}
