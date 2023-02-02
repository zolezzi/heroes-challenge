package com.es.mindata.heroeschallenge.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeroTest {
	
	private static final Long ID = 1L;
	private static final String NAME = "Spider-Man";
	private Hero hero;
	private Hero otherHero;
	
	@Before
	public void setUp(){
		hero = new Hero();
		hero.setId(ID);
		hero.setName(NAME);
		otherHero = new Hero();
		hero.setId(ID);
		hero.setName(NAME);
	}
	
	@Test
	public void testAccessors(){
		assertThat(hero.getId(), is(ID));
		assertThat(hero.getName(), is(NAME));
		assertNotNull(hero.toString());
		assertNotNull(hero.hashCode());
		assertFalse(hero.equals(otherHero));
	}
}

