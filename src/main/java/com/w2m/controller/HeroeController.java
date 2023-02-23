package com.w2m.controller;

import com.w2m.dto.*;
import com.w2m.logging.LogExecutionTime;
import com.w2m.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/heroes")
public class HeroeController {

	@Autowired
	HeroeService heroeService;


	@GetMapping("")
	@LogExecutionTime
	public ResponseEntity<HeroesResponseDTO> getAll() {

		Optional<HeroesResponseDTO> heroesDTO = heroeService.getAll();

		if (heroesDTO.isPresent()){
			return new ResponseEntity<>(heroesDTO.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@GetMapping("/getHeroeById")
	@LogExecutionTime
	@ResponseBody
	public ResponseEntity<HeroeResponseDTO> getById(@RequestParam(value = "heroeId") Long id) {

		Optional<HeroeResponseDTO> heroeDTO = heroeService.getHeroeById(id);

		if(heroeDTO.isPresent()){
			return new ResponseEntity<HeroeResponseDTO>(heroeDTO.get(), HttpStatus.OK);

		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@GetMapping("/getHeroesByNombre")
	@LogExecutionTime
	@ResponseBody
	public ResponseEntity<HeroesResponseDTO> getHeroesByNombre(@RequestParam(value = "nombre") String nombre) {

		Optional<HeroesResponseDTO> heroesDTO = heroeService.getHeroesByNombre(nombre);

		if(heroesDTO.isPresent()){
			return new ResponseEntity<HeroesResponseDTO>(heroesDTO.get(), HttpStatus.OK);

		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@PostMapping("/crearHeroe")
	@LogExecutionTime
	@ResponseBody
	public ResponseEntity<OperacionHeroeResponseDTO> crearHeroe(@Validated @RequestBody CrearHeroeRequestDTO crearHeroeRequest){

		OperacionHeroeResponseDTO response = OperacionHeroeResponseDTO.builder().mensaje("Heroe Creado").build();

		heroeService.crearHeroe(crearHeroeRequest);

		return new ResponseEntity<OperacionHeroeResponseDTO>(response, HttpStatus.OK);

	}

	@PutMapping("/modificarHeroe")
	@LogExecutionTime
	@ResponseBody
	public ResponseEntity<OperacionHeroeResponseDTO> modificarHeroe(@Validated @RequestBody ModificarHeroeRequestDTO modificarHeroeRequest) {
		OperacionHeroeResponseDTO response = OperacionHeroeResponseDTO.builder().mensaje("Heroe Modificado").build();

		heroeService.modificarHeore(modificarHeroeRequest);

		return new ResponseEntity<OperacionHeroeResponseDTO>(response, HttpStatus.OK);

	}

	@DeleteMapping("/eliminarHeroe")
	@LogExecutionTime
	@ResponseBody
	public ResponseEntity<OperacionHeroeResponseDTO> eliminarHeroe(@Validated @RequestBody EliminarHeroeRequestDTO eliminarRequest) {

	OperacionHeroeResponseDTO response = OperacionHeroeResponseDTO.builder().mensaje("Heroe Eliminado").build();

	heroeService.eliminarHeroe(eliminarRequest);

	return new ResponseEntity<OperacionHeroeResponseDTO>(response, HttpStatus.OK);

	}
}
