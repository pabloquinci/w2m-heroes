package com.w2m.controller;

import com.w2m.exception.MandatoryParamsException;
import com.w2m.exception.ResponseDefault;
import com.w2m.model.Heroe;
import com.w2m.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<HeroeDTO>> getAll() throws Exception {

		Optional<List<Heroe>> heroes=heroeService.getAll();

		List<HeroeDTO> heroesDTO= new ArrayList<>();

		heroes.get().forEach(p->heroesDTO.add(new HeroeDTO(p.getId(), p.getNombre())));

		return new ResponseEntity<>(heroesDTO,HttpStatus.OK);

	}

}
