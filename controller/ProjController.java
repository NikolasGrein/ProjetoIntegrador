package com.proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entities.Proj;
import com.proj.service.ProjService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Projeto", description = "API REST DE PROJETOS")
@RestController
@RequestMapping("/projeto")
public class ProjController {
	
	private final ProjService projServ;
	
	@Autowired
    public ProjController(ProjService projService) {
        this.projServ = projService;
    }

	@GetMapping("/{id}")
	@Operation(summary = "Localiza projeto por ID")
    public ResponseEntity<Proj> buscaProjControlId(@PathVariable Long id) {
		Proj proj = projServ.buscaProjId(id); 
        if (proj != null) {
            return ResponseEntity.ok(proj);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping("/")
    @Operation(summary = "Apresenta todos os projetos")
    public ResponseEntity<List<Proj>> buscaTodosProjControl() { 
        List<Proj> proj = projServ.buscaTodosProj();
        return ResponseEntity.ok(proj);

	}
	
	@PostMapping("/")
    public ResponseEntity<Proj> salvaProjControl(@RequestBody Proj proj) { 
		Proj salvaProj = projServ.salvaProj(proj);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaProj);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um projeto")
    public ResponseEntity<Proj> alteraProjControl(@PathVariable Long id, @RequestBody Proj proj) { 
    	Proj alteraProj = projServ.alterarProj(id, proj);
        if (alteraProj != null) {
            return ResponseEntity.ok(proj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um projeto")
    public ResponseEntity<String> apagaProjControl(@PathVariable Long id) { 
        boolean apagar = projServ.apagarProj(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}