package com.es.mindata.heroeschallenge.service.impl;

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
		var heroOpt = repository.findById(heroId);
		if(heroOpt.isEmpty()) {
			throw new HeroNotFoundException("Hero not found");
		}
		return heroOpt.get();
	}

}
