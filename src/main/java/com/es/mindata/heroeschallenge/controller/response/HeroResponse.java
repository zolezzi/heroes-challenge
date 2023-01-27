package com.es.mindata.heroeschallenge.controller.response;

import com.es.mindata.heroeschallenge.dto.HeroDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HeroResponse extends BasicResponse{

	private HeroDTO response;
	
	public HeroResponse(HeroDTO hero, Boolean error, String message){    
		super(message, error);
		response = hero;
	}
}
