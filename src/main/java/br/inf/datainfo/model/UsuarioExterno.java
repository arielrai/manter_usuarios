package br.inf.datainfo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe de persistência de usuário externo
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
@Entity
@Table(name = "usuario_externo")
public class UsuarioExterno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 11, name = "nu_cpf", nullable = false)
	private String cpf;

	@Column(length = 60, name = "no_usuario", nullable = false)
	private String nome;

	@Column(name = "de_email", nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "ic_situacao", length = 1)
	private UsuarioSituacao situacao;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "ic_perfil_acesso", referencedColumnName="co_funcao")
	private UsuarioExternoFuncao usuarioFuncao;

	@Column(length = 11, name = "nu_telefone")
	private String telefone;


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public UsuarioSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(UsuarioSituacao situacao) {
		this.situacao = situacao;
	}

	public UsuarioExternoFuncao getUsuarioFuncao() {
		return this.usuarioFuncao;
	}

	public void setUsuarioFuncao(final UsuarioExternoFuncao usuarioFuncao) {
		this.usuarioFuncao = usuarioFuncao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}