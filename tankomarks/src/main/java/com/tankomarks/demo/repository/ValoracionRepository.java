package com.tankomarks.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
	
	@Query(value="SELECT * FROM valoracion WHERE favorito = 1 AND usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Valoracion> mostrarValoraciones(@Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT v.* "
			+ "FROM valoracion v "
			+ "JOIN capitulo c ON v.capitulo_id_capitulo = c.id_capitulo "
			+ "WHERE c.nombre LIKE %:busqueda%, favorito = 1 AND v.usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Valoracion> buscarValoraciones(@Param("busqueda") String busqueda, @Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT * FROM valoracion WHERE usuario_id_usuario = :id_usuario AND capitulo_id_capitulo = :id_capitulo", nativeQuery=true)
	Valoracion mostrarDetalleValoracion(@Param("id_usuario") int id_usuario, @Param("id_capitulo") int id_capitulo);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO valoracion (favorito, comentario, capitulo_id_capitulo, usuario_id_usuario) VALUES (:favorito, :comentario, :id_capitulo, :id_usuario)", nativeQuery=true)
    int crearValoracion(@Param("favorito") boolean favorito, @Param("comentario") String comentario, @Param("id_capitulo") int id_capitulo, @Param("id_usuario") int id_usuario);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE valoracion SET favorito = :favorito, comentario = :comentario WHERE capitulo_id_capitulo = :id_capitulo AND usuario_id_usuario = :id_usuario", nativeQuery=true)
    int modificarValoracion(@Param("favorito") boolean favorito, @Param("comentario") String comentario, @Param("id_capitulo") int id_capitulo, @Param("id_usuario") int id_usuario);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM valoracion WHERE favorito = 0 AND comentario = ''", nativeQuery=true)
    int eliminarValoracion();
    
}
