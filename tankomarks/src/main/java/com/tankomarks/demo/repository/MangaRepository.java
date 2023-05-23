package com.tankomarks.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
	
	@Query(value="SELECT * FROM manga", nativeQuery=true)
	List<Manga> mostrarMangas();
	
	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda%", nativeQuery=true)
	List<Manga> buscarMangas(@Param("busqueda") String busqueda);
	
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

}
