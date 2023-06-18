package com.tankomarks.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tankomarks.demo.model.Demografia;

public interface DemografiaRepository extends JpaRepository<Demografia, Integer> {
	
	@Query(value="SELECT * FROM demografia WHERE id_demografia = :demografia", nativeQuery=true)
	Demografia convierteDemografia(@Param("demografia") String demografia);

}
