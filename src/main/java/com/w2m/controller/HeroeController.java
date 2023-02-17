package com.w2m.controller;

import com.w2m.dto.HeroesDTO;
import com.w2m.exception.MandatoryParamsException;
import com.w2m.exception.ResponseDefault;
import com.w2m.model.Heroe;
import com.w2m.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.w2m.dto.HeroeDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/heroes")
public class HeroeController {

	@Autowired
	HeroeService heroeService;

	@GetMapping("")
	public ResponseEntity<HeroesDTO> getAll() throws Exception {

		Optional<HeroesDTO> heroesDTO=heroeService.getAll();

		return new ResponseEntity<>(heroesDTO.get(),HttpStatus.OK);

	}

	@GetMapping("/getHeroeById")
	@ResponseBody
	public ResponseEntity<HeroeDTO> getById(@RequestParam(value="heroeId") Long id) throws Exception {

		Optional<HeroeDTO> heroeDTO=heroeService.getHeroeById(id);

		return new ResponseEntity<>(heroeDTO.get(),HttpStatus.OK);

	}


}
