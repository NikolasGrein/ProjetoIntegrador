package com.clie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clie.entities.Clie;
import com.clie.service.ClieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente", description = "API REST DE CLIENTES")
@RestController
@RequestMapping("/cliente")
public class ClieController {
	
	private final ClieService clieServ;
	
	@Autowired
    public ClieController(ClieService clieService) {
        this.clieServ = clieService;
    }

	@GetMapping("/{id}")
	@Operation(summary = "Localiza cliente por ID")
    public ResponseEntity<Clie> buscaClieControlId(@PathVariable Long id) {
		Clie clie = clieServ.buscaClieId(id); 
        if (clie != null) {
            return ResponseEntity.ok(clie);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@GetMapping("/")
    @Operation(summary = "Apresenta todas os clientes")
    public ResponseEntity<List<Clie>> buscaTodosClieControl() { 
        List<Clie> clie = clieServ.buscaTodosClie();
        return ResponseEntity.ok(clie);

	}
	
	@PostMapping("/")
    public ResponseEntity<Clie> salvaClieControl(@RequestBody Clie clie) { 
		Clie salvaClie = clieServ.salvaClie(clie);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaClie);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um cliente")
    public ResponseEntity<Clie> alteraClieControl(@PathVariable Long id, @RequestBody Clie clie) { 
    	Clie alteraClie = clieServ.alterarClie(id, clie);
        if (alteraClie != null) {
            return ResponseEntity.ok(clie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um cliente")
    public ResponseEntity<String> apagaClieControl(@PathVariable Long id) { 
        boolean apagar = clieServ.apagarClie(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}