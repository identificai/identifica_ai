package com.generation.identifica_ai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.identifica_ai.model.Agendamentos;

public interface AgendamentosRepository extends JpaRepository<Agendamentos, Long> {
	

	public List<Agendamentos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
