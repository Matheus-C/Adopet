package com.trabappcorp.Adopet;

import DAO.AdotanteDAO;
import DAO.DoadorDAO;
import DAO.UsuarioDAO;
import HttpErrors.BadRequest;
import Utils.JSONBuilder;
import Utils.ValidationWrapper;
import filters.Authorize;
import filters.UserParam;
import java.util.Date;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import modelo.Contato;
import modelo.Doador;
import modelo.Endereco;
import modelo.Instituicao;
import modelo.PessoaFisica;
import modelo.Usuario;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class UsersResource {

    private @Context
    HttpServletRequest servletRequest;

    @GET
    @Path("{idUsuario}")
    @UserParam
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam("idUsuario") String id) {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("paramUsuario");

            JSONBuilder usuarioJson = new JSONBuilder();
            JSONBuilder enderecoJson = new JSONBuilder();

            Endereco enderecoUsuario = usuario.getEndereco();
            if (enderecoUsuario != null) {
                enderecoJson
                        .add("bairro", enderecoUsuario.getBairro())
                        .add("cidade", enderecoUsuario.getCidade())
                        .add("estado", enderecoUsuario.getEstado())
                        .add("numero", enderecoUsuario.getNumero())
                        .add("rua", enderecoUsuario.getRua());
            }

            JsonArrayBuilder contatosJson = jsonFactory.createArrayBuilder();
            Set<Contato> contatosUsuario = usuario.getContatos();

            for (Contato contato : contatosUsuario) {
                JsonObjectBuilder contatoJson = jsonFactory.createObjectBuilder();
                contatoJson
                        .add("nome", contato.getNome())
                        .add("info", contato.getInfo())
                        .add("tipo", contato.getTipo());

                contatosJson.add(contatoJson);
            }

            usuarioJson
                    .add("id", usuario.getId())
                    .add("nome", usuario.getNome())
                    .add("perfil", usuario.getPerfil())
                    .add("contatos", contatosJson)
                    .add("endereco", enderecoJson);

            String perfil = usuario.getPerfil();

            switch (perfil) {
                case "doador":
                    Doador doador = (Doador) usuario;
                    usuarioJson
                            .add("CPF", doador.getCPF())
                            .add("dataNascimento", doador.getDataNascimento().toString());
                    break;
                case "adotante":
                    Adotante adotante = (Adotante) usuario;
                    usuarioJson
                            .add("CPF", adotante.getCPF())
                            .add("adotando", adotante.getAdotando())
                            .add("dataNascimento", adotante.getDataNascimento().toString());
                    break;
                case "instituicao":
                    Instituicao instituicao = (Instituicao) usuario;
                    usuarioJson
                            .add("capacidade", instituicao.getCapacidade())
                            .add("certificacoes", instituicao.getCertificacoes())
                            .add("numeroAnimais", instituicao.getNumeroAnimais())
                            .add("numeroRegistro", instituicao.getNumeroRegistro())
                            .add("razaoSocial", instituicao.getRazaoSocial())
                            .add("dataFundacao", instituicao.getDataFundacao().toString());
                    break;
                default:
                    throw new BadRequest("Perfil inválido");

            }

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("usuario", usuarioJson)
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
    @Path("{idUsuario}")
    @UserParam
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateUserInfo(
            @PathParam("idUsuario") String id,
            @FormParam("senha") String senha,
            @FormParam("nome") String nome,
            // endereco
            @FormParam("cidade") String cidade,
            @FormParam("bairro") String bairro,
            @FormParam("estado") String estado,
            @FormParam("rua") String rua,
            @FormParam("numero") String numero_str, // int
            @FormParam("complemento") String complemento,
            // pessoa (instituicao|doador)
            @FormParam("cpf") String cpf,
            @FormParam("adotando") String adotando,
            @FormParam("dataNascimento") String dataNascimento_str, // date
            // instituicao
            @FormParam("numAnimais") String num_animais_str, // int
            @FormParam("capacidade") String capacidade_str, // int
            @FormParam("numRegistro") String num_registro,
            @FormParam("certificacoes") String certificacoes,
            @FormParam("razaoSocial") String razaoSocial,
            @FormParam("dataFundacao") String data_fundacao_str // date
    ) {

        JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder responseJson = jsonFactory.createObjectBuilder();

        try {

            Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");
            Usuario usuarioParametro = (Usuario) this.servletRequest.getAttribute("paramUsuario");

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

            if (!usuario.getId().equals(usuarioParametro.getId())) {
                throw new HttpErrors.Unauthorized("Você não pode alterar este usuário");
            }

            if (ValidationWrapper.isSet(senha)) {
                usuario.setSenha(senha);
            }
            if (ValidationWrapper.isSet(nome)) {
                usuario.setNome(nome);
            }

            Integer numero = null;
            if (numero_str != null) {
                numero = ValidationWrapper.parseInt("numero", numero_str);
            }

            usuario
                    .getEndereco()
                    .atualizarInfo(cidade, bairro, estado, rua, numero, complemento);

            String perfil = usuario.getPerfil();

            if (perfil.equals("adotante") || perfil.equals("doador")) {
                PessoaFisica pessoaFisica = ((PessoaFisica) usuario);

                if (ValidationWrapper.isSet(cpf)) {
                    pessoaFisica.setCPF(cpf);
                }

                Date dataNascimento = null;
                if (dataNascimento_str != null) {
                    dataNascimento = ValidationWrapper.parseDate("dataNascimento", dataNascimento_str);
                    pessoaFisica.setDataNascimento(dataNascimento);
                }

                if (perfil.equals("doador")) {
                    Doador doador = (Doador) usuario;

                } else if (perfil.equals("adotante")) {
                    Adotante adotante = (Adotante) usuario;

                    if (adotando != null) {
                        adotante.setAdotando(adotando.equals("true"));
                    }
                }
            } else if (perfil.equals("instituicao")) {
                Instituicao instituicao = (Instituicao) usuario;

                if (num_animais_str != null) {
                    Integer num_animais = null;
                    num_animais = ValidationWrapper.parseInt("numAnimais", num_animais_str);
                    instituicao.setNumeroAnimais(num_animais);
                }
                if (capacidade_str != null) {
                    Integer capacidade = null;
                    capacidade = ValidationWrapper.parseInt("capacidade", capacidade_str);
                    instituicao.setCapacidade(capacidade);
                }
                if (dataNascimento_str != null) {
                    Date data_fundacao = null;
                    data_fundacao = ValidationWrapper.parseDate("dataFundacao", data_fundacao_str);
                    instituicao.setDataFundacao(data_fundacao);
                }

                if (certificacoes != null) {
                    instituicao.setCertificacoes(certificacoes);
                }

                if (num_registro != null) {
                    instituicao.setNumeroRegistro(num_registro);
                }

                if (razaoSocial != null) {
                    instituicao.setRazaoSocial(razaoSocial);
                }

            } else {
                throw new BadRequest("Perfil inválido");
            }

            usuarioDAO.persist(usuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Usuário atualizado com sucesso")
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
