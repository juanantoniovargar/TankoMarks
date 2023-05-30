package com.tankomarks.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Capitulo;

public interface CapituloRepository extends JpaRepository<Capitulo, Integer> {
	
	@Query(value="SELECT * FROM capitulo WHERE tomo_id_tomo = :tomo", nativeQuery=true)
	List<Capitulo> mostrarCapitulos(@Param("tomo") int tomo);
	
	@Query(value="SELECT id_capitulo, tomo_id_tomo FROM capitulo WHERE tomo_id_tomo = :tomo LIMIT 1", nativeQuery=true)
	List<Capitulo> mostrarImagenTomoCapitulos(@Param("tomo") int tomo);
	
	@Query(value="SELECT * FROM capitulo WHERE id_capitulo = :id_capitulo", nativeQuery=true)
	Capitulo mostrarDetallesCapitulo(@Param("id_capitulo") int id_capitulo);

	@Query(value="SELECT c.* "
			+ "FROM capitulo c "
			+ "JOIN leido_capitulo l ON c.id_capitulo = l.capitulo_id_capitulo "
			+ "WHERE l.usuario_id_usuario = :id_usuario AND l.capitulo_id_capitulo = :id_capitulo", nativeQuery=true)
	Capitulo verificarEstado(@Param("id_usuario") int id_usuario, @Param("id_capitulo") int id_capitulo);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO leido_capitulo (usuario_id_usuario, capitulo_id_capitulo) VALUES (:id_usuario, :id_capitulo)", nativeQuery=true)
    int activarLeido(@Param("id_usuario") int id_usuario, @Param("id_capitulo") int id_capitulo);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM leido_capitulo WHERE usuario_id_usuario = :id_usuario AND capitulo_id_capitulo = :id_capitulo", nativeQuery=true)
    int eliminarLeido(@Param("id_usuario") int id_usuario, @Param("id_capitulo") int id_capitulo);

}
