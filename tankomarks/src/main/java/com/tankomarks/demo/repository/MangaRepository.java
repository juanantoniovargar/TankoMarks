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
	
	@Query(value="SELECT * "
			+ "FROM manga "
			+ "WHERE id_manga IN (:id1, :id2, :id3) "
			+ "ORDER BY CASE id_manga "
			+ "    WHEN :id1 THEN 1 "
			+ "    WHEN :id2 THEN 2 "
			+ "    WHEN :id3 THEN 3 "
			+ "END", nativeQuery=true)
	List<Manga> mostrarMangasOrdenados(@Param("id1") String id1, @Param("id2") String id2, @Param("id3") String id3);
	
	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda% AND usuario_id_usuario IS NULL", nativeQuery=true)
	List<Manga> buscarMangasAdmin(@Param("busqueda") String busqueda);

	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda% AND usuario_id_usuario IS NULL AND demografia_id_demografia = :demografia", nativeQuery=true)
	List<Manga> buscarMangasFiltradosAdmin(@Param("busqueda") String busqueda, @Param("demografia") String demografia);
	
	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda% AND (usuario_id_usuario IS NULL OR usuario_id_usuario = :id_usuario)", nativeQuery=true)
	List<Manga> buscarMangas(@Param("busqueda") String busqueda, @Param("id_usuario") int id_usuario);

	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda% AND (usuario_id_usuario IS NULL OR usuario_id_usuario = :id_usuario) AND demografia_id_demografia = :demografia", nativeQuery=true)
	List<Manga> buscarMangasFiltrados(@Param("busqueda") String busqueda, @Param("demografia") String demografia, @Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT * FROM manga WHERE usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Manga> mostrarMangasPropios(@Param("id_usuario") int id_usuario);
	
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
	
	@Query(value="SELECT m.* "
			+ "FROM manga m "
			+ "JOIN leyendo_manga l ON m.id_manga = l.manga_id_manga "
			+ "WHERE l.usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Manga> mostrarTodosMangasLeyendo(@Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT m.* "
			+ "FROM manga m "
			+ "JOIN leido_manga l ON m.id_manga = l.manga_id_manga "
			+ "WHERE l.usuario_id_usuario = :id_usuario", nativeQuery=true)
	List<Manga> mostrarTodosMangasLeido(@Param("id_usuario") int id_usuario);
	
	@Query(value="SELECT * FROM manga WHERE id_manga = :id_manga", nativeQuery=true)
	Manga mostrarDetallesManga(@Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO manga (nombre, descripcion, enlacefoto, demografia_id_demografia) VALUES (:nombre, :descripcion, :enlacefoto, :demografia)", nativeQuery=true)
    int guardarManga(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("enlacefoto") String enlacefoto, @Param("demografia") int demografia);
	
	@Modifying
    @Transactional
    @Query(value="UPDATE manga SET nombre = :nombre, descripcion = :descripcion, enlacefoto = :enlacefoto, demografia_id_demografia = :demografia WHERE id_manga = :id_manga", nativeQuery=true)
    int actualizarManga(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("enlacefoto") String enlacefoto, @Param("demografia") int demografia, @Param("id_manga") int id_manga);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO manga (nombre, descripcion, enlacefoto, demografia_id_demografia, usuario_id_usuario) VALUES (:nombre, :descripcion, :enlacefoto, :demografia, :id_usuario)", nativeQuery=true)
    int guardarMangaPropio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("enlacefoto") String enlacefoto, @Param("demografia") int demografia, @Param("id_usuario") int id_usuario);
	
	@Modifying
    @Transactional
    @Query(value="UPDATE manga SET nombre = :nombre, descripcion = :descripcion, enlacefoto = :enlacefoto, demografia_id_demografia = :demografia, usuario_id_usuario = :id_usuario WHERE id_manga = :id_manga", nativeQuery=true)
    int actualizarMangaPropio(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("enlacefoto") String enlacefoto, @Param("demografia") int demografia, @Param("id_manga") int id_manga, @Param("id_usuario") int id_usuario);
	
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
