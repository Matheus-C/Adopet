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
        <script src="js/funcoes.js"></script>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <div class="container mt-4 mb-4" id="content">
            <h1 class="text-center mb-4 pb-5">Cadastro de usuários</h1>
                <div class="container mt-5 mb-5 pb-5">
                    <form id="formUsuario">
                        <label for="tipo" class="form-label">Você é uma:</label>
                        <select name="tipo" id="tipo" class="form-select">
                            <option disabled selected value> -- Selecione uma opção -- </option>
                            <option value="1">pessoa fí­sica</option>
                        </select>
                        <br>
                        <div class="form-group">
                            <label for="perfil" class="form-label">Você quer:</label>
                            <select name="perfil" id="perfil" class="form-select">
                                <option value="adotante">adotar</option>
                                <option value="doador">doar</option>
                            </select>
                        </div>
                        <br>
                        <label for="nome" class="form-label">Digite seu nome completo: </label><input type="text" name="nome" id="nome" class="form-control">
                        
                        <label for="login" class="form-label">Informe um nome de usuario:</label><input type="text" name="login" id="login" class="form-control">

                        <label for="senha" class="form-label">Informe uma senha:</label><input type="password" name="senha" id="senha" class="form-control">

                        
                        <div class="form-group">
                            <label for="estado" class="form-label">Estado:</label>
                            <input type="text" name="estado" id="estado" class="form-control">
                            <label for="cidade" class="form-label">Cidade:</label>
                            <input type="text" name="cidade" id="cidade" class="form-control">
                            <label for="bairro" class="form-label">Bairro:</label>
                            <input type="text" name="bairro" id="bairro" class="form-control">
                            <label for="rua" class="form-label">Rua:</label>
                            <input type="text" name="rua" id="rua" class="form-control">
                            <label for="numero" class="form-label">Número:</label>
                            <input type="number" name="numero" id="numero" class="form-control">
                            <label for="complemento" class="form-label">Complemento:</label>
                            <input type="text" name="complemento" id="complemento" class="form-control">
                        </div>
                        <br>
                        <div class="form-group" id="pessoaFisica">
                            <label for="cpf" class="form-label">CPF:</label>
                            <input type="text" name="cpf" id="cpf" class="form-control">
                            <label for="dataNascimento" class="form-label">Data de nascimento:</label>
                            <input type="date" name="dataNascimento" id="dataNascimento" class="form-control">
                        </div>
                        <br>
                        <input type="submit" name="buttonFormUsuario" id="buttonFormUsuario" class="btn btn-success">
                        
                    </form>
                </div>
        </div>
    </body>
</html>