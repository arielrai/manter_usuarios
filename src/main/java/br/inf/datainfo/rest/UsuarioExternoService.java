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
package br.inf.datainfo.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inf.datainfo.data.UsuarioExternoFuncaoRepository;
import br.inf.datainfo.data.UsuarioExternoRepository;
import br.inf.datainfo.i18n.AppResources;
import br.inf.datainfo.model.UsuarioExterno;
import br.inf.datainfo.model.UsuarioSituacao;
import br.inf.datainfo.model.DTO.UsuarioExternoDTO;
import br.inf.datainfo.model.DTO.UsuarioExternoFuncaoDTO;
import br.inf.datainfo.model.DTO.UsuarioFormCombosDTO;
import br.inf.datainfo.model.DTO.UsuarioSearchDTO;
import br.inf.datainfo.model.DTO.UsuarioSituacaoDTO;


/**
 * Classe reponsável pela com comunicação cliente-servidor da aplicação
 * @author Ariel Rai Rodrigues (arielrairodrigues@gmail.com)	
 *
 */
@Path("/users")
@ApplicationScoped
public class UsuarioExternoService {

    @Inject
    private UsuarioExternoRepository repository;
    @Inject 
    private UsuarioExternoFuncaoRepository usuarioFuncaoRepository; 


    /**
     * Endpoint responsável por listagem de usuários externos
     * @return uma {@link Response}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllMembers() {
    	List<UsuarioExternoDTO> usuarioExternos = repository.findAllOrderedByName().stream().map(t -> entityToDTO(t)).collect(Collectors.toList());
        return Response.ok(usuarioExternos).build();
    }
    
    /**
     * Endpoint responsável por listagem de usuários externos com parâmetros de busca
     * @param search - parâmetros de busca
     * @return uma {@link Response}
     */
    @POST
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMembers(UsuarioSearchDTO search) {
    	List<UsuarioExternoDTO> usuarioExternos = repository.findFiltering(search.getNome(), 
    			search.getSituacao() != null ? Stream.of(UsuarioSituacao.values()).filter(s -> search.getSituacao().equals(s.ordinal())).findAny().get(): null, 
    					search.getPerfil() != null ? usuarioFuncaoRepository.findById(Long.valueOf(search.getPerfil())) : null )
    			.stream().map(t -> entityToDTO(t)).collect(Collectors.toList());
        return Response.ok(usuarioExternos).build();
    }
    
    /**
     * Endpoint responsável por listar as possíveis opções de combobox
     * @return uma {@link Response}
     */
    @GET
    @Path("comboOptions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComboOptions() {
        return Response.ok(new UsuarioFormCombosDTO(usuarioFuncaoRepository.findAll(), Arrays.asList(UsuarioSituacao.values()))).build();
    }

    /**
     * converte uma entidade num objeto de transporte
     * @param usuarioExterno - usuario externo que será convertido
     * @return um {@link UsuarioExternoDTO} 
     */
	private UsuarioExternoDTO entityToDTO(UsuarioExterno usuarioExterno) {
		UsuarioExternoFuncaoDTO usuarioExternoFuncaoDTO = new UsuarioExternoFuncaoDTO(usuarioExterno.getUsuarioFuncao().getId().intValue(), usuarioExterno.getUsuarioFuncao().getNome());
		UsuarioSituacaoDTO usuarioSituacaoDTO = new UsuarioSituacaoDTO(usuarioExterno.getSituacao().ordinal(), usuarioExterno.getSituacao().getDescricao());

		return new UsuarioExternoDTO(-1, usuarioExterno.getEmail(), usuarioExterno.getCpf(), usuarioExterno.getNome(), usuarioExternoFuncaoDTO, usuarioSituacaoDTO, usuarioExterno.getTelefone());
	}

	/**
	 * Endpoint responsável pela criação de um novo usuário
	 * @param dto - dto que será utilizado na criacao
	 * @return uma {@link Response}
	 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(UsuarioExternoDTO dto){
    	
    	try {
    		repository.createUsuarioExterno(dto);
    		return Response.ok(AppResources.getMessage("usuario.cadastrado")).build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getCause().getMessage()).build();
		}
    }

    /**
     * Exclui um usuário
     * @param cpf
     * @return
     */
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{cpf}")
    public Response delete(@PathParam("cpf") String cpf){
   		repository.delete(cpf);
   		return Response.ok(AppResources.getMessage("usuario.excluido")).build();
    }
    
}
