package com.fraga.ApiProject.data.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fraga.ApiProject.data.model.Endereco;
import com.github.dozermapper.core.Mapping;

public class PessoaVO extends RepresentationModel <PessoaVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	
	private String nome;
	
	private LocalDate nascimento;
	
	
	public PessoaVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key, nascimento, nome);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaVO other = (PessoaVO) obj;
		return Objects.equals(key, other.key) && Objects.equals(nascimento, other.nascimento)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "PessoaVO [key=" + key + ", nome=" + nome + ", nascimento=" + nascimento + "]";
	}
	
}
