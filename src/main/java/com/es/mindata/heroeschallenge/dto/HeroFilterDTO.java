package com.es.mindata.heroeschallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroFilterDTO {
	
	@JsonProperty("name")
	private String name;

	public String getLikeName() {
		return "%"+ name +"%";
	}
}
