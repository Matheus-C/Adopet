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
        <%
        WebTarget wtAdotar;
        Client client = ClientBuilder.newClient();
        URI uriAdotar = new URI("http://localhost:8080/Adopet/sample");
        wtAdotar = client.target(uriAdotar).path("pets/cadastrados");
        Invocation.Builder builderAdotar = wtAdotar.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU");
        Response respostaAdotar = builderAdotar.get();
        if (respostaAdotar.getStatus() != 200) {
                JsonObject respostaMsg = respostaAdotar.readEntity(JsonObject.class);
        %>
        <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5"><% out.println(respostaMsg.getString("mensagem"));%></h1><%
        } else {
            JsonObject jsonObject = respostaAdotar.readEntity(JsonObject.class);
            JsonArray jsonArray = jsonObject.getJsonArray("pets");
        %>
        <!-- menu do topo da página -->
        <jsp:include page="menu.jsp"/>      
         <div class="container mt-4 mb-4" id="content">
            <h1 class="text-center mb-4 pb-5">Meus pets registrados</h1>
            <div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-4 g-4">     
        <%  //início do loop que recupera e renderiza um objeto contido no JSON Array
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.getJsonObject(i);   
        %>
            <div class="col">
                    <div class="card h-100 border-success shadow">
                        <div class="card-body text-center">
                            
                            <img src="https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-1100x628.jpg" class="card-img-top" alt="placeholder" class="border border-success">
                            <h5 class="card-title text-center mt-4"><% out.println(obj.getString("nome")); %></h5> 
                            <hr class="mt-2 mb-3"/>
                            <p class="card-text text-center"></p>
                                
                                <!-- renderizando atributos do objeto -->
                                <p><strong>Altura:</strong><br><% out.println(obj.getInt("altura"));%>cm<br></p>
                                <p><strong>Peso:</strong><br><% out.println(obj.getInt("peso"));%>kg<br></p>
                                <p><strong>Gasto Mensal:</strong><br>R$<% out.println(obj.getInt("gastoMensal"));%><br></p>
                                <p><strong>Data de Nascimento:</strong><br><% 
                                    
                                    out.println(obj.getString("dataNascimento")); %><br></p>
                                <p><strong>Observações:</strong> <br><% out.println(obj.getString("observacoes")); %><br></p>
                                
                                
                                
                                                         
                        </div>
                                
                        <!-- criando botão "Adotar Pets" -->
                        <div class="card-footer text-center">         
                            <a id="buttonPetDel" class="btn btn-danger">Remover</a>
                        </div>  
                    </div>
                </div>    
        <% } %> 
        <!-- fim do loop  -->                  
            </div>
        </div>  
        <% } %> 
    </body>
</html>
