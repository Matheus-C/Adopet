package com.trabappcorp.Adopet;

import DAO.PetDAO;
import DAO.VariacaoDAO;
import java.text.SimpleDateFormat;
import modelo.Pet;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Variacao;


@Path("/pets")
public class Pets {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response cadastro(@FormParam("nome") String nome, @FormParam("dataNascimento") String nasc, 
            @FormParam("peso") String peso, @FormParam("altura") String altura, @FormParam("dono") String idDono, 
            @FormParam("gasto") String gasto, @FormParam("obs") String obs, @FormParam("raca") String raca, 
            @FormParam("porte") String porte, @FormParam("especie") String especie, @FormParam("foto") String fotos) {
        
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();
        
        try {
            PetDAO petDAO = PetDAO.getInstance();
            SimpleDateFormat parser = new SimpleDateFormat("dd/mm/yyyy");
            Date dataNasc = parser.parse(nasc);
            Variacao novaVariacao = new Variacao(raca, porte, especie);
            Pet novoPet = new Pet(nome, 
            dataNasc, 
            Double.parseDouble(peso),
            Double.parseDouble(altura),
            Integer.parseInt(idDono),
            novaVariacao,
            Double.parseDouble(gasto), 
            obs,
            fotos);
            
            petDAO.persist(novoPet);
            
            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("message", "cadastro feito com sucesso")
                                    .add("id", novoPet.getId())
                                    .build()
                    ).build();
            
        } catch (Exception ex) {

            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(responseJson.add("message", "Erro interno").build())
                    .build();
        }

    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPet(@PathParam("id") int id){
        PetDAO petDAO = PetDAO.getInstance();
        Pet petEncontrado = petDAO.findById(id);
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();
        if(petEncontrado != null){
            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("nome", petEncontrado.getNome())
                                    .add("dataNasc", petEncontrado.getDataNascimento().toString())
                                    .add("peso", petEncontrado.getPeso().toString())
                                    .add("altura", petEncontrado.getAltura().toString())
                                    .add("especie", petEncontrado.getVariacao().getEspecie())
                                    .add("porte", petEncontrado.getVariacao().getPorte())
                                    .add("raca", petEncontrado.getVariacao().getRaca())
                                    .add("desejadoPor", petEncontrado.getDesejadoPor().size())
                                    .add("gastoMensal", petEncontrado.getGastoMensal().toString())
                                    .add("observacoes", petEncontrado.getObservacoes())
                                    .add("fotos", petEncontrado.getFotos())
                                    .build()
                    ).build();
        }
        return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("message", "não encontrado")
                                    .build()
                    ).build();
    }

    @GET
    @Path("todos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaTodos(@PathParam("id") int id){
        PetDAO petDAO = PetDAO.getInstance();
        List<Pet> petsEncontrados = petDAO.findByIdDono(Integer. parseInt(id));
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(int i = 0; i < petsEncontrados.size(); i++){
                array.add(responseJson.add("nome", petsEncontrados.get(i).getNome())
                .add("dataNasc", petsEncontrados.get(i).getDataNascimento().toString())
                .add("peso", petsEncontrados.get(i).getPeso().toString())
                .add("altura", petsEncontrados.get(i).getAltura().toString())
                .add("especie", petsEncontrados.get(i).getVariacao().getEspecie())
                .add("porte", petsEncontrados.get(i).getVariacao().getPorte())
                .add("raca", petsEncontrados.get(i).getVariacao().getRaca())
                .add("desejadoPor", petsEncontrados.get(i).getDesejadoPor().size())
                .add("gastoMensal", petsEncontrados.get(i).getGastoMensal().toString())
                .add("observacoes", petsEncontrados.get(i).getObservacoes())
                .add("fotos", petsEncontrados.get(i).getFotos())
                )
                .build();
        }
        if(petsEncontrados != null){
            return Response.status(Response.Status.OK)
                    .entity(array).build();
        }
        return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("message", "não encontrado")
                                    .build()
                    ).build();
    }

    @GET
    @Path("filtro/{gasto}/{especie}/{raca}/{porte}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaFiltro(@PathParam("gasto") float gasto, @PathParam("especie") String especie, @PathParam("raca") String raca, @PathParam("porte") String porte){
        VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();
        int variacaoEncontrada = variacaoDAO.getVariacaoFilter(especie, raca);
        PetDAO petDAO = PetDAO.getInstance();
        List<Pet> petsEncontrados = petDAO.findFilter(gasto, variacaoEncontrada);
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(int i = 0; i < petsEncontrados.size(); i++){
                array.add(responseJson.add("nome", petsEncontrados.get(i).getNome())
                .add("dataNasc", petsEncontrados.get(i).getDataNascimento().toString())
                .add("peso", petsEncontrados.get(i).getPeso().toString())
                .add("altura", petsEncontrados.get(i).getAltura().toString())
                .add("especie", petsEncontrados.get(i).getVariacao().getEspecie())
                .add("porte", petsEncontrados.get(i).getVariacao().getPorte())
                .add("raca", petsEncontrados.get(i).getVariacao().getRaca())
                .add("desejadoPor", petsEncontrados.get(i).getDesejadoPor().size())
                .add("gastoMensal", petsEncontrados.get(i).getGastoMensal().toString())
                .add("observacoes", petsEncontrados.get(i).getObservacoes())
                .add("fotos", petsEncontrados.get(i).getFotos())
                )
                .build();
        }
        if(petsEncontrados != null){
            return Response.status(Response.Status.OK)
                    .entity(array).build();
        }
        return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("message", "não encontrado")
                                    .build()
                    ).build();
    }
    @POST
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("id") int id, @FormParam("nome") String nome, @FormParam("dataNascimento") String nasc, 
            @FormParam("peso") String peso, @FormParam("altura") String altura, 
            @FormParam("gasto") String gasto, @FormParam("obs") String obs, @FormParam("raca") String raca, 
            @FormParam("porte") String porte, @FormParam("especie") String especie, @FormParam("foto") String fotos) {
        
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();
        
        try {
            PetDAO petDAO = PetDAO.getInstance();
            SimpleDateFormat parser = new SimpleDateFormat("dd/mm/yyyy");
            Date dataNasc = parser.parse(nasc);
            Variacao novaVariacao = new Variacao(raca, porte, especie);
            Pet novoPet = petDAO.updatePet(id, nome, 
            dataNasc, 
            Double.parseDouble(peso),
            Double.parseDouble(altura),
            novaVariacao,
            Double.parseDouble(gasto), 
            obs,
            fotos);
            
            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("message", "atualização feita com sucesso")
                                    .add("id", novoPet.getId())
                                    .build()
                    ).build();
            
        } catch (Exception ex) {

            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(responseJson.add("message", "Erro interno").build())
                    .build();
        }

    }

    @POST
    @DELETE
    @Path("/desejo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response desejar(@FormParam("idPet") int idPet, @FormParam("idUser") int idUser){
        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();
        
        try {
                PetDAO petDAO = PetDAO.getInstance();
                petDAO.mudarDesejo(idPet, idUser);
                return Response.status(Response.Status.OK)
                        .entity(
                                responseJson
                                        .add("message", "atualização feita com sucesso")
                                        .build()
                        ).build();

        } catch (Exception ex) {

        return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR
        ).entity(responseJson.add("message", "Erro interno").build())
                .build();

    }


}

