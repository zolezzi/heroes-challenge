package com.es.mindata.heroeschallenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.entity.Hero;
import com.es.mindata.heroeschallenge.exception.HeroNotFoundException;
import com.es.mindata.heroeschallenge.repository.HeroRepository;
import com.es.mindata.heroeschallenge.service.HeroService;
import com.es.mindata.heroeschallenge.utils.MapperUtil;
import com.es.mindata.heroeschallenge.vo.HeroVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
	
	private final HeroRepository repository;
	private final MapperUtil mapperUtil;
	
	public HeroDTO findHeroById(Long heroId) {
		var HeroDB = getHeroById(heroId);
		return mapperUtil.getMapper().map(HeroDB, HeroDTO.class);
	}

	public List<HeroDTO> findAllHeroes() {
		return  repository.findAll()
				.stream()
				.map((hero -> mapperUtil.getMapper().map(hero, HeroDTO.class)))
				.collect(Collectors.toList());
	}

	public void deleteHeroById(Long heroId) {
		 var HeroDB = getHeroById(heroId);
		 repository.delete(HeroDB);
	}
	
	public HeroDTO saveHero(HeroVO hero) {
		var heroDB = repository.save(mapperUtil.getMapper().map(hero, Hero.class));
		return mapperUtil.getMapper().map(heroDB, HeroDTO.class);
	}
	
	public HeroDTO updateHero(HeroDTO hero, Long heroId) {
		var HeroDB = getHeroById(heroId);
		HeroDB.setName(hero.getName());
		return mapperUtil.getMapper().map(repository.save(HeroDB), HeroDTO.class);
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
