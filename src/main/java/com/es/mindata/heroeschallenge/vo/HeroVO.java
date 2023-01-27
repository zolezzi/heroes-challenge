package com.es.mindata.heroeschallenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HeroVO {

	@JsonProperty("name")
	private String name;
}
