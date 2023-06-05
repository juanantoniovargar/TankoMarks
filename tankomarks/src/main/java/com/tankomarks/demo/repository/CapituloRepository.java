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

	@Query(value="SELECT COUNT(*) AS count_leyendo_tomo "
			+ "FROM leyendo_tomo lt "
			+ "JOIN tomo t ON lt.tomo_id_tomo = t.id_tomo "
			+ "JOIN capitulo c ON c.tomo_id_tomo = t.id_tomo "
			+ "JOIN leido_capitulo lc ON lc.capitulo_id_capitulo = c.id_capitulo "
			+ "WHERE lt.usuario_id_usuario = :id_usuario "
			+ "AND t.id_tomo = :id_tomo "
			+ "AND lt.tomo_id_tomo = t.id_tomo", nativeQuery=true)
    int verificaLeyendoTomo(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);
	
	/*@Query(value="SELECT COUNT(*) AS count_leyendo_tomo FROM leyendo_tomo WHERE usuario_id_usuario = :id_usuario AND tomo_id_tomo = :id_tomo", nativeQuery=true)
    int verificaLeyendoTomo(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);*/
	
	@Query(value="SELECT COUNT(*) AS count_leido_capitulo "
			+ "FROM capitulo c "
			+ "JOIN tomo t ON c.tomo_id_tomo = t.id_tomo "
			+ "LEFT JOIN leido_capitulo lc ON lc.capitulo_id_capitulo = c.id_capitulo "
			+ "WHERE lc.usuario_id_usuario = :id_usuario "
			+ "AND t.id_tomo = :id_tomo "
			+ "AND lc.capitulo_id_capitulo IS NOT NULL", nativeQuery=true)
    int numCapitulosLeidos(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);
	
	@Query(value="SELECT COUNT(*) AS total_capitulos FROM capitulo WHERE tomo_id_tomo = :id_tomo", nativeQuery=true)
    int totalCapitulosPorTomo(@Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO leido_tomo (usuario_id_usuario, tomo_id_tomo) VALUES (:id_usuario, :id_tomo)", nativeQuery=true)
    int activarLeidoTomo(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO leyendo_tomo (usuario_id_usuario, tomo_id_tomo) VALUES (:id_usuario, :id_tomo)", nativeQuery=true)
    int activarLeyendoTomo(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM leido_tomo WHERE usuario_id_usuario = :id_usuario AND tomo_id_tomo = :id_tomo", nativeQuery=true)
    int eliminarLeidoTomo(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM leyendo_tomo WHERE usuario_id_usuario = :id_usuario AND tomo_id_tomo = :id_tomo", nativeQuery=true)
    int eliminarLeyendoTomo(@Param("id_usuario") int id_usuario, @Param("id_tomo") int id_tomo);
	
}
