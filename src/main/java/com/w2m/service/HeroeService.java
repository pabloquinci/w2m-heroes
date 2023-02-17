package com.w2m.service;

import java.util.List;
import java.util.Optional;

import com.w2m.dto.HeroeDTO;
import com.w2m.dto.HeroesDTO;
import com.w2m.model.Heroe;

public interface HeroeService {
	
	public Optional<Heroe> getHeroeById(Integer id);

	public Optional<HeroesDTO> getAll();

}
