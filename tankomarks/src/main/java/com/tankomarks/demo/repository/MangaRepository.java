package com.tankomarks.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
	
	@Query(value="SELECT * FROM manga LIMIT 3", nativeQuery=true)
	List<Manga> mostrarMangas();
	
	@Query(value="SELECT * FROM manga WHERE nombre LIKE %:busqueda%", nativeQuery=true)
	List<Manga> buscarMangas(@Param("busqueda") String busqueda);
	
	@Query(value="SELECT manga.* "
			+ "FROM manga "
			+ "JOIN leyendo_manga ON manga.id_manga = leyendo_manga.manga_id_manga "
			+ "JOIN usuario ON leyendo_manga.usuario_id_usuario = usuario.id_usuario"
			+ "WHERE usuario.id_usuario = :id_usuario", nativeQuery=true)
	List<Manga> MostrarMangasLeyendo(@Param("id_usuario") int id_usuario);

}
