package com.es.mindata.heroeschallenge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.mindata.heroeschallenge.controller.response.BasicResponse;
import com.es.mindata.heroeschallenge.controller.response.HeroResponse;
import com.es.mindata.heroeschallenge.dto.HeroDTO;
import com.es.mindata.heroeschallenge.dto.HeroFilterDTO;
import com.es.mindata.heroeschallenge.service.impl.HeroServiceImpl;
import com.es.mindata.heroeschallenge.vo.HeroVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController("heroes")
@Api(value = "Heroes Service")
@RequestMapping("/heroes")
@RequiredArgsConstructor
public class HeroController {

	private final HeroServiceImpl service;
	
    @ApiOperation(
            value = "Service that return a Hero",
            notes = "This service return a Hero by the ID",
            nickname = "findHeroById",
            response = HeroDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = HeroDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = HeroDTO.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, 
    					example = "Bearer access_token")
    @GetMapping(
            value = "/find-hero-by-id/{id}",
            produces = { "application/json" }
    )
	public HeroDTO findHeroById(@PathVariable("id") Long id) {
		return service.findHeroById(id);
	}

    @ApiOperation(
            value = "Service that returns all heroes",
            notes = "This service returns all heroes load",
            nickname = "findAllHeroes",
            response = HeroDTO.class, 
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = HeroDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = HeroDTO.class, responseContainer = "List") })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @GetMapping(
            value = "/find-all-heroes",
            produces = { "application/json" }
    )
    public List<HeroDTO> findAllHeroes(){
        return service.findAllHeroes();
    }

    @ApiOperation(
            value = "This service save a hero",
            notes = "Service that return responseHero with saved object",
            nickname = "saveHero",
            response = HeroResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = HeroResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = HeroResponse.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/save-hero",
            produces = { "application/json" }
    )
    public HeroResponse saveHero(@RequestBody HeroVO hero){
        return new HeroResponse(service.saveHero(hero), Boolean.FALSE, "Successfully saved");
    }
    
    @ApiOperation(
            value = "This service update a hero",
            notes = "Update a hero, if it doesn't find it throw an exception",
            nickname = "updateHero",
            response = HeroResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = HeroResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = HeroResponse.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PutMapping(
            value = "/update-hero/{id}",
            produces = { "application/json" }
    )
    public HeroResponse updateHero(@RequestBody HeroDTO hero, @PathVariable("id") Long heroId){
        return new HeroResponse(service.updateHero(hero, heroId), Boolean.FALSE, "Successfully updated");
    }
    
    @ApiOperation(
            value = "This service delete a hero",
            notes = "Delete a hero, if it doesn't find it throw an exception",
            nickname = "deleteHeroById",
            response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = BasicResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = BasicResponse.class) })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @DeleteMapping(
            value = "/delete-hero/{id}",
            produces = { "application/json" }
    )
    public BasicResponse deleteHeroById(@PathVariable("id") Long heroId){
    	service.deleteHeroById(heroId);
        return new BasicResponse("Successfully deleted", Boolean.FALSE);
    }
    
    @ApiOperation(
            value = "Service that returns heroes filter by name",
            notes = "This service returns heroes filter by name",
            nickname = "searchHeroByName",
            response = HeroDTO.class, 
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = HeroDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = HeroDTO.class, responseContainer = "List") })
    @ApiImplicitParam(name = "Authorization",required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @PostMapping(
            value = "/search-hero-by-name",
            produces = { "application/json" }
    )
    public List<HeroDTO> searchHeroByName(@RequestBody HeroFilterDTO filter){
        return service.searchHeroByName(filter);
    }
}
