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
import com.tankomarks.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario guardar(RegistroDto registroDto) throws ConstraintViolationException {
		
		/*
		Optional<Usuario> OptionalUserRole = usuarioRepository.findById(1);
		Optional<Collection<Rol>> userRole = OptionalUserRole.get().getRol().stream().findFirst());
	    
		Usuario usuario = new Usuario(			
							registroDto.getNombre(),
							registroDto.getEmail(),
							passwordEncoder.encode(registroDto.getPassword()),
							Arrays.asList(userRole));
		
		return usuarioRepository.save(usuario);
		
		Usuario usuario = new Usuario(			
		        		  	registroDto.getNombre(),
		        		  	registroDto.getEmail(),
		        		  	passwordEncoder.encode(registroDto.getPassword()),
		        		  	new ArrayList<>()); 

		Collection<Rol> roles = usuario.getRol();
		Rol userRole = roles.stream().findFirst().orElse(null);
		    
		if (userRole == null) {
		    userRole = new Rol("USER");
		    roles.add(userRole); // Se agrega el nuevo rol a la colección
		}
		    
		return usuarioRepository.save(usuario);
		*/
		
		Usuario usuario = new Usuario(			
				registroDto.getNombre(),
				registroDto.getEmail(),
				passwordEncoder.encode(registroDto.getPassword()),
				Arrays.asList(new Rol("USER")));

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
