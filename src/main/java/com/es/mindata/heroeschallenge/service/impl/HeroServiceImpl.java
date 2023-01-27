package com.es.mindata.heroeschallenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.dto.HeroFilterDTO;
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
	
	@Cacheable("heroes")
	public HeroDTO findHeroById(Long heroId) {
		var heroDB = getHeroById(heroId);
		return mapperUtil.getMapper().map(heroDB, HeroDTO.class);
	}

	@Cacheable("heroes")
	public List<HeroDTO> findAllHeroes() {
		return  repository.findAll()
				.stream()
				.map((hero -> mapperUtil.getMapper().map(hero, HeroDTO.class)))
				.collect(Collectors.toList());
	}

	@CacheEvict(value="heroes", allEntries = true)
	public void deleteHeroById(Long heroId) {
		 var heroDB = getHeroById(heroId);
		 repository.delete(heroDB);
	}
	
	@Transactional
	@CacheEvict(value="heroes", allEntries = true)
	public HeroDTO saveHero(HeroVO hero) {
		var heroDB = repository.save(mapperUtil.getMapper().map(hero, Hero.class));
		return mapperUtil.getMapper().map(heroDB, HeroDTO.class);
	}
	
	@Transactional
	@CacheEvict(value="heroes", allEntries = true)
	public HeroDTO updateHero(HeroDTO hero, Long heroId) {
		var heroDB = getHeroById(heroId);
		heroDB.setName(hero.getName());
		return mapperUtil.getMapper().map(repository.save(heroDB), HeroDTO.class);
	}

	@Cacheable("heroes")
	public List<HeroDTO> searchHeroByName(HeroFilterDTO filter) {
		if(filter == null) {
			throw new HeroNotFoundException("Hero not found");
		}
		return repository.searchHeroByName(filter.getLikeName())
				.stream()
				.map((hero -> mapperUtil.getMapper().map(hero, HeroDTO.class)))
				.collect(Collectors.toList());
	}
	
	private Hero getHeroById(Long heroId) {
		var heroOpt = repository.findById(heroId);
		if(heroOpt.isEmpty()) {
			throw new HeroNotFoundException("Hero not found");
		}
		return heroOpt.get();
	}

}
