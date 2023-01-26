package com.es.mindata.heroeschallenge.service;

import java.util.List;

import com.es.mindata.heroeschallenge.entity.Hero;

public interface HeroService {

	Hero findHeroById(Long heroId);
	
	List<Hero> findAllHeroes();
	
	void deleteHeroById(Long id);
	
	Hero saveHero(Hero hero);
}
