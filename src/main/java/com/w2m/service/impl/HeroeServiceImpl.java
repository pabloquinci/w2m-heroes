package com.w2m.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.HeroeService;

@Service
public class HeroeServiceImpl implements HeroeService {

	@Autowired
	private HeroeRepository heroeRepository;


	@Autowired
	public HeroeServiceImpl(HeroeRepository heroeRepository){
		this.heroeRepository=heroeRepository;

	}
	@Override
	public Optional<Heroe> getHeroeById(Integer id) {
		return heroeRepository.findById(id);
	}

	@Override
	public Optional<List<Heroe>> getAll() {
		return Optional.of(heroeRepository.findAll());
	}

}
