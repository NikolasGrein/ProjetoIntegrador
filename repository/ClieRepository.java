package com.clie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clie.entities.Clie;

public interface ClieRepository extends JpaRepository<Clie, Long> {
	
}
