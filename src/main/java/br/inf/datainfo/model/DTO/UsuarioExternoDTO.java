package br.inf.datainfo.model.DTO;

import br.inf.datainfo.model.UsuarioExterno;

/**
 * Objeto de transporte que representa um {@link UsuarioExterno}
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
public class UsuarioExternoDTO {

	private Integer version;
	private String email;
	private String cpf;
	private String nome;
	private UsuarioExternoFuncaoDTO perfil;
	private UsuarioSituacaoDTO situacao;
	private String telefone;

	public UsuarioExternoDTO() {

	}

	public UsuarioExternoDTO(Integer version, String email, String cpf, String nome, UsuarioExternoFuncaoDTO perfil,
			UsuarioSituacaoDTO situacao, String telefone) {
		super();
		this.version = version;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.perfil = perfil;
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioExternoFuncaoDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(UsuarioExternoFuncaoDTO perfil) {
		this.perfil = perfil;
	}

	public UsuarioSituacaoDTO getSituacao() {
		return situacao;
	}

	public void setSituacao(UsuarioSituacaoDTO situacao) {
		this.situacao = situacao;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
