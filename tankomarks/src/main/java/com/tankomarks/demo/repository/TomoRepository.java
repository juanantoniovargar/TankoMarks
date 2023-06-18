package com.tankomarks.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Tomo;

public interface TomoRepository extends JpaRepository<Tomo, Integer> {
	
	@Query(value="SELECT * FROM tomo WHERE manga_id_manga = :manga", nativeQuery=true)
	List<Tomo> mostrarTomos(@Param("manga") int manga);
	
	@Query(value="SELECT id_tomo, manga_id_manga FROM tomo WHERE manga_id_manga = :manga LIMIT 1", nativeQuery=true)
	List<Tomo> mostrarNombreMangaTomos(@Param("manga") int manga);
	
	@Query(value="SELECT * FROM tomo WHERE id_tomo = :id_tomo", nativeQuery=true)
	Tomo VerTomo(@Param("id_tomo") int id_tomo);
	
	@Query(value="SELECT manga_id_manga FROM tomo WHERE id_tomo = :id_tomo LIMIT 1", nativeQuery=true)
	int getMangaPorId_tomo(@Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO tomo (numero, enlacefoto, manga_id_manga) VALUES (:numero, :enlacefoto, :manga)", nativeQuery=true)
    int guardarTomo(@Param("numero") int numero, @Param("enlacefoto") String enlacefoto, @Param("manga") int manga);
	
	@Modifying
    @Transactional
    @Query(value="UPDATE tomo SET numero = :numero, enlacefoto = :enlacefoto WHERE id_tomo = :id_tomo", nativeQuery=true)
    int actualizarTomo(@Param("numero") int numero, @Param("enlacefoto") String enlacefoto, @Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM tomo WHERE id_tomo = :id_tomo", nativeQuery=true)
    int eliminarTomo(@Param("id_tomo") int id_tomo);
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM tomo WHERE manga_id_manga = :id_manga", nativeQuery=true)
    int eliminarTomoPorManga(@Param("id_manga") int id_manga);

}
