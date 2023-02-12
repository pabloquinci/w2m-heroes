package com.w2m.service.impl;

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
	@Override
	public Optional<Heroe> getHeroeById(Integer id) {
		// TODO Auto-generated method stub
		return heroeRepository.findById(id);
	}

}
