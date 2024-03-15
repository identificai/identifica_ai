package com.generation.identifica_ai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.identifica_ai.model.Agendamentos;
import com.generation.identifica_ai.model.Usuarios;

public interface AgendamentosRepository extends JpaRepository<Agendamentos, Long> {
	

	public List<Agendamentos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	 List<Agendamentos> findAllByUsuarioId(Usuarios userId);
	 List<Agendamentos> findAllByUsuario(Usuarios usuario);

}
