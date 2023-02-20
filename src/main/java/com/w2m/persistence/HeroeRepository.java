package com.w2m.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2m.model.Heroe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long>{
	Optional<Heroe> findById(Long id);

	@Query("SELECT u FROM Heroe u WHERE LOWER(u.nombre) like %:nombre%")
	Optional<List<Heroe>> buscarPorNombre(@Param("nombre") String nombre);

	Optional<Heroe> findByNombre(String nombre);

	Optional<Heroe> findByIdAndNombre(Long id,String nombre);

}
