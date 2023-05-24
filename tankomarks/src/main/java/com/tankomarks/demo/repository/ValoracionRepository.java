package com.tankomarks.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
	
	@Query(value="SELECT * FROM valoracion WHERE usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Valoracion> mostrarValoraciones(@Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT v.* "
			+ "FROM valoracion v "
			+ "JOIN capitulo c ON v.capitulo_id_capitulo = c.id_capitulo "
			+ "WHERE c.nombre LIKE %:busqueda% AND v.usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Valoracion> buscarValoraciones(@Param("busqueda") String busqueda, @Param("id_usuario") int id_usuario);

}
