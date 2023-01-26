package com.es.mindata.heroeschallenge.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.es.mindata.heroeschallenge.exception.HeroNotFoundException;
import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.repository.HeroRepository;
import com.es.mindata.heroeschallenge.service.HeroService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
	
	private final HeroRepository repository;
	
	public Hero findHeroById(Long heroId) {
		return getHeroById(heroId);
	}

	public List<Hero> findAllHeroes() {
		return repository.findAll();
	}

	public void deleteHeroById(Long heroId) {
		 var HeroDB = getHeroById(heroId);
		 repository.delete(HeroDB);
	}
	
	public Hero saveHero(Hero hero) {
		return repository.save(hero);
	}
	
	public Hero updateHero(Hero hero, Long heroId) {
		var HeroDB = getHeroById(heroId);
		HeroDB.setName(hero.getName());
		return repository.save(HeroDB);
	}

	public List<Hero> searchHeroByName(String name) {
		if(name == null) {
			throw new HeroNotFoundException("Hero not found");
		}
		return repository.searchHeroByName(name);
	}
	
	private Hero getHeroById(Long heroId) {
		var heroOpt = repository.findById(heroId);
		if(heroOpt.isEmpty()) {
			throw new HeroNotFoundException("Hero not found");
		}
		return heroOpt.get();
	}

}
