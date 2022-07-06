package com.trabappcorp.Adopet;

import DAO.AdotanteDAO;
import DAO.DoadorDAO;
import DAO.InstituicaoDAO;
import DAO.UsuarioDAO;
import HttpErrors.BadRequest;
import HttpErrors.HttpError;
import Utils.JSONBuilder;
import Utils.ValidationWrapper;
import filters.Authorize;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Adotante;
import modelo.Doador;
import modelo.Endereco;
import modelo.Instituicao;
import modelo.Usuario;

@Path("/auth")
public class AuthenticationResource {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));

    private @Context
    HttpServletRequest servletRequest;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticate(@FormParam("login") String login, @FormParam("senha") String senha) {

        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        JSONBuilder responseJson = new JSONBuilder();
        
        try {

            if (!ValidationWrapper.isSet(login) || !ValidationWrapper.isSet(senha)) {
                throw new HttpErrors.BadRequest("Falta parâmetros");
            }

            Usuario usuarioEncontrado = usuarioDAO.findByLogin(login);

            if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(senha)) {
                String jwtToken = Jwts.builder()
                        .setSubject(usuarioEncontrado.getLogin())
                        .setIssuer("my_app")
                        .setIssuedAt(new Date())
                        .signWith(CHAVE, SignatureAlgorithm.HS256)
                        .compact();

                responseJson.add("token", jwtToken);
                
                return Response.status(Response.Status.OK)
                        .entity(
                                responseJson
                                        .build()
                        ).build();
            } else {
                throw new HttpErrors.Unauthorized("Usuário e/ou senha inválidos");
            }
        } catch (HttpErrors.HttpError httpError) {
            return Response.status(httpError.getStatus())
                    .entity(
                            responseJson
                                    .add("mensagem", httpError.getMessage())
                                    .build()
                    ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(ex.getMessage())
                    .build();
        }
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response register(
            @FormParam("login") String login,
            @FormParam("senha") String senha,
            @FormParam("nome") String nome,
            @FormParam("perfil") String perfil,
            // endereco
            @FormParam("cidade") String cidade,
            @FormParam("bairro") String bairro,
            @FormParam("estado") String estado,
            @FormParam("rua") String rua,
            @FormParam("numero") String numero_str, // int
            @FormParam("complemento") String complemento,
            // pessoa (adotante|doador)
            @FormParam("cpf") String cpf,
            @FormParam("dataNascimento") String dataNascimento_str, // date
            // instituicao
            @FormParam("numAnimais") String num_animais_str, // int
            @FormParam("capacidade") String capacidade_str, // int
            @FormParam("numRegistro") String num_registro,
            @FormParam("certificacoes") String certificacoes,
            @FormParam("razaoSocial") String razaoSocial,
            @FormParam("dataFundacao") String data_fundacao_str // date
    ) {

        JSONBuilder responseJson = new JSONBuilder();

        try {

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuarioEncontrado = usuarioDAO.findByLogin(login);

            if (
                !ValidationWrapper.isSet(login) || !ValidationWrapper.isSet(senha) || 
                !ValidationWrapper.isSet(nome) || !ValidationWrapper.isSet(perfil)
            ) {
                throw new HttpErrors.BadRequest("Parâmetros faltantes ou inválidos");
            }

            if (usuarioEncontrado != null) {
                throw new HttpErrors.BadRequest("Usuário já existe");
            }

            Usuario novoUsuario = null;

            Integer numero = null;
            if (numero_str != null) {
                numero = ValidationWrapper.parseInt("numero", numero_str);
            }

            Endereco endereco = new Endereco(cidade, bairro, estado, rua, numero, complemento);

            if (perfil.equals("doador") || perfil.equals("adotante")) {

                if (!ValidationWrapper.isSet(cpf)) {
                    throw new HttpErrors.BadRequest("Parâmetros faltantes ou inválidos");
                }

                Date dataNascimento = ValidationWrapper.parseDate("dataNascimento", dataNascimento_str);

                if (perfil.equals("doador")) {
                    DoadorDAO doadorDAO = DoadorDAO.getInstance();
                    novoUsuario = new Doador(
                            login, senha, endereco, nome, perfil,
                            cpf, dataNascimento
                    );

                } else if (perfil.equals("adotante")) {
                    AdotanteDAO adotanteDAO = AdotanteDAO.getInstance();
                    novoUsuario = new Adotante(
                            login, senha, endereco, login, perfil,
                            cpf, dataNascimento
                    );

                }
            } else if (perfil.equals("instituicao")) {

                if (!ValidationWrapper.isSet(num_animais_str)
                        || !ValidationWrapper.isSet(capacidade_str)
                        || !ValidationWrapper.isSet(data_fundacao_str)
                        || !ValidationWrapper.isSet(razaoSocial)
                        || !ValidationWrapper.isSet(num_registro)) {
                    throw new HttpErrors.BadRequest("Parâmetros faltantes ou inválidos");
                }

                Integer num_animais = null;
                num_animais = ValidationWrapper.parseInt("numAnimais", num_animais_str);

                Integer capacidade = null;
                capacidade = ValidationWrapper.parseInt("capacidade", capacidade_str);

                Date data_fundacao = null;
                data_fundacao = ValidationWrapper.parseDate("dataFundacao", data_fundacao_str);

                novoUsuario = new Instituicao(
                        login, senha, endereco, nome, perfil,
                        num_animais,
                        capacidade,
                        num_registro,
                        certificacoes,
                        razaoSocial,
                        data_fundacao
                );
            } else {
                throw new BadRequest("Perfil inválido");
            }

            usuarioDAO.persist(novoUsuario);

            return Response.status(Response.Status.OK)
                    .entity(
                            responseJson
                                    .add("mensagem", "Cadastro feito com sucesso")
                                    .add("login", novoUsuario.getLogin())
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
    @Path("check")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response check() {

        JSONBuilder json = new JSONBuilder();

        Usuario usuario = (Usuario) this.servletRequest.getAttribute("usuario");

        return Response.status(Response.Status.OK)
                .entity(
                        json
                                .add("mensagem", "sucesso")
                                .add("login", usuario.getLogin())
                                .add("id", usuario.getId())
                                .build()
                ).build();
    }
}
