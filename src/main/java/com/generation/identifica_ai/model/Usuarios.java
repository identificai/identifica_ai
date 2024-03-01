package com.generation.identifica_ai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.identifica_ai.model.Servicos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


	@Entity
	@Table(name = "tb_usuarios")
	public class Usuarios {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Schema(example = "email@email.com.br")
		@NotNull(message = "O Atributo Usuário é Obrigatório!")
		@Email(message = "O Atributo Usuário deve ser um email válido!")
		private String usuario;
		
		@NotBlank(message = "Tipo de usuário")
		@Size(min = 1, max = 255, message = "O atributo tipo é obrigatório e deve ter no máximo 255 caracteres.")
		private String tipo;

		@NotNull(message = "O Atributo Nome é Obrigatório!")
		private String nome;

		@NotBlank(message = "O Atributo Senha é Obrigatório!")
		@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
		private String senha;

		@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
		private String foto;

		@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("usuario")
		private List<Servicos> servicos;
		
		

		public Usuarios(Long id, String usuario, String tipo, String nome, String senha, String foto) {
			this.id = id;
			this.usuario = usuario;
			this.tipo = tipo;
			this.nome = nome;
			this.senha = senha;
			this.foto = foto;
		}

		public Usuarios() {
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public List<Servicos> getServicos() {
			return servicos;
		}

		public void setServicos(List<Servicos> servicos) {
			this.servicos = servicos;
		}
		
		
}
