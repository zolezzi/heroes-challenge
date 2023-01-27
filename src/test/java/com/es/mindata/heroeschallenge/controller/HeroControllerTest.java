package com.es.mindata.heroeschallenge.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class HeroControllerTest {
	
	private static final Long ID = 1L;

	@Mock
	private HeroServiceImpl service;
	
	@Mock
	private HeroDTO heroDto;
	
	@InjectMocks
	private HeroController controller;
	
	@Before
	public void setUp(){
		when(service.findHeroById(ID)).thenReturn(heroDto);
	}
	
	@Test
	public void testFindHeroByIdThenReturnAHeroDTO(){
		assertThat(controller.findHeroById(ID), is(heroDto));
		verify(service).findHeroById(eq(ID));
	}
	
//	@Test
//	public void testFindHeroByIdThenReturnAHeroDTO(){
//		assertThat(controller.findHeroById(ID), is(heroDto));
//		verify(service).findHeroById(eq(ID));
//	}
}
