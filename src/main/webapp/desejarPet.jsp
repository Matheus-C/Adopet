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
    </head>
    <body>
        <%
        WebTarget wtAdotar;
        Client client = ClientBuilder.newClient();
        URI uriAdotar = new URI("http://localhost:8080/Adopet/sample");
        wtAdotar = client.target(uriAdotar).path("pets/" + request.getParameter("id") + "/desejo");
        Invocation.Builder builderAdotar = wtAdotar.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXdhbnRzcGV0czEyMyIsImlzcyI6Im15X2FwcCIsImlhdCI6MTY1NzYwMDU3Mn0.3KyezbQ6id46fYKzpkqU-bJ5vz7IdoHpCiuqzo7KQX8");
        Response respostaAdotar = builderAdotar.get();
        if (respostaAdotar.getStatus() != 200) {
                JsonObject respostaMsg = respostaAdotar.readEntity(JsonObject.class);
        %>
            <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5"><% out.println(respostaMsg.getString("mensagem"));%></h1><%
            } else {
        %>
        <!-- menu do topo da página -->
        <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5">Pet adicionado à sua lista de desejados!</h1>
    <% } %>
    </body>
</html>
