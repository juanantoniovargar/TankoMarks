package com.tankomarks.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Tomo;

public interface TomoRepository extends JpaRepository<Tomo, Integer> {
	
	@Query(value="SELECT * FROM tomo WHERE manga_id_manga = :manga", nativeQuery=true)
	List<Tomo> mostrarTomos(@Param("manga") int manga);

}
