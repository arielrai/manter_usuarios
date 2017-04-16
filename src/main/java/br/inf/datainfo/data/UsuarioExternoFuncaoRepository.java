package br.inf.datainfo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.inf.datainfo.model.UsuarioExternoFuncao;

/**
 * Classe responsável por realizar operações de banco de dados sobre a classe {@link UsuarioExternoFuncao}
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
@Stateless
public class UsuarioExternoFuncaoRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Busca um {@link UsuarioExternoFuncao} pelo id
	 * @param id - id que será buscado
	 * @return o {@link UsuarioExternoFuncao} encontrado
	 */
	public UsuarioExternoFuncao findById(Long id) {
		return em.find(UsuarioExternoFuncao.class, id);
	}

	/**
	 * Busca todos os {@link UsuarioExternoFuncao}
	 * @return uma {@link List<UsuarioExternoFuncao>}
	 */
	public List<UsuarioExternoFuncao> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioExternoFuncao> criteria = cb.createQuery(UsuarioExternoFuncao.class);
		Root<UsuarioExternoFuncao> usuarioExtenrnoCriteria = criteria.from(UsuarioExternoFuncao.class);
		criteria.select(usuarioExtenrnoCriteria);
		return em.createQuery(criteria).getResultList();
	}
}