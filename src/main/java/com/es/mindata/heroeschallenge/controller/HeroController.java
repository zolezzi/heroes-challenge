package com.es.mindata.heroeschallenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.es.mindata.heroeschallenge.controller.response.HeroResponse;
import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;
import com.es.mindata.heroeschallenge.vo.HeroVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeroController {

	private final HeroServiceImpl service;
	
	public HeroDTO findHeroById(Long id) {
		return service.findHeroById(id);
	}

    public List<HeroDTO> findAllHeroes(){
        return service.findAllHeroes();
    }

    public HeroResponse saveHero(@RequestBody HeroVO hero){
        return new HeroResponse(service.saveHero(hero), Boolean.FALSE, "Successfully saved");
    }
}
