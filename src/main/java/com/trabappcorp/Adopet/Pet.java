package com.trabappcorp.Adopet;

import DAO.PetDAO;
import DAO.VariacaoDAO;
import HttpErrors.BadRequest;
import Utils.JSONBuilder;
import filters.Authorize;
import filters.UserParam;
import java.util.List;
import java.util.Set;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import modelo.Adotante;
import modelo.Usuario;
import modelo.Variacao;

/**
 * Root resource (exposed at "myresource" path)
 */
//@Path("/pets")
public class Pet {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it! ";
    }

    /* 
        /pets
            /desejados
                GET     retorna os pets marcados como desejados pelo adotante autenticado (DONE)
            /variacoes
                GET     retorna as variacoes de pet cadastradas (DONE)
                POST    registra uma nova variacao de pet (DONE)

     
    @GET
    @Path("{idUsuario}")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetsDesejados(@PathParam("idUsuario") Long id) {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            JSONBuilder petJson = new JSONBuilder();

            JsonArrayBuilder wantedPetsListJson = jsonFactory.createArrayBuilder();
            JsonArrayBuilder adotantesListJson = jsonFactory.createArrayBuilder();
            PetDAO petDAO = PetDAO.getInstance();
            List<modelo.Pet> wantedPets = petDAO.getWantedPetsByUserId(id);

            for (modelo.Pet pet : wantedPets) {
                for (Adotante adotante : pet.getDesejadoPor()) {
                    JsonObjectBuilder adotantesJson = jsonFactory.createObjectBuilder();
                    adotantesJson
                            .add("cpf", adotante.getCPF())
                            .add("nome", adotante.getNome());

                    adotantesListJson.add(adotantesJson);
                }
                JsonObjectBuilder wantedPetJson = jsonFactory.createObjectBuilder();
                wantedPetJson
                        .add("id", pet.getId())
                        .add("name", pet.getNome())
                        .add("dataNascimento", pet.getDataNascimento().toString())
                        .add("peso", pet.getPeso())
                        .add("altura", pet.getAltura())
                        .add("adotado", pet.getAdotado())
                        .add("dono", pet.getDono().getId())
                        .add("variacao", pet.getVariacao().getId())
                        .add("desejadoPor", adotantesListJson)
                        .add("gastoMensal", pet.getGastoMensal())
                        .add("observacoes", pet.getObservacoes())
                        .add("fotos", pet.getFotos());

                wantedPetsListJson.add(wantedPetJson);
            }

            petJson
                    .add("userId", id)
                    .add("pets", wantedPetsListJson);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("petsDesejados", petJson)
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
    @Path("getVariacoes")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVariacoes() {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            JSONBuilder variacoesJson = new JSONBuilder();

            JsonArrayBuilder variacoesListJson = jsonFactory.createArrayBuilder();
            VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();
            List<Variacao> variacoes = variacaoDAO.getAllVariacoes();

            for (Variacao variacao : variacoes) {
                JsonObjectBuilder variacaoJson = jsonFactory.createObjectBuilder();
                variacaoJson
                        .add("id", variacao.getId())
                        .add("raca", variacao.getRaca())
                        .add("porte", variacao.getPorte())
                        .add("especie", variacao.getEspecie());

                variacoesListJson.add(variacaoJson);
            }

            variacoesJson
                    .add("variacoes", variacoesListJson);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("variacoes", variacoesJson)
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

    @POST
    @Path("registerVariacao")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateUserInfo(
            @FormParam("raca") String raca,
            @FormParam("porte") String porte,
            @FormParam("especie") String especie
    ) {
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {
            VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();

            List<Variacao> variacoes = variacaoDAO.getAllVariacoes();

            for (Variacao variacao : variacoes) {
                if (raca.equals(variacao.getRaca()) && especie.equals(variacao.getEspecie())) {
                    throw new HttpErrors.BadRequest("Esta variação já foi cadastrada");
                }
            }

            Variacao variacao = new Variacao(raca, porte, especie);

            variacaoDAO.persist(variacao);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Variação cadastrada com sucesso")
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

    }*/
}
