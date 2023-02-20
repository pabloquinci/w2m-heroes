package com.w2m.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.w2m.dto.HeroeDTO;
import com.w2m.dto.HeroesDTO;
import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.exception.ResponseDefault;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.HeroeService;

@Service
public class HeroeServiceImpl implements HeroeService {

	//@Autowired
	private HeroeRepository heroeRepository;

	@Autowired
	ModelMapper modelMapper;


	@Autowired
	public HeroeServiceImpl(HeroeRepository heroeRepository){
		this.heroeRepository=heroeRepository;

	}
	@Override
	public Optional<HeroeDTO> getHeroeById(Long id) {
		Optional <Heroe> heroe= heroeRepository.findById(id);

		if(heroe.isEmpty()){
			throw new HeroeNoEncontradoException(ResponseDefault
					.builder()
					.date(LocalDateTime.now())
					.message("Heroe No Encontrado...")
					.build());
		}

		HeroeDTO heroeDTO= HeroeDTO
				.builder()
				.nombre(heroe.get().getNombre())
				.id(heroe.get().getId())
				.build();

		return Optional.of(heroeDTO);
	}


	@Override
	public Optional<HeroesDTO> getAll() {
		List<Heroe> heroes=heroeRepository.findAll();
		List<HeroeDTO> listaHeroesDTO= new ArrayList<>();

		heroes.stream().forEach(heroe -> {
			HeroeDTO heroeDTO= modelMapper.map(heroe, HeroeDTO.class);
			listaHeroesDTO.add(heroeDTO);
		});

		HeroesDTO heroesDTO= HeroesDTO.builder().heroes(listaHeroesDTO).build();
		return Optional.of(heroesDTO);
	}

	@Override
	public Optional<HeroesDTO> getHeroesByNombre(String nombre) {
		Optional<List<Heroe>> heroes= heroeRepository.findByNombre(nombre);
		List<HeroeDTO> listaHeroesDTO= new ArrayList<>();

		if(heroes.isEmpty() || heroes.get().size()==0){
			throw new HeroeNoEncontradoException(ResponseDefault
					.builder()
					.date(LocalDateTime.now())
					.message("Heroes No Encontrados con ese parametro de nombre...")
					.build());
		}

		heroes.get().stream().forEach(heroe -> {
			HeroeDTO heroeDTO= modelMapper.map(heroe, HeroeDTO.class);
			listaHeroesDTO.add(heroeDTO);
		});
		HeroesDTO heroesDTO= HeroesDTO.builder().heroes(listaHeroesDTO).build();
		return Optional.of(heroesDTO);
	}

}
