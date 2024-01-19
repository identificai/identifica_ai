package com.generation.identifica_ai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.identifica_ai.model.Servicos;

public interface ServicosRepository extends JpaRepository<Servicos, Long> {
	
	public List<Servicos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
