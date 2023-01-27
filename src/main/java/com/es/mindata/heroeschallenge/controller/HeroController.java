package com.es.mindata.heroeschallenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.es.mindata.heroeschallenge.controller.response.BasicResponse;
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
    
    public HeroResponse updateHero(@RequestBody HeroDTO hero, @PathVariable("id") Long heroId){
        return new HeroResponse(service.updateHero(hero, heroId), Boolean.FALSE, "Successfully updated");
    }
    
    public BasicResponse deleteHeroById(@PathVariable("id") Long heroId){
    	service.deleteHeroById(heroId);
        return new BasicResponse("Successfully deleted", Boolean.FALSE);
    }
    
    public List<HeroDTO> searchHeroByName(@RequestBody String name){
        return service.searchHeroByName(name);
    }
}
