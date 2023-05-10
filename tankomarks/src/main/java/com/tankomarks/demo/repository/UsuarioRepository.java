package com.tankomarks.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tankomarks.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByEmail(String email);

}
