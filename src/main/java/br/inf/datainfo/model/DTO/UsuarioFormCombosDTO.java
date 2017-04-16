package br.inf.datainfo.model.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.inf.datainfo.model.UsuarioExternoFuncao;
import br.inf.datainfo.model.UsuarioSituacao;

/**
 * Objeto para transporte de opções de combobox
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
public class UsuarioFormCombosDTO {

	private List<UsuarioExternoFuncaoDTO> usuarioFuncao;
	private List<UsuarioSituacaoDTO> usuarioSituacao;

	public UsuarioFormCombosDTO(List<UsuarioExternoFuncao> funcoes, List<UsuarioSituacao> situacoes) {
		setUsuarioFuncao(funcoes.stream().map(f -> new UsuarioExternoFuncaoDTO(f.getId().intValue(), f.getNome()))
				.collect(Collectors.toList()));
		setUsuarioSituacao(situacoes.stream().map(f -> new UsuarioSituacaoDTO(f.ordinal(), f.getDescricao()))
				.collect(Collectors.toList()));

	}

	public List<UsuarioExternoFuncaoDTO> getUsuarioFuncao() {
		return usuarioFuncao;
	}

	public void setUsuarioFuncao(List<UsuarioExternoFuncaoDTO> usuarioFuncao) {
		this.usuarioFuncao = usuarioFuncao;
	}

	public List<UsuarioSituacaoDTO> getUsuarioSituacao() {
		return usuarioSituacao;
	}

	public void setUsuarioSituacao(List<UsuarioSituacaoDTO> usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

}
