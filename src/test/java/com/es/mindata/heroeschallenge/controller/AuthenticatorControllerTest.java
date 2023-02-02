package com.es.mindata.heroeschallenge.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.es.mindata.heroeschallenge.dto.JwtResponseDTO;
import com.es.mindata.heroeschallenge.dto.UserDTO;
import com.es.mindata.heroeschallenge.service.impl.AuthenticatorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorControllerTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	@Mock
	private AuthenticatorServiceImpl service;
	
	@Mock
	private JwtResponseDTO jwtResponseDto;
	
	@InjectMocks
	private AuthenticatorController controller;
	
	@Before
	public void setUp(){
		when(service.login(any())).thenReturn(jwtResponseDto);
	}
	
	@Test
	public void testFindHeroByIdThenReturnAHeroDTO(){
		var user = new UserDTO();
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		assertThat(controller.login(user), is(jwtResponseDto));
		verify(service).login(any());
	}
}
