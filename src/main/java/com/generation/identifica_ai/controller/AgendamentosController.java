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

import com.generation.identifica_ai.model.Agendamentos;
import com.generation.identifica_ai.model.Servicos;
import com.generation.identifica_ai.repository.AgendamentosRepository;
import com.generation.identifica_ai.repository.CategoriasRepository;
import com.generation.identifica_ai.repository.ServicosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AgendamentosController {
	
	@Autowired
	private AgendamentosRepository agendamentosRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Agendamentos>> getAll(){
		return ResponseEntity.ok(agendamentosRepository.findAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Agendamentos> getById(@PathVariable Long id){
		return agendamentosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Agendamentos>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(agendamentosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
    public ResponseEntity<Agendamentos> post(@Valid @RequestBody Agendamentos agendamentos) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(agendamentosRepository.save(agendamentos));
      
        
    }
	
	@PutMapping
    public ResponseEntity<Agendamentos> put(@Valid @RequestBody Agendamentos agendamentos) {
        if (agendamentosRepository.existsById(agendamentos.getId())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(agendamentosRepository.save(agendamentos));
            
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Agendamentos> agendamentos = agendamentosRepository.findById(id);
		
		if(agendamentos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		agendamentosRepository.deleteById(id);				
	}

}
