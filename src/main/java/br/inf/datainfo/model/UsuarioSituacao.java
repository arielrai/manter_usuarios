package br.inf.datainfo.model;

import br.inf.datainfo.i18n.AppResources;

/**
 * Possíveis status do usuário
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
public enum UsuarioSituacao {
	A(AppResources.getMessage("usuario.ativo")), I(AppResources.getMessage("usuario.inativo"));

	private String descricao;

	private UsuarioSituacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
