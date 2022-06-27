package com.trabappcorp.Adopet;

import DAO.InstituicaoDAO;
import Utils.JSONBuilder;
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

@Path("instituicoes")
public class InstituicoesResource extends UsersResource {

    private @Context
    HttpServletRequest servletRequest;

    /* GET - "/encontrar/adotar" - retorna as instituicoes da cidade do usuario autenticado
    priorizando as mais cheias */
    @GET
    @Path("/encontrar/adotar")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstituicoesPorCapacidadeCheia() {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("paramUsuario");

            InstituicaoDAO instituicoesDAO = InstituicaoDAO.getInstance();
            List<Instituicao> instituicoes = instituicoesDAO.findAll();
            instituicoes.sort(Comparator.comparingInt(
                    (Instituicao p) -> ((p.getNumeroAnimais() / p.getCapacidade()) * 100)
                    ).reversed()
            );
            JsonArrayBuilder instituicoesJson = jsonFactory.createArrayBuilder();

            for (Instituicao instituicao : instituicoes) {
                if (instituicao.getEndereco().getCidade().equals(usuario.getEndereco().getCidade())){
                    JSONBuilder enderecoJson = new JSONBuilder();
                    enderecoJson
                            .add("estado", instituicao.getEndereco().getEstado())
                            .add("cidade", instituicao.getEndereco().getCidade())
                            .add("bairro", instituicao.getEndereco().getBairro())
                            .add("rua", instituicao.getEndereco().getRua())
                            .add("numero", instituicao.getEndereco().getNumero());

                    JsonArrayBuilder contatosJson = jsonFactory.createArrayBuilder();
                    Set<Contato> contatosInstituicao = instituicao.getContatos();

                    for (Contato contato : contatosInstituicao) {
                        JsonObjectBuilder contatoJson = jsonFactory.createObjectBuilder();
                        contatoJson
                                .add("nome", contato.getNome())
                                .add("info", contato.getInfo())
                                .add("tipo", contato.getTipo());

                        contatosJson.add(contatoJson);
                    }

                    JsonObjectBuilder instituicaoJson = jsonFactory.createObjectBuilder();
                    instituicaoJson
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
            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("instituicoes", instituicoesJson)
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
    @Path("/encontrar/doar")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstituicoesPorCapacidadeVazia() {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("paramUsuario");

            InstituicaoDAO instituicoesDAO = InstituicaoDAO.getInstance();
            List<Instituicao> instituicoes = instituicoesDAO.findAll();
            instituicoes.sort(
                    Comparator.comparingInt(
                            p -> (p.getNumeroAnimais()/p.getCapacidade())*100
                    )
            );
            JsonArrayBuilder instituicoesJson = jsonFactory.createArrayBuilder();

            for (Instituicao instituicao : instituicoes) {
                if (instituicao.getEndereco().getCidade().equals(usuario.getEndereco().getCidade())){
                    JSONBuilder enderecoJson = new JSONBuilder();
                    enderecoJson
                            .add("estado", instituicao.getEndereco().getEstado())
                            .add("cidade", instituicao.getEndereco().getCidade())
                            .add("bairro", instituicao.getEndereco().getBairro())
                            .add("rua", instituicao.getEndereco().getRua())
                            .add("numero", instituicao.getEndereco().getNumero());

                    JsonArrayBuilder contatosJson = jsonFactory.createArrayBuilder();
                    Set<Contato> contatosInstituicao = instituicao.getContatos();

                    for (Contato contato : contatosInstituicao) {
                        JsonObjectBuilder contatoJson = jsonFactory.createObjectBuilder();
                        contatoJson
                                .add("nome", contato.getNome())
                                .add("info", contato.getInfo())
                                .add("tipo", contato.getTipo());

                        contatosJson.add(contatoJson);
                    }

                    JsonObjectBuilder instituicaoJson = jsonFactory.createObjectBuilder();
                    instituicaoJson
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
            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("instituicoes", instituicoesJson)
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

    /* GET - "/{id}/pets" - retorna os pets cadastrados na instituicao*/
    @GET
    @Path("/{idInstituicao}/pets")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetsInstituicao(@PathParam("idInstituicao") Long idInstituicao) {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            InstituicaoDAO instituicoesDAO = InstituicaoDAO.getInstance();
            Set<Pet> petsInstituicao = instituicoesDAO.getById(idInstituicao).getPetsCadastrados();

            JsonArrayBuilder petsInstituicaoJson = jsonFactory.createArrayBuilder();

            for (Pet pet : petsInstituicao) {
                if (!pet.getAdotado()) {

                    JsonObjectBuilder petJson = jsonFactory.createObjectBuilder();
                    petJson
                            .add("nome", pet.getNome())
                            .add("dataNascimento", pet.getDataNascimento().toString())
                            .add("peso", pet.getPeso())
                            .add("altura", pet.getAltura())
                            .add("raca", pet.getVariacao().getRaca())
                            .add("porte", pet.getVariacao().getPorte())
                            .add("especie", pet.getVariacao().getEspecie())
                            .add("gastoMensal", pet.getGastoMensal())
                            .add("observacoes", pet.getObservacoes())
                            .add("fotos", pet.getFotos());
                    petsInstituicaoJson.add(petJson);
                }
            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("pets", petsInstituicaoJson)
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
