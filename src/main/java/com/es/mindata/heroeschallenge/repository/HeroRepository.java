package com.es.mindata.heroeschallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.es.mindata.heroeschallenge.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>{

}
