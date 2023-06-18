package com.tankomarks.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByEmail(String email);
	
	@Query(value="SELECT id_usuario FROM usuario WHERE email = :email", nativeQuery=true)
    int getId_usuario(@Param("email") String email);
	
	@Modifying
    @Transactional    
    @Query(value="UPDATE usuario SET nombre = :nombre WHERE email = :email", nativeQuery=true)
    int actualizarPerfil(@Param("nombre") String nombre, @Param("email") String email);
	
}
