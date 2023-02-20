package com.w2m.service;

import java.util.Optional;

import com.w2m.dto.CrearHeroeRequestDTO;
import com.w2m.dto.HeroeResponseDTO;
import com.w2m.dto.HeroesResponseDTO;
import com.w2m.dto.ModificarHeroeRequestDTO;

public interface HeroeService {

	public Optional<HeroesResponseDTO> getAll();

	public Optional<HeroeResponseDTO> getHeroeById(Long id);

	public Optional<HeroesResponseDTO> getHeroesByNombre(String nombre);
	public void crearHeroe(CrearHeroeRequestDTO heroe);

	public void modificarHeore(ModificarHeroeRequestDTO heroe);


}
