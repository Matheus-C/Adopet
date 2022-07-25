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
            <h1 class="text-center mb-4 pb-5">Cadastro de Variações de Pets</h1>
                <div class="container mt-5 mb-5 pb-5">
                    <form id="formVariacao">

                    <label for="especie" class="form-label">Espécie:</label>
                    <input type="text" name="especie" id="especie" class="form-control" required>

                    <label for="porte" class="form-label">Porte:</label>
                    <input type="text" name="porte" id="porte" class="form-control" required>

                    <label for="raca" class="form-label">Raça:</label>
                    <input type="text" name="raca" id="raca" class="form-control" required>
                    

                    <input type="submit" name="buttonNovaVariacao" id="buttonNovaVariacao" class="btn btn-success mt-3">
                        
                    </form>
                </div>
        </div>
    </body>
</html>