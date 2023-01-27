package com.es.mindata.heroeschallenge.controller.advice;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
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

	private static final String MESSAGE = "message";
	
	@Mock
	private HeroNotFoundException heroNotFoundException;
	
	@InjectMocks
	  private ExceptionHandlerController exceptionHandlerController;
	
	@Before
	public void setUp() {
		when(heroNotFoundException.getMessage()).thenReturn(MESSAGE);
	}
	
	@Test
	public void testHandleNotFoundHero(){
		ResponseEntity<BasicResponse> response = exceptionHandlerController.handleNotFoundException(heroNotFoundException);
		assertThat(response, is(notNullValue()));
		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
		assertThat(response.getBody().getMessage(), is("Not Found"));
		assertThat(response.getBody().getError(), is(Boolean.TRUE));
	}
}
