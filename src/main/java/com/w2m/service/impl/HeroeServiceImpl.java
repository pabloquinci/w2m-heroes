package com.w2m.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.w2m.dto.CrearHeroeRequestDTO;
import com.w2m.dto.HeroeResponseDTO;
import com.w2m.dto.HeroesResponseDTO;
import com.w2m.dto.ModificarHeroeRequestDTO;
import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.exception.HeroeYaExistenteException;
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
	public Optional<HeroeResponseDTO> getHeroeById(Long id) {
		Optional <Heroe> heroe= heroeRepository.findById(id);

		if(heroe.isEmpty()){
			throw new HeroeNoEncontradoException(ResponseDefault
					.builder()
					.date(LocalDateTime.now())
					.mensaje("Heroe No Encontrado...")
					.build());
		}

		HeroeResponseDTO heroeResponseDTO = HeroeResponseDTO
				.builder()
				.nombre(heroe.get().getNombre())
				.id(heroe.get().getId())
				.build();

		return Optional.of(heroeResponseDTO);
	}


	@Override
	public Optional<HeroesResponseDTO> getAll() {
		List<Heroe> heroes=heroeRepository.findAll();
		List<HeroeResponseDTO> listaHeroesDTO= new ArrayList<>();

		heroes.stream().forEach(heroe -> {
			HeroeResponseDTO heroeResponseDTO = modelMapper.map(heroe, HeroeResponseDTO.class);
			listaHeroesDTO.add(heroeResponseDTO);
		});

		HeroesResponseDTO heroesResponseDTO = HeroesResponseDTO.builder().heroes(listaHeroesDTO).build();
		return Optional.of(heroesResponseDTO);
	}

	@Override
	public Optional<HeroesResponseDTO> getHeroesByNombre(String nombre) {
		Optional<List<Heroe>> heroes= heroeRepository.buscarPorNombre(nombre);
		List<HeroeResponseDTO> listaHeroesDTO= new ArrayList<>();

		if(heroes.isEmpty() || heroes.get().size()==0){
			throw new HeroeNoEncontradoException(ResponseDefault
					.builder()
					.date(LocalDateTime.now())
					.mensaje("Heroes No Encontrados con ese parametro de nombre...")
					.build());
		}

		heroes.get().stream().forEach(heroe -> {
			HeroeResponseDTO heroeResponseDTO = modelMapper.map(heroe, HeroeResponseDTO.class);
			listaHeroesDTO.add(heroeResponseDTO);
		});
		HeroesResponseDTO heroesResponseDTO = HeroesResponseDTO.builder().heroes(listaHeroesDTO).build();
		return Optional.of(heroesResponseDTO);
	}

	@Override
	public void crearHeroe(CrearHeroeRequestDTO heroe) {
		Optional<Heroe> heroeBuscado= heroeRepository.findByNombre(heroe.getNombre());
		if(heroeBuscado.isPresent()){
			throw new HeroeYaExistenteException(ResponseDefault
					.builder()
					.mensaje("El heroe que está intentando crear ya existe")
					.date(LocalDateTime.now())
					.build());
		}
		Heroe heroeNuevo=Heroe.builder().nombre(heroe.getNombre()).build();
		Heroe h1=heroeRepository.saveAndFlush(heroeNuevo);
	}

	@Override
	public void modificarHeore(ModificarHeroeRequestDTO heroe) {
		Optional <Heroe> heroeBuscado= heroeRepository.findById(heroe.getHeroeId());
		if(!heroeBuscado.isPresent()){
			throw new HeroeNoEncontradoException(ResponseDefault
					.builder()
					.mensaje("El heroe que está intentando modificar no existe")
					.date(LocalDateTime.now())
					.build());
		}

		Heroe heroeModificado= Heroe.builder()
				.nombre(heroe.getNombre())
				.id(heroeBuscado.get().getId())
				.build();

		heroeRepository.save(heroeModificado);


	}
}
