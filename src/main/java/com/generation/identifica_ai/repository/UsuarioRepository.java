package com.generation.identifica_ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.identifica_ai.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{

	public Optional<Usuarios> findByUsuario(String usuario);
	
}