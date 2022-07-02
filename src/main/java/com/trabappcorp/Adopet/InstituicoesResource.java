package com.trabappcorp.Adopet;

import DAO.InstituicaoDAO;
import Utils.JSONArrayBuilder;
import Utils.JSONBuilder;
import Utils.ValidationWrapper;
import filters.Authorize;
import modelo.Contato;
import modelo.Instituicao;
import modelo.Pet;
import modelo.Usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import modelo.Adotante;
import modelo.Doador;

@Path("instituicoes")
public class InstituicoesResource extends UsersResource {

    private @Context
    HttpServletRequest servletRequest;

    /* GET - "/encontrar/adotar" - retorna as instituicoes da cidade do usuario autenticado
    priorizando as mais cheias */
    @GET
    @Path("/adotar")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response findInstituicaoToAdopt() {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("adotante")) {
                throw new HttpErrors.Unauthorized("Usuário autenticado não é um adotante");
            }

            Adotante adotante = (Adotante) usuario;

            InstituicaoDAO instituicoesDAO = InstituicaoDAO.getInstance();
            List<Instituicao> instituicoes = instituicoesDAO.findFor(adotante);

            JSONArrayBuilder instituicoesJson = new JSONArrayBuilder();

            for (Instituicao instituicao : instituicoes) {
                JSONBuilder enderecoJson = new JSONBuilder();
                enderecoJson
                        .add("estado", instituicao.getEndereco().getEstado())
                        .add("cidade", instituicao.getEndereco().getCidade())
                        .add("bairro", instituicao.getEndereco().getBairro())
                        .add("rua", instituicao.getEndereco().getRua())
                        .add("numero", instituicao.getEndereco().getNumero());

                JSONArrayBuilder contatosJson = new JSONArrayBuilder();
                List<Contato> contatosInstituicao = instituicao.getContatos();

                for (Contato contato : contatosInstituicao) {
                    JSONBuilder contatoJson = new JSONBuilder();
                    contatoJson
                            .add("nome", contato.getNome())
                            .add("info", contato.getInfo())
                            .add("tipo", contato.getTipo());

                    contatosJson.add(contatoJson);
                }

                JSONBuilder instituicaoJson = new JSONBuilder();
                instituicaoJson
                        .add("id", instituicao.getId())
                        .add("nome", instituicao.getNome())
                        .add("razaoSocial", instituicao.getRazaoSocial())
                        .add("numeroRegistro", instituicao.getNumeroRegistro())
                        .add("dataFundacao", instituicao.getDataFundacao().toString())
                        .add("certificacoes", instituicao.getCertificacoes())
                        .add("capacidade", instituicao.getCapacidade())
                        .add("numeroAnimais", instituicao.getNumeroAnimais())
                        .add("endereco", enderecoJson)
                        .add("contatos", contatosJson)
                        .add("perfil", instituicao.getPerfil());

                instituicoesJson.add(instituicaoJson);

            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("instituicoes", instituicoesJson)
                                    .build()
                    ).build();

        } catch (HttpErrors.HttpError httpError) {
            return Response.status(httpError.getStatus())
                    .entity(
                            responseJson
                                    .add("mensagem", httpError.getMessage())
                                    .build()
                    ).build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(responseJson.add("mensagem", "Erro interno").build())
                    .build();
        }

    }


    /* GET - "/encontrar/doar" - retorna as instituicoes da cidade do usuario autenticado
    priorizando as mais vazias */
    @GET
    @Path("/doar")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response findInstituicaoToDonate() {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("doador")) {
                throw new HttpErrors.Unauthorized("Usuário autenticado não é um doador");
            }

            Doador doador = (Doador) usuario;

            InstituicaoDAO instituicoesDAO = InstituicaoDAO.getInstance();
            List<Instituicao> instituicoes = instituicoesDAO.findFor(doador);

            JSONArrayBuilder instituicoesJson = new JSONArrayBuilder();

            for (Instituicao instituicao : instituicoes) {
                JSONBuilder enderecoJson = new JSONBuilder();
                enderecoJson
                        .add("estado", instituicao.getEndereco().getEstado())
                        .add("cidade", instituicao.getEndereco().getCidade())
                        .add("bairro", instituicao.getEndereco().getBairro())
                        .add("rua", instituicao.getEndereco().getRua())
                        .add("numero", instituicao.getEndereco().getNumero());

                JSONArrayBuilder contatosJson = new JSONArrayBuilder();
                List<Contato> contatosInstituicao = instituicao.getContatos();

                for (Contato contato : contatosInstituicao) {
                    JSONBuilder contatoJson = new JSONBuilder();
                    contatoJson
                            .add("nome", contato.getNome())
                            .add("info", contato.getInfo())
                            .add("tipo", contato.getTipo());

                    contatosJson.add(contatoJson);
                }

                JSONBuilder instituicaoJson = new JSONBuilder();
                instituicaoJson
                        .add("id", instituicao.getId())
                        .add("nome", instituicao.getNome())
                        .add("razaoSocial", instituicao.getRazaoSocial())
                        .add("numeroRegistro", instituicao.getNumeroRegistro())
                        .add("dataFundacao", instituicao.getDataFundacao().toString())
                        .add("certificacoes", instituicao.getCertificacoes())
                        .add("capacidade", instituicao.getCapacidade())
                        .add("numeroAnimais", instituicao.getNumeroAnimais())
                        .add("endereco", enderecoJson)
                        .add("contatos", contatosJson)
                        .add("perfil", instituicao.getPerfil());

                instituicoesJson.add(instituicaoJson);

            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("instituicoes", instituicoesJson)
                                    .build()
                    ).build();

        } catch (HttpErrors.HttpError httpError) {
            return Response.status(httpError.getStatus())
                    .entity(
                            responseJson
                                    .add("mensagem", httpError.getMessage())
                                    .build()
                    ).build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(responseJson.add("mensagem", "Erro interno").build())
                    .build();
        }

    }

    @GET
    @Path("/{instituicaoId}/pets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegisteredPets(@PathParam("instituicaoId") String instituicaoId_str) {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            JSONArrayBuilder petsJson = new JSONArrayBuilder();

            Long instituicaoId = ValidationWrapper.parseLongId("instituicaoId", instituicaoId_str);
            
            InstituicaoDAO instDao = InstituicaoDAO.getInstance();
            
            Instituicao instituicao = instDao.getById(instituicaoId);
            
            if(instituicao == null) throw new HttpErrors.NotFound("Instituicao não foi encontrada");
            
            List<Pet> pets = instituicao.getPetsCadastrados();

            for (Pet pet : pets) {
                JSONBuilder petJson = new JSONBuilder();
                petJson
                        .add("id", pet.getId())
                        .add("adotado", pet.getAdotado())
                        .add("altura", pet.getAltura())
                        .add("fotos", pet.getFotos())
                        .add("gastoMensal", pet.getGastoMensal())
                        .add("nome", pet.getNome())
                        .add("observacoes", pet.getObservacoes())
                        .add("peso", pet.getPeso())
                        .add("dataNascimento", pet.getDataNascimento().toString())
                        .add("variacao", pet.getVariacao().getId());

                petsJson.add(petJson);
            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("pets", petsJson)
                                    .build()
                    ).build();

        } catch (HttpErrors.HttpError httpError) {
            return Response.status(httpError.getStatus())
                    .entity(
                            responseJson
                                    .add("mensagem", httpError.getMessage())
                                    .build()
                    ).build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(responseJson.add("mensagem", "Erro interno").build())
                    .build();
        }
    }


}
