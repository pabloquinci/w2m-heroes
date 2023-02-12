package com.w2m.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;

public interface HeroeService {
	
	public Optional<Heroe> getHeroeById(Integer id);

}
