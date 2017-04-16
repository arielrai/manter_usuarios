package br.inf.datainfo.model.DTO;

/**
 * Objeto de transporte que representa a situação do usuário
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
public class UsuarioSituacaoDTO {

	private Integer codigo;
	private String descricao;

	public Integer getCodigo() {
		return codigo;
	}
	
	public UsuarioSituacaoDTO() {
	}

	public UsuarioSituacaoDTO(Integer codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
