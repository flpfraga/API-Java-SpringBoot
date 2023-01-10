package com.fraga.ApiProject.data.vo;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fraga.ApiProject.data.model.Pessoa;
import com.github.dozermapper.core.Mapping;

public class EnderecoVO extends RepresentationModel <EnderecoVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;

	private String logradouro;

	private String cidade;

	private Integer numero;

	private Boolean principal;

	private String cep;

	@JsonIgnoreProperties("endereco")
	private Pessoa pessoa;
	
	public EnderecoVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cep, cidade, key, logradouro, numero, pessoa, principal);
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
		EnderecoVO other = (EnderecoVO) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade) && Objects.equals(key, other.key)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero)
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(principal, other.principal);
	}

	@Override
	public String toString() {
		return "EnderecoVO [key=" + key + ", logradouro=" + logradouro + ", cidade=" + cidade + ", numero=" + numero
				+ ", principal=" + principal + ", cep=" + cep + ", pessoa=" + pessoa + "]";
	}
	
	
	
}
