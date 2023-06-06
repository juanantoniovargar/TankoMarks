package com.tankomarks.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
	
	@Query(value="SELECT * FROM manga WHERE usuario_id_usuario IS NULL", nativeQuery=true)
	List<Manga> mostrarMangas();
	
	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda% AND usuario_id_usuario IS NULL", nativeQuery=true)
	List<Manga> buscarMangas(@Param("busqueda") String busqueda);

	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda% AND usuario_id_usuario IS NULL AND demografia_id_demografia = :demografia", nativeQuery=true)
	List<Manga> buscarMangasFiltrados(@Param("busqueda") String busqueda, @Param("demografia") String demografia);
	
	@Query(value="SELECT m.* "
			+ "FROM manga m "
			+ "JOIN leyendo_manga l ON m.id_manga = l.manga_id_manga "
			+ "WHERE l.usuario_id_usuario = :id_usuario LIMIT 3", nativeQuery=true)
	List<Manga> mostrarMangasLeyendo(@Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT m.* "
			+ "FROM manga m "
			+ "JOIN leido_manga l ON m.id_manga = l.manga_id_manga "
			+ "WHERE l.usuario_id_usuario = :id_usuario LIMIT 4", nativeQuery=true)
	List<Manga> mostrarMangasLeido(@Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT * FROM manga WHERE id_manga = :id_manga", nativeQuery=true)
	Manga mostrarDetallesManga(@Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM manga WHERE id_manga = :id_manga", nativeQuery=true)
    int eliminarManga(@Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO leyendo_manga (usuario_id_usuario, manga_id_manga) VALUES (:id_usuario, :id_manga)", nativeQuery=true)
    int activarLeyendo(@Param("id_usuario") int id_usuario, @Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM leyendo_manga WHERE usuario_id_usuario = :id_usuario AND manga_id_manga = :id_manga", nativeQuery=true)
    int eliminarLeyendo(@Param("id_usuario") int id_usuario, @Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO leido_manga (usuario_id_usuario, manga_id_manga) VALUES (:id_usuario, :id_manga)", nativeQuery=true)
    int activarLeido(@Param("id_usuario") int id_usuario, @Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM leido_manga WHERE usuario_id_usuario = :id_usuario AND manga_id_manga = :id_manga", nativeQuery=true)
    int eliminarLeido(@Param("id_usuario") int id_usuario, @Param("id_manga") int id_manga);
	
	@Query(value="SELECT COUNT(*) AS count_leido_manga FROM leido_manga WHERE usuario_id_usuario = :id_usuario AND manga_id_manga = :id_manga", nativeQuery=true)
    int verificaLeido(@Param("id_usuario") int id_usuario, @Param("id_manga") int id_manga);
	
	@Query(value="SELECT COUNT(*) AS count_leyendo_manga FROM leyendo_manga WHERE usuario_id_usuario = :id_usuario AND manga_id_manga = :id_manga", nativeQuery=true)
    int verificaLeyendo(@Param("id_usuario") int id_usuario, @Param("id_manga") int id_manga);

}
