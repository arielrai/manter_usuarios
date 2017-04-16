package br.inf.datainfo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de persistência de usuário externo função
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
@Entity
@Table(name = "funcao_usuario_externo")
public class UsuarioExternoFuncao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "co_funcao", updatable = false, nullable = false)
	private Long id;

	@Column(length = 50, name = "no_funcao")
	private String nome;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UsuarioExternoFuncao)) {
			return false;
		}
		UsuarioExternoFuncao other = (UsuarioExternoFuncao) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}