package com.generation.identifica_ai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.identifica_ai.model.Servicos;
import com.generation.identifica_ai.repository.CategoriasRepository;
import com.generation.identifica_ai.repository.ServicosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicosController {
	
	@Autowired
	private ServicosRepository servicosRepository;
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<Servicos>> getAll(){
		return ResponseEntity.ok(servicosRepository.findAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servicos> getById(@PathVariable Long id){
		return servicosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Servicos>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(servicosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
    public ResponseEntity<Servicos> post(@Valid @RequestBody Servicos servicos) {
        if (categoriasRepository.existsById(servicos.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(servicosRepository.save(servicos));
        
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
        
    }
	
	@PutMapping
    public ResponseEntity<Servicos> put(@Valid @RequestBody Servicos servicos) {
        if (servicosRepository.existsById(servicos.getId())) {
            if (servicosRepository.existsById(servicos.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(servicosRepository.save(servicos));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Servicos> produto = servicosRepository.findById(id);
		
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		servicosRepository.deleteById(id);				
	}

}
