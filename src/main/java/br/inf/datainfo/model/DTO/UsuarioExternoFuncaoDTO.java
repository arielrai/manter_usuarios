package br.inf.datainfo.model.DTO;

import br.inf.datainfo.model.UsuarioExternoFuncao;

/**
 * Objeto de transporte que representa um {@link UsuarioExternoFuncao}
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
public class UsuarioExternoFuncaoDTO {

	private Integer codigo;
	private String descricao;
	
	public UsuarioExternoFuncaoDTO() {
	}
	
	public UsuarioExternoFuncaoDTO(Integer codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
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
