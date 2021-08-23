package br.edu.ifrn.crud.domains;

import java.util.Objects;

public class Usuario {
	// Atributos da classe
	private int uid;
	private String nome;
	private String email;
	private String senha;
	private String sexo;
	private String profissao;

	
	@Override
	public int hashCode() {
		return Objects.hash(uid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return uid == other.uid;
	}
	
	
	// Construtor da classe
	public Usuario() {

	}

	// GETs e SETs
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

}
