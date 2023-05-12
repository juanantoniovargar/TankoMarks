package com.tankomarks.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.tankomarks.demo.dto.RegistroDto;
import com.tankomarks.demo.model.Usuario;

public interface UsuarioService extends UserDetailsService{

	public Usuario guardar(RegistroDto registroDto) throws ConstraintViolationException;
	
	public List<Usuario> listarUsuarios();
	
	Optional<Usuario> findById(int id_usuario);
		
}
