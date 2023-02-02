package com.es.mindata.heroeschallenge.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.dto.HeroFilterDTO;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;
import com.es.mindata.heroeschallenge.vo.HeroVO;

@RunWith(MockitoJUnitRunner.class)
public class HeroControllerTest {
	
	private static final Long ID = 1L;
	private static final String NAME = "Spiderman";
	private static final String NAME_LIKE = "man";
	
	@Mock
	private HeroServiceImpl service;
	
	@Mock
	private HeroDTO heroDto;
	
	@InjectMocks
	private HeroController controller;
	
	@Before
	public void setUp(){
		when(service.findHeroById(ID)).thenReturn(heroDto);
		when(service.findAllHeroes()).thenReturn(List.of(heroDto));
		when(service.saveHero(any())).thenReturn(heroDto);
		when(service.updateHero(heroDto, ID)).thenReturn(heroDto);
		when(service.searchHeroByName(any())).thenReturn(List.of(heroDto));
	}
	
	@Test
	public void testFindHeroByIdThenReturnAHeroDTO(){
		assertThat(controller.findHeroById(ID), is(heroDto));
		verify(service).findHeroById(eq(ID));
	}
	
	@Test
	public void testFindAllHeroes(){
		assertThat(controller.findAllHeroes(), is(List.of(heroDto)));
		verify(service).findAllHeroes();
	}
	
	@Test
	public void testSaveHeroThenReturnHeroResponse(){
		var heroVO = new HeroVO();
		heroVO.setName(NAME);
		var response = controller.saveHero(heroVO);
		assertThat(response.getMessage(),is(notNullValue()));    
		assertThat(response.getError(),is(Boolean.FALSE));
		assertThat(response.getResponse(),is(heroDto));
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).saveHero(eq(heroVO));
	}
	
	@Test
	public void testUpdateHeroThenReturnHeroResponse(){
		var response = controller.updateHero(heroDto, ID);
		assertThat(response.getMessage(),is(notNullValue()));    
		assertThat(response.getError(),is(Boolean.FALSE));
		assertThat(response.getResponse(),is(heroDto));
		verify(service).updateHero(eq(heroDto), eq(ID));
	}
	
	@Test
	public void testDeleteHeroThenReturnBasicResponse(){
		var response = controller.deleteHeroById(ID);
		assertThat(response.getMessage(),is(notNullValue()));    
		assertThat(response.getError(),is(Boolean.FALSE));
		assertNotNull(response.toString());
		assertNotNull(response.hashCode());
		verify(service).deleteHeroById(eq(ID));
	}
	
	@Test
	public void testSearchHeroesByName(){
		var heroFilterDto = new HeroFilterDTO(NAME_LIKE);
		assertThat(controller.searchHeroByName(heroFilterDto), is(List.of(heroDto)));
		verify(service).searchHeroByName(eq(heroFilterDto));
	}
	
}
