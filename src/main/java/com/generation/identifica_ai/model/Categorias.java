package com.generation.identifica_ai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categorias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Tipo de Categoria")
	@Size(min = 1, max = 255, message = "O atributo tipo é obrigatório e deve ter no máximo 255 caracteres.")
	private String tipo;
	
	@NotNull(message = "O Atributo Descrição é obrigatório")
	@Size(min = 1, max = 500, message = "O atributo descricao é obrigatório e deve ter no máximo 500 caracteres.")
	private String descricao;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Servicos> servicos;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the servicos
	 */
	public List<Servicos> getServicos() {
		return servicos;
	}

	/**
	 * @param servicos the servicos to set
	 */
	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}

}
