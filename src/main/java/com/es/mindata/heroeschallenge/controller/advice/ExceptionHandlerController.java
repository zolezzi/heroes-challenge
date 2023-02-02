package com.es.mindata.heroeschallenge.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.es.mindata.heroeschallenge.controller.response.BasicResponse;
import com.es.mindata.heroeschallenge.exception.HeroNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HeroNotFoundException.class)
	public ResponseEntity<BasicResponse> handleNotFoundException(Exception e){
		var basicResponse = new BasicResponse("Not Found", Boolean.TRUE);
		return new ResponseEntity<>(basicResponse, HttpStatus.NOT_FOUND);
	}
}
