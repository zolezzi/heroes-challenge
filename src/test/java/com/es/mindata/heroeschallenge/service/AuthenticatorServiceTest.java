package com.es.mindata.heroeschallenge.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.es.mindata.heroeschallenge.dto.UserDTO;
import com.es.mindata.heroeschallenge.entity.User;
import com.es.mindata.heroeschallenge.repository.UserRepository;
import com.es.mindata.heroeschallenge.service.impl.AuthenticatorServiceImpl;
import com.es.mindata.heroeschallenge.utils.TokenUtil;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorServiceTest {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	private static final String USER_NOT_FOUND = "USER2";
	private static final String TOKEN_VALUE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NTMwMTAyNCwibmFtZSI6ImFkbWluIn0.1nWH5XPt71NJ3cC4nzrvXPLr31DnXg5iUUFg0dyemHbhYirBI4IMbR0KH_iNnt0x_dAwNWZco66w8XCOXIut9g";
	
	@Mock
	private User user;
	
	@Mock
	private UserRepository repository;
    
	@Mock
	private AuthenticationManager authenticationManager;
    
	@Mock
	private TokenUtil tokenUtil;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private AuthenticatorServiceImpl service;
	
	@Before
	public void setUp(){
		service = new AuthenticatorServiceImpl(repository, authenticationManager, tokenUtil);
		
		when(repository.findOneByUsername(USERNAME)).thenReturn(Optional.of(user));
		when(user.getUsername()).thenReturn(USERNAME);
		when(tokenUtil.createToken(eq(USERNAME))).thenReturn(TOKEN_VALUE);
	}
	
	@Test
	public void testLoginUserValidThenReturnJWTResponseWithUsernameAndToken(){
		var user = new UserDTO();
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		var jwtResponseDTO = service.login(user);
	    assertThat(jwtResponseDTO.getUsername(), is(USERNAME));
	    assertThat(jwtResponseDTO.getToken(), is(TOKEN_VALUE));
		assertNotNull(jwtResponseDTO.toString());
		assertNotNull(jwtResponseDTO.hashCode());
	    verify(repository).findOneByUsername(eq(USERNAME));
	    verify(authenticationManager).authenticate(any());
	}
	
	@Test
	public void testLoginUserNotFoundThenReturnThenReturnException(){
		ex.expect(UsernameNotFoundException.class);
		ex.expectMessage("No found user:USER2");
		var user = new UserDTO();
		user.setUsername(USER_NOT_FOUND);
		user.setPassword(PASSWORD);
		service.login(user);
		verify(repository, never()).findOneByUsername(eq(USER_NOT_FOUND));
	}
}
