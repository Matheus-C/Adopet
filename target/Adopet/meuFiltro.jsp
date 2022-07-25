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
    </head>
    <body>
        <%//construindo e enviando request ao endpoint e gerando um JSON Array a partir da response
            WebTarget wt;
            Client client = ClientBuilder.newClient();
            URI uri = new URI("http://localhost:8080/Adopet/sample");
            wt = client.target(uri).path("adotantes/filtro");
            Invocation.Builder builder = wt.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXdhbnRzcGV0czEyMyIsImlzcyI6Im15X2FwcCIsImlhdCI6MTY1ODM3ODQ1NX0.ZazL4Z1lyil40ndDkyRuo9t0_blPfA_LgCmG8G88OfE");
            Response resposta = builder.get();
            if (resposta.getStatus() != 200) {
                JsonObject respostaMsg = resposta.readEntity(JsonObject.class);
        %>
        <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5"><% out.println(respostaMsg.getString("mensagem"));%></h1></div><%
            } else {
            JsonObject respostaJson = resposta.readEntity(JsonObject.class);
            JsonObject obj = respostaJson.getJsonObject("filtroAdotante");
        %>   
        <!-- menu do topo da página -->
        <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5">Configurações de filtro de pets para adoção</h1>
                <div class="container mt-5 mb-5 pb-5">
                    <h5 class="text-center">Filtro Atual Para Adoção de Pets</h5>
                    <hr class="mt-2 mb-3"/>
                    <p class="text-center"><strong>Menor Altura:</strong> <br><% out.println(obj.getInt("alturaMenor")); %><br>
                    <strong>Menor Gasto Mensal:</strong> <br><% out.println(obj.getInt("gastoMensalMenor")); %><br>
                    <strong>Menor Peso:</strong> <br><% out.println(obj.getInt("pesoMenor")); %><br>
                    <strong>Espécie:</strong> <br><% out.println(obj.getString("especie")); %><br>
                    <strong>Raça:</strong> <br><% out.println(obj.getString("raca")); %><br>
                    <strong>Porte:</strong> <br><% out.println(obj.getString("porte")); %><br></p>
                    <hr class="mt-2 mb-3"/>                                        
                </div> 
        </div>
        <% } %>     
    </body>
</html>
