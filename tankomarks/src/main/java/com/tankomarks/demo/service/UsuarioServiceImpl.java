package com.tankomarks.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import java.util.ArrayList;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tankomarks.demo.dto.RegistroDto;
import com.tankomarks.demo.model.Usuario;
import com.tankomarks.demo.model.Rol;
import com.tankomarks.demo.repository.RolRepository;
import com.tankomarks.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository,
			BCryptPasswordEncoder passwordEncoder) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.rolRepository = rolRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Usuario guardar(RegistroDto registroDto) throws ConstraintViolationException {
		
		/*
		Usuario usuario = new Usuario(			
				registroDto.getNombre(),
				registroDto.getEmail(),
				passwordEncoder.encode(registroDto.getPassword()),
				Arrays.asList(new Rol("USER")));

		return usuarioRepository.save(usuario);
		*/
		
		Rol userRole = rolRepository.findRolByNombre("USER");
	    if (userRole == null) {
	        userRole = new Rol("USER");
	    }

	    Usuario usuario = new Usuario(			
	        registroDto.getNombre(),
	        registroDto.getEmail(),
	        passwordEncoder.encode(registroDto.getPassword()),
	        Arrays.asList(userRole));

	    return usuarioRepository.save(usuario);

	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Email o contraseña erroneos");		
		}
		
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRol()));
	}
	
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> rol){
		return rol.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findById(int id_usuario) {
		return usuarioRepository.findById(id_usuario);
	}		
}
