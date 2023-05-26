package com.tankomarks.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Capitulo;

public interface CapituloRepository extends JpaRepository<Capitulo, Integer> {
	
	@Query(value="SELECT * FROM capitulo WHERE tomo_id_tomo = :tomo", nativeQuery=true)
	List<Capitulo> mostrarCapitulos(@Param("tomo") int tomo);
	
	@Query(value="SELECT id_capitulo, tomo_id_tomo FROM capitulo WHERE tomo_id_tomo = :tomo LIMIT 1", nativeQuery=true)
	List<Capitulo> mostrarImagenTomoCapitulos(@Param("tomo") int tomo);

}
