package com.w2m.controller;

import com.w2m.dto.*;
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
	public ResponseEntity<HeroesResponseDTO> getAll() throws Exception {

		Optional<HeroesResponseDTO> heroesDTO=heroeService.getAll();

		return new ResponseEntity<HeroesResponseDTO>(heroesDTO.get(),HttpStatus.OK);

	}

	@GetMapping("/getHeroeById")
	@ResponseBody
	public ResponseEntity<HeroeResponseDTO> getById(@RequestParam(value="heroeId") Long id) throws Exception {

		Optional<HeroeResponseDTO> heroeDTO=heroeService.getHeroeById(id);

		return new ResponseEntity<>(heroeDTO.get(),HttpStatus.OK);

	}

	@GetMapping("/getHeroesByNombre")
	@ResponseBody
	public ResponseEntity<HeroesResponseDTO> getHeroesByNombre(@RequestParam(value = "nombre") String nombre) throws Exception{

		Optional<HeroesResponseDTO> heroesDTO=heroeService.getHeroesByNombre(nombre);

		return new ResponseEntity<HeroesResponseDTO>(heroesDTO.get(),HttpStatus.OK);
	}

	@PostMapping("/crearHeroe")
	@ResponseBody
	public ResponseEntity<OperacionHeroeResponseDTO> crearHeroe(@Validated @RequestBody CrearHeroeRequestDTO crearHeroeRequest) throws Exception{

		OperacionHeroeResponseDTO response= OperacionHeroeResponseDTO.builder().mensaje("Heroe Creado").build();

		heroeService.crearHeroe(crearHeroeRequest);

		return new ResponseEntity<OperacionHeroeResponseDTO>(response,HttpStatus.OK);

	}

	@PutMapping("/modificarHeroe")
	@ResponseBody
	public ResponseEntity<OperacionHeroeResponseDTO> modificarHeroe(@Validated @RequestBody ModificarHeroeRequestDTO modificarHeroeRequest) throws Exception{
		OperacionHeroeResponseDTO response= OperacionHeroeResponseDTO.builder().mensaje("Heroe Modificado").build();

		heroeService.modificarHeore(modificarHeroeRequest);

		return new ResponseEntity<OperacionHeroeResponseDTO>(response,HttpStatus.OK);

	}



}
