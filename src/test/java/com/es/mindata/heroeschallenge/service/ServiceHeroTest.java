package com.es.mindata.heroeschallenge.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
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
	private static final Long ID_HERO_DELETE = 2L;
	private static final Long ID_HERO_DELETE_NOT_FOUND = 10L;
	private static final String NAME = "Spiderman";
	private static final String NAME_LIKE = "man";
	
	
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
		
		when(hero.getName()).thenReturn(NAME);
		
		when(repository.findById(ID)).thenReturn(Optional.of(hero));
		when(repository.findById(ID_HERO_DELETE)).thenReturn(Optional.of(hero));
		when(repository.save(hero)).thenReturn(hero);
		when(repository.save(hero)).thenReturn(hero);
		when(repository.findAll()).thenReturn(List.of(hero));
		when(repository.searchHeroByName(NAME_LIKE)).thenReturn(List.of(hero));
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

	@Test
	public void testFindAllHeroesWithElements(){
	    assertThat(service.findAllHeroes(), is(List.of(hero)));
	    verify(repository).findAll();
	}
	
	@Test
	public void testDeleteHeroById(){
		service.deleteHeroById(ID_HERO_DELETE);
	    verify(repository).delete(any(Hero.class));
	}
	
	@Test
	public void testDeleteHeroByIdAndNotFoundThenReturnException(){
		ex.expect(HeroNotFoundException.class);
		ex.expectMessage("Hero not found");
		service.deleteHeroById(ID_HERO_DELETE_NOT_FOUND);
		verify(repository, never()).findById(eq(ID_HERO_DELETE_NOT_FOUND));
	}
	
	@Test
	public void testSaveHero(){
	    assertThat(service.saveHero(hero), is(hero));
	    verify(repository).save(eq(hero));
	}
	
	@Test
	public void testUpdateHero(){
		assertThat(hero.getName(), is(NAME));
		hero.setName("Spider-Man");
	    assertThat(service.updateHero(hero, ID), is(hero));
	    verify(repository).save(eq(hero));
	    verify(repository, times(1)).save(hero);
	}
	
	@Test
	public void testUpdateHeroAndNotFoundThenReturnException(){
		ex.expect(HeroNotFoundException.class);
		ex.expectMessage("Hero not found");
		service.updateHero(hero, ID_HERO_NOT_FOUND);
	    verify(repository, never()).findById(eq(ID_HERO_NOT_FOUND));
	    verify(repository, never()).save(eq(hero));
	}
	
	@Test
	public void testSearchHeroByNameThenReturnListHeroes(){
	    assertThat(service.searchHeroByName(NAME_LIKE), is(List.of(hero)));
	    verify(repository).searchHeroByName(NAME_LIKE);
	    verify(repository, times(1)).searchHeroByName(NAME_LIKE);
	}
	
	@Test
	public void testSearchHeroByNameAndNotFoundThenReturnException(){
		ex.expect(HeroNotFoundException.class);
		ex.expectMessage("Hero not found");
		service.searchHeroByName(null);
	    verify(repository, never()).searchHeroByName(eq(anyString()));
	}
}
