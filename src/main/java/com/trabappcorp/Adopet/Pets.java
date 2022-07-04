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
import modelo.Usuario;
import modelo.Variacao;

@Path("/pets")
public class Pets {

    private @Context
    HttpServletRequest servletRequest;

    @GET
    @Path("/variacoes")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVariacoes() {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            JSONArrayBuilder variacoesJson = new JSONArrayBuilder();

            VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();

            List<Variacao> variacoes = variacaoDAO.getAllVariacoes();

            for (Variacao variacao : variacoes) {
                JSONBuilder variacaoJson = new JSONBuilder();
                variacaoJson
                        .add("id", variacao.getId())
                        .add("raca", variacao.getRaca())
                        .add("porte", variacao.getPorte())
                        .add("especie", variacao.getEspecie());

                variacoesJson.add(variacaoJson);
            }

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
    @Path("/variacoes")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createVariacao(
            @FormParam("raca") String raca,
            @FormParam("porte") String porte,
            @FormParam("especie") String especie
    ) {
        JSONBuilder responseJson = new JSONBuilder();

        try {

            Variacao novaVariacao = new Variacao();

            if (ValidationWrapper.isSet(raca)) {
                novaVariacao.setRaca(raca);
            } else {
                throw new HttpErrors.BadRequest("Parametros faltantes ou inválidos");
            }

            if (ValidationWrapper.isSet(especie)) {
                novaVariacao.setEspecie(especie);
            } else {
                throw new HttpErrors.BadRequest("Parametros faltantes ou inválidos");
            }

            if (ValidationWrapper.isSet(porte)) {
                novaVariacao.setPorte(porte);
            } else {
                throw new HttpErrors.BadRequest("Parametros faltantes ou inválidos");
            }

            VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();
            Variacao variacaoExists = variacaoDAO.findExistingVariacao(porte, especie, raca);

            if (variacaoExists != null) {
                throw new HttpErrors.BadRequest("Variacao identica já existe");
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

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Authorize
    public Response registerPet(
            @FormParam("nome") String nome,
            @FormParam("dataNascimento") String dataNascimento_str,
            @FormParam("peso") String peso_str,
            @FormParam("altura") String altura_str,
            @FormParam("gastoMensal") String gastoMensal_str,
            @FormParam("obs") String obs,
            @FormParam("idVariacao") String idVariacao_str,
            @FormParam("fotos") String fotos) {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            if (!ValidationWrapper.isSet(nome) || !ValidationWrapper.isSet(dataNascimento_str) || !ValidationWrapper.isSet(peso_str)
                    || !ValidationWrapper.isSet(altura_str) || !ValidationWrapper.isSet(gastoMensal_str)
                    || !ValidationWrapper.isSet(idVariacao_str) || !ValidationWrapper.isSet(fotos)) {
                throw new HttpErrors.BadRequest("Faltam parâmetros");
            }

            Date dataNascimento = ValidationWrapper.parseDate("dataNascimento", dataNascimento_str);
            double peso = ValidationWrapper.parseDouble("peso", peso_str);
            double altura = ValidationWrapper.parseDouble("altura", altura_str);
            double gastoMensal = ValidationWrapper.parseDouble("gastoMensal", gastoMensal_str);
            long idVariacao = ValidationWrapper.parseLongId("idVariacao", idVariacao_str);

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("instituicao") && !usuario.getPerfil().equals("doador")) {
                throw new HttpErrors.BadRequest("Usuário autenticado não é um doador ou instituição");
            }

            VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();

            Variacao variacao = variacaoDAO.getById(idVariacao);

            if (variacao == null) {
                throw new HttpErrors.NotFound("A variação não foi encontrada");
            }

            PetDAO petDAO = PetDAO.getInstance();
            Pet novoPet = new Pet(nome, dataNascimento, peso, altura, usuario, variacao, gastoMensal, obs, fotos);
            petDAO.persist(novoPet);

            usuario.getPetsCadastrados().add(novoPet);
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            usuarioDAO.persist(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Cadastro de Pet realizado com sucesso")
                                    .add("id", novoPet.getId())
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
            ).entity(responseJson.add("message", "Erro interno").build())
                    .build();
        }

    }

    @POST
    @Path("/{idPet}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Authorize
    public Response updatePet(
            @PathParam("idPet") String idPet_str,
            @FormParam("nome") String nome,
            @FormParam("dataNascimento") String dataNascimento_str,
            @FormParam("peso") String peso_str,
            @FormParam("altura") String altura_str,
            @FormParam("gastoMensal") String gastoMensal_str,
            @FormParam("obs") String obs,
            @FormParam("idVariacao") String idVariacao_str,
            @FormParam("fotos") String fotos,
            @FormParam("adotado") String adotado_str) {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            if (!ValidationWrapper.isSet(nome)
                    && !ValidationWrapper.isSet(dataNascimento_str)
                    && !ValidationWrapper.isSet(peso_str)
                    && !ValidationWrapper.isSet(altura_str)
                    && !ValidationWrapper.isSet(gastoMensal_str)
                    && !ValidationWrapper.isSet(idVariacao_str)
                    && !ValidationWrapper.isSet(obs)
                    && !ValidationWrapper.isSet(fotos)
                    && !ValidationWrapper.isSet(adotado_str)) {
                throw new HttpErrors.BadRequest("Faltam parâmetros");
            }

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            PetDAO petDAO = PetDAO.getInstance();

            Long idPet = ValidationWrapper.parseLongId("idPet", idPet_str);

            Pet pet = petDAO.getById(idPet);

            if(pet == null) throw new HttpErrors.NotFound("Pet não encontrado");
            
            if (!pet.getDono().getId().equals(usuario.getId())) {
                throw new HttpErrors.Unauthorized("Você não tem permissão para alterar este Pet");
            }

            if (ValidationWrapper.isSet(nome)) {
                pet.setNome(nome);
            }
            if (ValidationWrapper.isSet(obs)) {
                pet.setObservacoes(obs);
            }
            if (ValidationWrapper.isSet(fotos)) {
                pet.setFotos(fotos);
            }

            if (ValidationWrapper.isSet(dataNascimento_str)) {
                Date dataNascimento = ValidationWrapper.parseDate("dataNascimento", dataNascimento_str);
                pet.setDataNascimento(dataNascimento);
            }
            if (ValidationWrapper.isSet(peso_str)) {
                double peso = ValidationWrapper.parseDouble("peso", peso_str);
                pet.setPeso(peso);
            }
            if (ValidationWrapper.isSet(altura_str)) {
                double altura = ValidationWrapper.parseDouble("altura", altura_str);
                pet.setAltura(altura);
            }
            if (ValidationWrapper.isSet(gastoMensal_str)) {
                double gastoMensal = ValidationWrapper.parseDouble("gastoMensal", gastoMensal_str);
                pet.setGastoMensal(gastoMensal);
            }
            if (ValidationWrapper.isSet(adotado_str)) {
                boolean adotado = ValidationWrapper.parseBoolean("adotado", adotado_str);
                pet.setAdotado(adotado);
            }
            if (ValidationWrapper.isSet(idVariacao_str)) {
                long idVariacao = ValidationWrapper.parseLongId("idVariacao", idVariacao_str);
                VariacaoDAO variacaoDAO = VariacaoDAO.getInstance();
                Variacao variacao = variacaoDAO.getById(idVariacao);
                if (variacao == null) {
                    throw new HttpErrors.NotFound("A Variação não foi encontrada");
                }

                pet.setVariacao(variacao);
            }

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

            petDAO.persist(pet);
            usuarioDAO.refresh(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Pet atualizado com sucesso")
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
            ).entity(responseJson.add("message", "Erro interno").build())
                    .build();
        }

    }

    @GET
    @Path("/cadastrados")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegisteredPets() {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            JSONArrayBuilder petsJson = new JSONArrayBuilder();

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");
            List<Pet> pets = usuario.getPetsCadastrados();

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

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(responseJson.add("mensagem", "Erro interno").build())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idPet}/desejo")
    @Authorize
    public Response wishPet(
            @PathParam("idPet") String idPet_str
    ) {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            long idPet = ValidationWrapper.parseLongId("idPet", idPet_str);

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("adotante")) {
                throw new HttpErrors.BadRequest("Usuário autenticado não é um adotante");
            }

            Adotante adotante = (Adotante) usuario;

            PetDAO petDAO = PetDAO.getInstance();

            Pet pet = petDAO.getById(idPet);

            if (pet == null) {
                throw new HttpErrors.NotFound("O Pet não foi encontrado");
            }

            adotante.getPetsDesejados().add(pet);
            pet.getDesejadoPor().add(adotante);

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

            petDAO.persist(pet);
            usuarioDAO.persist(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Pet foi marcado como desejado com sucesso")
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
            ).entity(responseJson.add("message", "Erro interno").build())
                    .build();
        }

    }

    @GET
    @Path("/desejados")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWishedPets() {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            JSONArrayBuilder petsJson = new JSONArrayBuilder();

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("adotante")) {
                throw new HttpErrors.BadRequest("Usuário autenticado não é um adotante");
            }

            Adotante adotante = (Adotante) usuario;

            Set<Pet> pets = adotante.getPetsDesejados();

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

    @DELETE
    @Path("/{idPet}/desejo")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeWish(@PathParam("idPet") String idPet_str) {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("adotante")) {
                throw new HttpErrors.BadRequest("Usuário autenticado não é um adotante");
            }

            Adotante adotante = (Adotante) usuario;

            long idPet = ValidationWrapper.parseLongId("idPet", idPet_str);

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            PetDAO petDAO = PetDAO.getInstance();
            Pet pet = petDAO.getById(idPet);

            if (pet == null) {
                throw new HttpErrors.NotFound("O Pet não foi encontrado");
            }

            adotante.getPetsDesejados().remove(pet);
            pet.getDesejadoPor().remove(adotante);

            petDAO.persist(pet);
            usuarioDAO.persist(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Desejo removido com sucesso")
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

    @DELETE
    @Path("/{idPet}")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePet(@PathParam("idPet") String idPet_str) {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            long idPet = ValidationWrapper.parseLongId("idPet", idPet_str);

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            PetDAO petDAO = PetDAO.getInstance();
            Pet pet = petDAO.getById(idPet);

            if (pet == null) {
                throw new HttpErrors.NotFound("O Pet não foi encontrado");
            }

            if (!pet.getDono().getId().equals(usuario.getId())) {
                throw new HttpErrors.BadRequest("Você não tem permissão para remover este Pet");
            }

            petDAO.remove(pet);
            usuarioDAO.refresh(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Pet removido com sucesso")
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
    @Path("/encontrar")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPets() {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            JSONArrayBuilder petsJson = new JSONArrayBuilder();

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

            if (!usuario.getPerfil().equals("adotante")) {
                throw new HttpErrors.BadRequest("Usuário autenticado não é um adotante");
            }

            Adotante adotante = (Adotante) usuario;

            List<Pet> pets = PetDAO.getInstance().findPetsFor(adotante);

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
