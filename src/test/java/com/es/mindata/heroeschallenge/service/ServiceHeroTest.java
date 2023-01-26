package com.es.mindata.heroeschallenge.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.exception.HeroNotFoundException;
import com.es.mindata.heroeschallenge.repository.HeroRepository;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceHeroTest {

	private static final Long ID = 1L;
	private static final Long ID_HERO_NOT_FOUND = 10L;
	
	@Mock
	private Hero hero;
	
	@Mock
	private HeroRepository repository;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
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
	
	@Test
	public void testFindHeroByIdAndNotFoundThenReturnException(){
		ex.expect(HeroNotFoundException.class);
		ex.expectMessage("Hero not found");
		service.findHeroById(ID_HERO_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_HERO_NOT_FOUND));
	}


}
