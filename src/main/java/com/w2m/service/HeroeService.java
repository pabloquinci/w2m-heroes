package com.w2m.service;

import java.util.Optional;

import com.w2m.dto.HeroeRequestDTO;
import com.w2m.dto.HeroeResponseDTO;
import com.w2m.dto.HeroesResponseDTO;

public interface HeroeService {

	public Optional<HeroesResponseDTO> getAll();

	public Optional<HeroeResponseDTO> getHeroeById(Long id);

	public Optional<HeroesResponseDTO> getHeroesByNombre(String nombre);
	public void crearHeroe(HeroeRequestDTO heroe);



}
