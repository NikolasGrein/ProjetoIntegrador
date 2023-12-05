package com.clie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clie.entities.Clie;
import com.clie.repository.ClieRepository;

@Service
public class ClieService {
	
	private final ClieRepository clieRepo;
	
	@Autowired
    public ClieService(ClieRepository clieRepo) {
        this.clieRepo = clieRepo;
    }

	public List<Clie> buscaTodosClie(){
        return clieRepo.findAll();
    }

    public Clie buscaClieId(Long id) {
        Optional <Clie> Clie = clieRepo.findById(id);
        return Clie.orElse(null);
    }
    
    public Clie salvaClie (Clie clie) {
        return clieRepo.save(clie);
    }

    public Clie alterarClie(Long id, Clie alterarClie) {
        Optional <Clie> existeClie = clieRepo.findById(id);
        if (existeClie.isPresent()) {
            alterarClie.setId(id);
            return clieRepo.save(alterarClie);
        }
        return null;
    }
    
    public boolean apagarClie (Long id) {
        Optional <Clie> existeClie = clieRepo.findById(id);
        if (existeClie.isPresent()) {
        	clieRepo.deleteById(id);
            return true;
        }
        return false;
    }
}