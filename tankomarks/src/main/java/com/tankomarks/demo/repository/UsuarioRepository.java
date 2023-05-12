package com.tankomarks.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

//import com.tankomarks.demo.model.Rol;
import com.tankomarks.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByEmail(String email);
	
	// @Query(value = "SELECT nombre FROM rol where nombre = :nombre", nativeQuery = true)
	// public Rol findByNombre(String nombre);

}
