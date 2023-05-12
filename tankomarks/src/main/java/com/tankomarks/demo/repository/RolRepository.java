package com.tankomarks.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankomarks.demo.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	
	public Rol findRolByNombre(String nombre);

}
