package br.inf.datainfo.model.DTO;

/**
 * Objeto de transporte que representa uma busca
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
public class UsuarioSearchDTO {

	private String nome;
	private Integer situacao;
	private Integer perfil;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}


}
