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
            wt = client.target(uri).path("instituicoes/adotar");
            Invocation.Builder builder = wt.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXdhbnRzcGV0czEyMyIsImlzcyI6Im15X2FwcCIsImlhdCI6MTY1NzYwMDU3Mn0.3KyezbQ6id46fYKzpkqU-bJ5vz7IdoHpCiuqzo7KQX8");
            Response resposta = builder.get();
            if (resposta.getStatus() != 200) {
                JsonObject respostaMsg = resposta.readEntity(JsonObject.class);
            %>
            <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5"><% out.println(respostaMsg.getString("mensagem"));%></h1><%
            } else {
            JsonObject jsonObject = resposta.readEntity(JsonObject.class);
            JsonArray jsonArray = jsonObject.getJsonArray("instituicoes");
        %>   
        <!-- menu do topo da página -->
        <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5">Adote nas melhores instituições!</h1>
            <div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-4 g-4">     
        <%  //início do loop que recupera e renderiza um objeto contido no JSON Array
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.getJsonObject(i);   
        %>
                <div class="col">
                    <div class="card h-100 border-success shadow">
                        <div class="card-body text-center">
                            <h5 class="card-title text-center"><% out.println(obj.getString("nome")); %></h5>
                            <hr class="mt-2 mb-3"/>
                            <p class="card-text text-center"></p>
                            
                                <!-- renderizando atributos do objeto -->
                                <p><strong>Razão Social:</strong> <br><% out.println(obj.getString("razaoSocial")); %><br></p>
                                <p><strong>Nº de Registro:</strong> <br><% out.println(obj.getString("numeroRegistro")); %><br></p>
                                <p><strong>Certificações:</strong> <br><% out.println(obj.getString("certificacoes")); %><br></p>
                                <p><strong>Data de Fundação:</strong><br><% 
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:s.S");
                                    LocalDate date = LocalDate.parse(obj.getString("dataFundacao"), formatter);
                                    out.println(date); %><br></p>
                                <hr class="mt-2 mb-3"/>
                                
                                <!-- renderizando Endereço e Contatos e criando seus botões -->
                                <div class="btn-group-vertical">
                                    <button type="button" class="btn btn-primary text-center" data-bs-toggle="popover" data-bs-trigger="focus" title="Contatos" data-bs-placement="top" data-bs-content="<%
                                            JsonArray contatos = obj.getJsonArray("contatos");
                                            if (contatos.isEmpty())
                                                out.println("Não há informações de contato.");
                                            else
                                            for (int j = 0; j < contatos.size(); j++) {
                                                JsonObject contato = contatos.getJsonObject(j);
                                                out.println(contato.getString("nome") + " -");
                                                out.println(contato.getString("tipo") + " -");
                                                out.println(contato.getString("info"));
                                            }
                                            %>">Contatos</button>
                                            
                                    <button type="button" class="btn btn-primary text-center mt-2" data-bs-toggle="popover" data-bs-trigger="focus" title="Endereço" data-bs-placement="top" data-bs-content="<% 
                                        JsonObject endereco = obj.getJsonObject("endereco");
                                        if (endereco.isEmpty())
                                                out.println("Não há informações de endereço.");
                                        else
                                        out.println(endereco.getString("estado") + " -");
                                        out.println(endereco.getString("cidade") + " -");
                                        out.println(endereco.getString("bairro") + " -");
                                        out.println(endereco.getString("rua") + " -");
                                        out.println("nº "+ endereco.getInt("numero"));
                                        %>">Endereço</button>
                                </div>                                
                        </div>
                                
                        <!-- criando botão "Adotar Pets" -->
                        <div class="card-footer text-center">
                            <a href="getPetsInstituicao.jsp?id=<%out.println(obj.getInt("id"));%>" class="btn btn-success">Adotar Pets</a>
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
