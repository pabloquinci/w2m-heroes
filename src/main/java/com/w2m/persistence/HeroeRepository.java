package com.w2m.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2m.model.Heroe;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long>{
	Optional<Heroe> findById(Long id);

}
