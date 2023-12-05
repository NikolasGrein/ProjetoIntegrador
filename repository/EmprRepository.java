package com.empr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empr.entities.Empr;

public interface EmprRepository extends JpaRepository<Empr, Long>{

}
