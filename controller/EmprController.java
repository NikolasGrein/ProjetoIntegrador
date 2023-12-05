package com.empr.controller;

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

import com.empr.entities.Empr;
import com.empr.service.EmprService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Empresa", description = "API REST DE EMPRESAS")
@RestController
@RequestMapping("/empresa")
public class EmprController {
	
	private final EmprService emprServ;
	
	@Autowired
    public EmprController(EmprService emprService) {
        this.emprServ = emprService;
    }

	@GetMapping("/{id}")
	@Operation(summary = "Localiza empresa por ID")
    public ResponseEntity<Empr> buscaEmprControlId(@PathVariable Long id) {
		Empr empr = emprServ.buscaEmprId(id); 
        if (empr != null) {
            return ResponseEntity.ok(empr);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping("/")
    @Operation(summary = "Apresenta todas as empresas")
    public ResponseEntity<List<Empr>> buscaTodosEmprControl() { 
        List<Empr> empr = emprServ.buscaTodosEmpr();
        return ResponseEntity.ok(empr);

	}
	
	@PostMapping("/")
    public ResponseEntity<Empr> salvaEmprControl(@RequestBody Empr empr) { 
		Empr salvaEmpr = emprServ.salvaEmpr(empr);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaEmpr);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera uma empresa")
    public ResponseEntity<Empr> alteraProjControl(@PathVariable Long id, @RequestBody Empr empr) { 
    	Empr alteraEmpr = emprServ.alterarEmpr(id, empr);
        if (alteraEmpr != null) {
            return ResponseEntity.ok(empr);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma empresa")
    public ResponseEntity<String> apagaEmprControl(@PathVariable Long id) { 
        boolean apagar = emprServ.apagarEmpr(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}