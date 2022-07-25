<%@page import="javax.json.JsonValue"%>
<%@page import="Utils.JSONBuilder"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="javax.ws.rs.core.HttpHeaders"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="javax.ws.rs.client.Invocation"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="java.net.URI"%>
<%@page import="javax.ws.rs.client.ClientBuilder"%>
<%@page import="javax.ws.rs.client.Client"%>
<%@page import="javax.ws.rs.client.WebTarget"%>
<%@page import="javax.json.JsonReader"%>
<%@page import="javax.json.JsonArray"%>
<%@page import="javax.json.JsonObject"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.json.JsonReaderFactory"%>
<%@page import="javax.json.Json"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="cabecalho.html"/>
        <script src="js/response.js"></script>
    </head>
<body>
    <jsp:include page="menu.jsp"/>
        <div class="container mt-4 mb-4" id="content">
            <h1 class="text-center mb-4 pb-5">Projeto Adopet!</h1>
                
                <div class="container mt-5 mb-5 pb-5">
                    <a href="login.jsp" class="btn btn-lg btn-primary mt-2 mb-2 align-items-center" role="button">Login</a>
                    <div class="row">
                        <h1>Instituição</h1>
                        <div class="col-sm">
                          <a href="cadastrarInstituicao.jsp" class="btn btn-primary mt-1 mb-2" role="button">Cadastre uma Instituição</a>
                          <a href="cadastrarVariacao.jsp" class="btn btn-primary mt-1 mb-2" role="button">Cadastre uma Variação de Pet</a>
                          <a href="meusPets.jsp" class="btn btn-primary mt-1 mb-2" role="button">Listar Pets</a>
                          <a href="cadastrarPet.jsp" class="btn btn-primary mt-1 mb-2" role="button">Cadastre um Pet</a>
                          <a href="editarPet.jsp" class="btn btn-primary mt-1 mb-2" role="button">Edite um Pet</a>
                          <a href="editarUsuario.jsp" class="btn btn-primary mt-1 mb-2" role="button">Editar Usuário</a>
                          <a href="infoUsuario.jsp" class="btn btn-primary mt-1 mb-2" role="button">Dados do Usuário</a>
                        </div>
                        <br>
                        <h1>Adotante</h1>
                        <div class="col-sm">
                          <a href="cadastrarUsuario.jsp" class="btn btn-primary mt-1 mb-2" role="button">Cadastre um Adotante</a>
                          <a href="meuFiltro.jsp" class="btn btn-primary mt-1 mb-2" role="button">Listar filtro para Adoção</a>
                          <a href="editarFiltro.jsp" class="btn btn-primary mt-1 mb-2" role="button">Edite filtro para Adoção</a>
                          <a href="getInstituicoesAdocao.jsp" class="btn btn-primary mt-1 mb-2" role="button">Instituições para Adoção</a>
                          <a href="meusPetsDesejados.jsp" class="btn btn-primary mt-1 mb-2" role="button">Meus Pets Desejados</a>
                        </div>
                        <br>
                        <h1>Doador</h1>
                        <div class="col-sm">
                          <a href="cadastrarUsuario.jsp" class="btn btn-primary mt-1 mb-2" role="button">Cadastre um Doador</a>
                          <a href="getInstituicoesDoacao.jsp" class="btn btn-primary mt-1 mb-2" role="button">Instituições para Doação</a>
                        </div>
                    </div>
                </div>
        </div>
</body>
</html>
