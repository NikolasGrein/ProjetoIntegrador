package com.empr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empr.entities.Empr;
import com.empr.repository.EmprRepository;

@Service
public class EmprService {
	
	private final EmprRepository emprRepo;
	
	@Autowired
    public EmprService(EmprRepository emprRepo) {
        this.emprRepo = emprRepo;
    }

	public List<Empr> buscaTodosEmpr(){
        return emprRepo.findAll();
    }

    public Empr buscaEmprId(Long id) {
        Optional <Empr> Empr = emprRepo.findById(id);
        return Empr.orElse(null);
    }
    
    public Empr salvaEmpr (Empr empr) {
        return emprRepo.save(empr);
    }

    public Empr alterarEmpr(Long id, Empr alterarEmpr) {
        Optional <Empr> existeEmpr = emprRepo.findById(id);
        if (existeEmpr.isPresent()) {
            alterarEmpr.setId(id);
            return emprRepo.save(alterarEmpr);
        }
        return null;
    }
    
    public boolean apagarEmpr (Long id) {
        Optional <Empr> existeEmpr = emprRepo.findById(id);
        if (existeEmpr.isPresent()) {
        	emprRepo.deleteById(id);
            return true;
        }
        return false;
    }
}