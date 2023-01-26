package com.es.mindata.heroeschallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.es.mindata.heroeschallenge.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>{

	@Query("SELECT h FROM Hero h WHERE UPPER(h.name) like UPPER('%:name%')")
	List<Hero> searchHeroByName(String name);

}
