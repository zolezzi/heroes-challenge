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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.dto.HeroFilterDTO;
import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.exception.HeroNotFoundException;
import com.es.mindata.heroeschallenge.repository.HeroRepository;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;
import com.es.mindata.heroeschallenge.utils.MapperUtil;
import com.es.mindata.heroeschallenge.vo.HeroVO;

@RunWith(MockitoJUnitRunner.class)
public class HeroServiceTest {

	private static final Long ID = 1L;
	private static final Long ID_HERO_NOT_FOUND = 10L;
	private static final Long ID_HERO_DELETE = 2L;
	private static final Long ID_HERO_DELETE_NOT_FOUND = 10L;
	private static final String NAME = "Spiderman";
	private static final String NAME_LIKE = "man";
	private static final String NAME_LIKE_VALUE = "%man%";
	private static final String NAME_LIKE_NOT_MATCH = "Bat";
	private static final String NAME_LIKE_NOT_MATCH_VALUE = "%Bat%";
	
	
	@Mock
	private Hero hero;
	
	@Mock
	private HeroDTO heroDto;
	
	@Mock
	private HeroRepository repository;
	
	@Mock
	private MapperUtil mapperUtil;
	
	@Mock
	private ModelMapper mapper;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private HeroServiceImpl service;
	
	
	@Before
	public void setUp(){
		service = new HeroServiceImpl(repository, mapperUtil);
		
		when(hero.getName()).thenReturn(NAME);
		
		when(repository.findById(ID)).thenReturn(Optional.of(hero));
		when(mapperUtil.getMapper()).thenReturn(mapper);
		when(mapper.map(eq(hero), eq(HeroDTO.class))).thenReturn(heroDto);
		when(mapper.map(any(), eq(Hero.class))).thenReturn(hero);
		when(repository.findById(ID_HERO_DELETE)).thenReturn(Optional.of(hero));
		when(repository.save(hero)).thenReturn(hero);
		when(repository.save(hero)).thenReturn(hero);
		when(repository.findAll()).thenReturn(List.of(hero));
		when(repository.searchHeroByName(NAME_LIKE_VALUE)).thenReturn(List.of(hero));
		when(repository.searchHeroByName(NAME_LIKE_NOT_MATCH_VALUE)).thenReturn(Collections.emptyList());
		when(repository.searchHeroByName(NAME_LIKE_VALUE)).thenReturn(List.of(hero));
		when(repository.searchHeroByName(NAME_LIKE_NOT_MATCH_VALUE)).thenReturn(Collections.emptyList());
	}
	
	@Test
	public void testFindHeroByIdThenReturnAHero(){
		HeroDTO heroResponse = service.findHeroById(ID);
	    assertThat(heroResponse, is(heroDto));
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
	    assertThat(service.findAllHeroes(), is(List.of(heroDto)));
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
		var heroVO = new HeroVO();
		heroVO.setName(NAME);
	    assertThat(service.saveHero(heroVO), is(heroDto));
	    verify(repository).save(eq(hero));
	}
	
	@Test
	public void testUpdateHero(){
		assertThat(hero.getName(), is(NAME));
		hero.setName("Spider-Man");
	    assertThat(service.updateHero(heroDto, ID), is(heroDto));
	    verify(repository).save(eq(hero));
	    verify(repository, times(1)).save(hero);
	}
	
	@Test
	public void testUpdateHeroAndNotFoundThenReturnException(){
		ex.expect(HeroNotFoundException.class);
		ex.expectMessage("Hero not found");
		service.updateHero(heroDto, ID_HERO_NOT_FOUND);
	    verify(repository, never()).findById(eq(ID_HERO_NOT_FOUND));
	    verify(repository, never()).save(eq(hero));
	}
	
	@Test
	public void testSearchHeroByNameThenReturnListHeroes(){
		var heroFilterDto = new HeroFilterDTO(NAME_LIKE);
	    assertThat(service.searchHeroByName(heroFilterDto), is(List.of(heroDto)));
	    verify(repository).searchHeroByName(NAME_LIKE_VALUE);
	    verify(repository, times(1)).searchHeroByName(NAME_LIKE_VALUE);
	}
	
	@Test
	public void testSearchHeroByNameAndNotMatchedThenReturnEmptyLists(){
		var heroFilterDto = new HeroFilterDTO(NAME_LIKE_NOT_MATCH);
	    assertThat(service.searchHeroByName(heroFilterDto), is(Collections.emptyList()));
	    verify(repository).searchHeroByName(NAME_LIKE_NOT_MATCH_VALUE);
	    verify(repository, times(1)).searchHeroByName(NAME_LIKE_NOT_MATCH_VALUE);
	}
	
	@Test
	public void testSearchHeroByNameAndNotFoundThenReturnException(){
		ex.expect(HeroNotFoundException.class);
		ex.expectMessage("Hero not found");
		service.searchHeroByName(null);
	    verify(repository, never()).searchHeroByName(eq(anyString()));
	}
}
