package com.es.mindata.heroeschallenge.service;

import java.util.List;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.vo.HeroVO;

public interface HeroService {

	HeroDTO findHeroById(Long heroId);
	
	List<HeroDTO> findAllHeroes();
	
	void deleteHeroById(Long id);
	
	HeroDTO saveHero(HeroVO hero);
	
	Hero updateHero(Hero hero, Long heroId);
	
	List<Hero> searchHeroByName(String name);
}
