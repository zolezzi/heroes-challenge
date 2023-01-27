package com.es.mindata.heroeschallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "username", "password"})
public class UserDTO {
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
}
