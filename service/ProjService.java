package com.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.entities.Proj;
import com.proj.repository.ProjRepository;

@Service
public class ProjService {
	
	private final ProjRepository projRepo;
	
	@Autowired
    public ProjService(ProjRepository projRepo) {
        this.projRepo = projRepo;
    }

	public List<Proj> buscaTodosProj(){
        return projRepo.findAll();
    }

    public Proj buscaProjId(Long id) {
        Optional <Proj> Proj = projRepo.findById(id);
        return Proj.orElse(null);
    }
    
    public Proj salvaProj (Proj proj) {
        return projRepo.save(proj);
    }

    public Proj alterarProj(Long id, Proj alterarProj) {
        Optional <Proj> existeProj = projRepo.findById(id);
        if (existeProj.isPresent()) {
            alterarProj.setId(id);
            return projRepo.save(alterarProj);
        }
        return null;
    }
    
    public boolean apagarProj (Long id) {
        Optional <Proj> existeProj = projRepo.findById(id);
        if (existeProj.isPresent()) {
        	projRepo.deleteById(id);
            return true;
        }
        return false;
    }
}