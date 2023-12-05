package com.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.entities.Proj;

public interface ProjRepository extends JpaRepository<Proj, Long>{

}
