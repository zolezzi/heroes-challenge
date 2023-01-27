package com.es.mindata.heroeschallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HeroDTO {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
}
