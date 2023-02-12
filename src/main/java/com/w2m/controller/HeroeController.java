package com.w2m.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.dto.HeroeDTO;

@RestController("/heroes")
public class HeroeController {
	
	@GetMapping("/{id}")
	public ResponseEntity<HeroeDTO> getHeroeById(@PathVariable Integer id ){
		
		return new ResponseEntity<HeroeDTO>(null, HttpStatus.OK);
		
	}

}
