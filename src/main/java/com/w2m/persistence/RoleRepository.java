package com.w2m.persistence;

import java.util.Optional;

import com.w2m.model.ERole;
import com.w2m.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(ERole name);

}
