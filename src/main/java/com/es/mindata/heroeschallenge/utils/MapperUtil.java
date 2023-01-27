package com.es.mindata.heroeschallenge.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.entity.Hero;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.configure();
    }

    public ModelMapper getMapper() {
        return this.modelMapper;
    }
    
    private void configure() {
        // HeroDTO config
        this.modelMapper.typeMap(Hero.class, HeroDTO.class).addMappings(mapper -> {
            mapper.map(Hero::getId, HeroDTO::setId);
            mapper.map(Hero::getName, HeroDTO::setName);
        });
    }
}
