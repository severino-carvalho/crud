package br.edu.ifrn.crud.domains;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.*;

@Entity
public class Usuario {

	// Atributos da classe
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false) // MAPEA O ATRIBUTO PARA UM COLUNA NO BANCO DE DADOS
	@NotBlank(message = "Campo 'Nome' é obrigatório.") // Obriga o campo a não ser nulo ou não está em braco
	@Size(min = 3, message = "O campo 'Nome' deve conter ao menos 3 caracteres.") // Limita o limite mínimo de
																					// caracteres para
	// 3, ex: Ana - Valor mínimo
	private String nome;

	@Column(nullable = false) // MAPEA O ATRIBUTO PARA UM COLUNA NO BANCO DE DADOS
	@NotBlank(message = "Campo 'Email' é obrigatório.") // Obriga o campo a não ser nulo ou não está em braco
	private String email;

	@Column(nullable = false) // MAPEA O ATRIBUTO PARA UM COLUNA NO BANCO DE DADOS
	@NotBlank(message = "Campo 'Telefone' é obrigatório.") // Obriga o campo a não ser nulo ou não está em braco
	@Size(min = 9, max = 9, message = "O campo 'Telefone' deve conter somente 9 dígitos.") // Obriga o campo ter um
																							// valor de 9 dígitos
	private String telefone;

	@Column(nullable = false) // MAPEA O ATRIBUTO PARA UM COLUNA NO BANCO DE DADOS
	@NotBlank(message = "Campo 'Senha' é obrigatório.") // Obriga o campo a não ser nulo ou não está em braco
	@Size(min = 6, max = 15, message = "A senha deve conter dentre 6 a 15 caractéres")
	private String senha;

	@Column(nullable = false) // MAPEA O ATRIBUTO PARA UM COLUNA NO BANCO DE DADOS
	@NotBlank(message = "Campo 'Sexo' é obrigatório.") // Obriga o campo a não ser nulo ou não está em braco
	private String sexo;

	@ManyToOne(optional = false) // MAPEA O ATRIBUTO PARA UM COLUNA NO BANCO DE DADOS
	@NotBlank(message = "Campo 'Profissao' é obrigatório.") // Obriga o campo a não ser nulo ou não está em braco
	private Profissao profissao;

	@ManyToMany
	private List<CursoFormacao> formacoes;

	@Transient
	private CursoFormacao formacao;

	// Equals and HashCode
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}

	// Construtor da classe
	public Usuario() {

	}

	// GETs e SETs

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public List<CursoFormacao> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(List<CursoFormacao> formacoes) {
		this.formacoes = formacoes;
	}

	public CursoFormacao getFormacao() {
		return formacao;
	}

	public void setFormacao(CursoFormacao formacao) {
		this.formacao = formacao;
	}

	

}
