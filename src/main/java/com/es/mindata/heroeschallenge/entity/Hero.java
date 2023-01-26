package com.es.mindata.heroeschallenge.entity;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Hero {

	private Long id;
	private String name;

}
