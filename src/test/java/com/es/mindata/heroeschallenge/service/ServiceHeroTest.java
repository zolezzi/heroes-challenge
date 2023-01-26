package com.es.mindata.heroeschallenge.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.repository.HeroRepository;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceHeroTest {

	private static final Long ID = 1L;
	
	@Mock
	private Hero hero;
	
	@Mock
	private HeroRepository repository;
	
	private HeroServiceImpl service;
	
	@Before
	public void setUp(){
		service = new HeroServiceImpl(repository);
		when(repository.findById(ID)).thenReturn(Optional.of(hero));
	}
	
	@Test
	public void testFindHeroByIdThenReturnAHero(){
		Hero heroBD = service.findHeroById(ID);
	    assertThat(heroBD, is(hero));
	    verify(repository).findById(eq(ID));
	}

}
