package com.trabappcorp.Adopet;

import DAO.PetDAO;
import DAO.UsuarioDAO;
import DAO.VariacaoDAO;
import Utils.JSONArrayBuilder;
import Utils.JSONBuilder;
import Utils.ValidationWrapper;
import filters.Authorize;
import filters.UserParam;
import java.text.SimpleDateFormat;
import modelo.Pet;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Adotante;
import modelo.FiltroAdotante;
import modelo.Usuario;
import modelo.Variacao;

@Path("/adotantes")
public class AdotantesResource {

    private @Context
    HttpServletRequest servletRequest;

    @GET
    @Path("/filtro")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFiltro() {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("adotante")) {
                throw new HttpErrors.Unauthorized("Usuário autenticado não é um adotante");
            }

            Adotante adotante = (Adotante) usuario;

            FiltroAdotante filtro = adotante.getFiltro();

            if (filtro == null) {
                filtro = new FiltroAdotante();
            }

            JSONBuilder filtroJson = new JSONBuilder();
            filtroJson
                    .add("id", filtro.getId())
                    .add("alturaMenor", filtro.getAlturaMenor())
                    .add("gastoMensalMenor", filtro.getGastoMensalMenor())
                    .add("pesoMenor", filtro.getPesoMenor())
                    .add("porte", filtro.getPorte())
                    .add("raca", filtro.getRaca())
                    .add("especie", filtro.getEspecie());

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("filtroAdotante", filtroJson)
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

    @POST
    @Path("/filtro")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateFiltro(
            @FormParam("alturaMenor") String alturaMenor_str,
            @FormParam("gastoMensalMenor") String gastoMensalMenor_str,
            @FormParam("pesoMenor") String pesoMenor_str,
            @FormParam("raca") String raca,
            @FormParam("porte") String porte,
            @FormParam("especie") String especie
    ) {
        JSONBuilder responseJson = new JSONBuilder();

        try {
            
            Long id = 12L;
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuario = usuarioDAO.getById(id);
            Adotante adotante = (Adotante) usuario;

            FiltroAdotante filtro = adotante.getFiltro();

            if (ValidationWrapper.isSet(raca)) {
                if(raca.equals("null")) raca = null;
                filtro.setRaca(raca);
            }
            if (ValidationWrapper.isSet(especie)) {
                if(especie.equals("null")) especie = null;
                filtro.setEspecie(especie);
            }
            if (ValidationWrapper.isSet(porte)) {
                if(porte.equals("null")) porte = null;
                filtro.setPorte(porte);
            }

            if (ValidationWrapper.isSet(alturaMenor_str)) {                
                if(alturaMenor_str.equals("null")) filtro.setAlturaMenor(null);
                else {
                    Double alturaMenor = ValidationWrapper.parseDouble("alturaMenor", alturaMenor_str);
                    filtro.setAlturaMenor(alturaMenor);
                }
            }
            if (ValidationWrapper.isSet(gastoMensalMenor_str)) {                
                if(gastoMensalMenor_str.equals("null")) filtro.setGastoMensalMenor(null);
                else {                
                    Double gastoMensalMenor = ValidationWrapper.parseDouble("gastoMensalMenor", gastoMensalMenor_str);
                    filtro.setGastoMensalMenor(gastoMensalMenor);
                }
            }
            if (ValidationWrapper.isSet(pesoMenor_str)) {
                if(pesoMenor_str.equals("null")) filtro.setPesoMenor(null);
                else {                
                    Double pesoMenor = ValidationWrapper.parseDouble("pesoMenor", pesoMenor_str);
                    filtro.setPesoMenor(pesoMenor);
                }
            }

            
            usuarioDAO.persist(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Filtro atualizado com sucesso")
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
