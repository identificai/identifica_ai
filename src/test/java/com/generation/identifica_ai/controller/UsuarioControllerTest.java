package com.generation.identifica_ai.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.identifica_ai.model.Usuarios;
import com.generation.identifica_ai.repository.UsuarioRepository;
import com.generation.identifica_ai.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start(){

		usuarioRepository.deleteAll();

		usuarioService.cadastrarUsuario(new Usuarios(0L, 
			"Root", "root@root.com", "rootroot", "-", "-"));

	}

	@Test
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmUsuario() {

		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(new Usuarios(0L, 
			"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "-", "-"));

		ResponseEntity<Usuarios> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuarios.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	
	}

	@Test
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuarios(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "-", "-"));

		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(new Usuarios(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "-", "-"));

		ResponseEntity<Usuarios> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuarios.class);

		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Atualizar um Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuarios> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuarios(0L, 
			"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "-", "-"));

		Usuarios usuarioUpdate = new Usuarios(usuarioCadastrado.get().getId(), 
			"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123" , "-", "-");
		
		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(usuarioUpdate);

		ResponseEntity<Usuarios> corpoResposta = testRestTemplate
			.withBasicAuth("root@root.com", "rootroot")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuarios.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		
	}

	@Test
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		usuarioService.cadastrarUsuario(new Usuarios(0L, 
			"Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123", "-", "-"));
		
		usuarioService.cadastrarUsuario(new Usuarios(0L, 
			"Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123", "-", "-"));

		ResponseEntity<String> resposta = testRestTemplate
		.withBasicAuth("root@root.com", "rootroot")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

}