/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.inf.datainfo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.inf.datainfo.i18n.AppResources;
import br.inf.datainfo.model.UsuarioExterno;
import br.inf.datainfo.model.UsuarioExternoFuncao;
import br.inf.datainfo.model.UsuarioSituacao;
import br.inf.datainfo.model.DTO.UsuarioExternoDTO;

/**
 * Classe responsável por realizar operações de banco de dados sobre a classe
 * {@link UsuarioExterno }
 * 
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)
 *
 */
@Stateless
public class UsuarioExternoRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Encontra um usuário externo pelo seu cpf
	 * 
	 * @param cpf
	 *            - cpf que será buscado
	 * @return um {@link UsuarioExterno}
	 */
	public UsuarioExterno findByCPF(String cpf) {
		return em.find(UsuarioExterno.class, cpf);
	}

	/**
	 * Encontra uma lista de usuários externos passando parâmetros de busca
	 * adicionais
	 * 
	 * @param nome
	 *            - nome que será utilizado como parâmetro adicional
	 * @param situacao
	 *            - situação que será utilizado como parâmetro adicional
	 * @param funcao
	 *            - funcao que será utilizado como parâmetro adicional
	 * @return uma {@link List<UsuarioExterno>}
	 */
	public List<UsuarioExterno> findFiltering(String nome, UsuarioSituacao situacao, UsuarioExternoFuncao funcao) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioExterno> criteria = cb.createQuery(UsuarioExterno.class);
		Root<UsuarioExterno> usuarioExterno = criteria.from(UsuarioExterno.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (funcao != null) {
			predicates.add(cb.equal(usuarioExterno.join("usuarioFuncao").get("id"), funcao.getId()));
		}
		if (situacao != null) {
			predicates.add(cb.equal(usuarioExterno.get("situacao"), situacao));
		}
		if (nome != null && nome.length() > 0) {
			Expression<String> x = usuarioExterno.get("nome");
			predicates.add(cb.like(x, "%" + nome + "%"));
		}

		if (predicates.size() > 0) {
			criteria.select(usuarioExterno).where(cb.and(predicates.toArray(new Predicate[] {})));
			return em.createQuery(criteria).getResultList();

		} else {
			return findAllOrderedByName();
		}
	}

	/**
	 * Exclui um {@link UsuarioExterno} pelo seu cpf
	 * 
	 * @param cpf
	 *            - cpf que será utilizado para encontrar o cadastro a ser
	 *            excluído
	 */
	public void delete(String cpf) {
		em.remove(findByCPF(cpf));
	}

	/**
	 * Código para validar cpf
	 * https://www.vivaolinux.com.br/script/Codigo-Java-para-validar-CPF
	 * 
	 * @param strCpf
	 *            - cpf que será validadado
	 * @return
	 */
	public boolean isCpfValid(String strCpf) {
		if (strCpf != null) {
			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;

			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;

			for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
				digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

				// multiplique a ultima casa por 2 a seguinte por 3 a seguinte
				// por 4 e assim por diante.
				d1 = d1 + (11 - nCount) * digitoCPF;

				// para o segundo digito repita o procedimento incluindo o
				// primeiro digito calculado no passo anterior.
				d2 = d2 + (12 - nCount) * digitoCPF;
			}
			;

			// Primeiro resto da divisão por 11.
			resto = (d1 % 11);

			// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é
			// 11 menos o resultado anterior.
			if (resto < 2)
				digito1 = 0;
			else
				digito1 = 11 - resto;

			d2 += 2 * digito1;

			// Segundo resto da divisão por 11.
			resto = (d2 % 11);

			// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é
			// 11 menos o resultado anterior.
			if (resto < 2)
				digito2 = 0;
			else
				digito2 = 11 - resto;

			// Digito verificador do CPF que está sendo validado.
			String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

			// Concatenando o primeiro resto com o segundo.
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

			// comparar o digito verificador do cpf com o primeiro resto + o
			// segundo resto.
			return nDigVerific.equals(nDigResult);

		}
		return false;
	}

	/**
	 * Encontra todos usuários ordenando-os por nome
	 * 
	 * @return uma List<UsuarioExterno>
	 */
	public List<UsuarioExterno> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioExterno> criteria = cb.createQuery(UsuarioExterno.class);
		Root<UsuarioExterno> usuarioExtenrnoCriteria = criteria.from(UsuarioExterno.class);
		criteria.select(usuarioExtenrnoCriteria).orderBy(cb.asc(usuarioExtenrnoCriteria.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
	
	/**
	 * Método reponsável pela criação de um usuário externo
	 * @param usuarioExterno - usuario externo que será criado
	 * @return o {@link UsuarioExterno} criado
	 */
	public UsuarioExterno createUsuarioExterno(UsuarioExternoDTO usuarioExterno) {
		// Validações
		// cpf inválido
		if (!isCpfValid(usuarioExterno.getCpf())) {
			throw new IllegalArgumentException(AppResources.getMessage("usuario.cpf.invalido"));
		}
		// Cpf duplicado
		if (findByCPF(usuarioExterno.getCpf()) != null) {
			throw new IllegalArgumentException(AppResources.getMessage("usuario.duplicado"));
		}

		if (usuarioExterno.getVersion() == null) {
			UsuarioExterno toSaveUsuarioExterno = new UsuarioExterno();
			toSaveUsuarioExterno.setCpf(usuarioExterno.getCpf());
			dtoToEntity(usuarioExterno, toSaveUsuarioExterno);
			em.persist(toSaveUsuarioExterno);
			toSaveUsuarioExterno.getUsuarioFuncao();
			return toSaveUsuarioExterno;
		} else {
			UsuarioExterno savedUsuarioExterno = em.find(UsuarioExterno.class, usuarioExterno.getCpf());
			dtoToEntity(usuarioExterno, savedUsuarioExterno);
			savedUsuarioExterno.getUsuarioFuncao();
			return em.merge(savedUsuarioExterno);
		}
	}

	/**
	 * Converte o objeto de transporte numa entidade
	 * @param usuarioExterno - usuário objeto de transporte
	 * @param savedUsuarioExterno - usuário salvo
	 */
	private void dtoToEntity(UsuarioExternoDTO usuarioExterno, UsuarioExterno savedUsuarioExterno) {
		savedUsuarioExterno.setEmail(usuarioExterno.getEmail());
		savedUsuarioExterno.setNome(usuarioExterno.getNome());
		UsuarioSituacao usuarioSituacao = Stream.of(UsuarioSituacao.values())
				.filter(u -> usuarioExterno.getSituacao().getCodigo().equals(u.ordinal())).findAny().get();
		savedUsuarioExterno.setSituacao(usuarioSituacao);
		savedUsuarioExterno.setTelefone(usuarioExterno.getTelefone());
		savedUsuarioExterno.setUsuarioFuncao(
				em.find(UsuarioExternoFuncao.class, Long.valueOf(usuarioExterno.getPerfil().getCodigo())));
	}
}
