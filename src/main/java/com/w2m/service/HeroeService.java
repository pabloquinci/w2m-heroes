package com.w2m.service;

import java.util.Optional;

import com.w2m.dto.*;

import javax.transaction.Transactional;

public interface HeroeService {

	public Optional<HeroesResponseDTO> getAll();

	public Optional<HeroeResponseDTO> getHeroeById(Long id);

	public Optional<HeroesResponseDTO> getHeroesByNombre(String nombre);
	public void crearHeroe(CrearHeroeRequestDTO heroe);

	@Transactional
	public void modificarHeore(ModificarHeroeRequestDTO heroe);

	@Transactional
	public void eliminarHeroe(EliminarHeroeRequestDTO heroe);


}
