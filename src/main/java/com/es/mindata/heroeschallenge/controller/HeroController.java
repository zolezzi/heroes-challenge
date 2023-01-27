package com.es.mindata.heroeschallenge.controller;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeroController {

	private final HeroServiceImpl service;
	
	public HeroDTO findHeroById(Long id) {
		// TODO Auto-generated method stub
		return service.findHeroById(id);
	}

}
