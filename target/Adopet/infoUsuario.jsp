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
        <%//construindo e enviando request ao endpoint e gerando um JSON Array a partir da response
            WebTarget wt;
            Client client = ClientBuilder.newClient();
            URI uri = new URI("http://localhost:8080/Adopet/sample");
            wt = client.target(uri).path("auth/check");
            Invocation.Builder builder = wt.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU");
            Response resposta = builder.get();
            if (resposta.getStatus() != 200) {
                JsonObject respostaMsg = resposta.readEntity(JsonObject.class);
            %>
            <jsp:include page="menu.jsp"/>      
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5"><% out.println(respostaMsg.getString("mensagem"));%></h1><%
            } else {
            JsonObject obj = resposta.readEntity(JsonObject.class);
            wt = client.target(uri).path("users/" + obj.getInt("id"));
            builder = wt.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU");
            resposta = builder.get();
            obj = resposta.readEntity(JsonObject.class);
            obj = obj.getJsonObject("usuario");
            JsonObject endereco = obj.getJsonObject("endereco");

            wt = client.target(uri).path("users/" + obj.getInt("id") + "/contatos");
            builder = wt.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU");
            resposta = builder.get();
            JsonObject contatosJson = resposta.readEntity(JsonObject.class);
            JsonArray contatos = contatosJson.getJsonArray("contatos");
            if(obj.getString("perfil").equals("instituicao")){   
        %>   
        <!-- menu do topo da página -->
        <jsp:include page="menu.jsp"/>      
        <div class="container mt-4" id="content">
            <h1 class="text-center mb-4 pb-5">Informações da Instituição</h1>
                        <div class="container text-center mt-5">
                            <h3 class="text-center"><% out.println(obj.getString("nome")); %></h3>
                            <hr class="mt-2 mb-3"/>
                            <p class="text-center">
                            <p><strong>Capacidade:</strong> <br><% out.println(obj.getInt("capacidade")); %><br></p>
                            <p><strong>Nº de Animais:</strong> <br><% out.println(obj.getInt("numeroAnimais")); %><br></p>
                            <p><strong>Razão Social:</strong> <br><% out.println(obj.getString("razaoSocial")); %><br></p>
                            <p><strong>Nº de Registro:</strong> <br><% out.println(obj.getString("numeroRegistro")); %><br></p>
                            <p><strong>Certificações:</strong> <br><% out.println(obj.getString("certificacoes")); %><br></p>
                            <p><strong>Data de Fundação:</strong><br><% 
                                    out.println(obj.getString("dataFundacao")); %><br>
                                    
                                    
                                <div class="container text-center mt-5 mb-5">
                                    <h3 class="text-center">Contatos</h3>
                                    <hr class="mt-2 mb-3"/>
                                    <%
                                            if (obj.getJsonArray("contatos").isEmpty()){
                                                %>
                                            <p><strong>Sem contatos: </strong> <br><% out.println("Não há informações de contato."); %><br></p><%
                                            } else {
                                            for (int j = 0; j < contatos.size(); j++) {
                                                JsonObject contato = contatos.getJsonObject(j); %>
                                            <p><strong>Nome: </strong> <br> <% out.println(contato.getString("nome")); %> <br></p>
                                            <p><strong>Tipo: </strong> <br> <%   out.println(contato.getString("tipo")); %> <br></p>
                                            <p><strong>Info: </strong> <br> <%   out.println(contato.getString("info")); %> <br></p>
                                            <a class="btn btn-danger" id="buttonContatoDel" role="button">Remover Contato</a>
                                            <%    }}
                                            %>
                                <div class="container mt-2 mb-2">
                                    <a href="cadastrarContato.jsp" class="btn btn-primary">Novo Contato</a>
                                </div>
                                </div>

                                <div class="container text-center mb-5">
                                    <h3 class="text-center">Endereço</h3>
                                    <hr class="mt-2 mb-3"/>
                                    <% 
                                        if (obj.isNull("endereco")) {
                                            %>
                                        <p><strong>Sem contatos: </strong> <br> <% out.println("Não há informações de endereço.");
                                        %><br></p><%
                                        } else { %>
                                        <p><strong>Estado: </strong> <br> <% out.println(endereco.getString("estado")); %><br></p>
                                        <p><strong>Cidade: </strong> <br> <% out.println(endereco.getString("cidade")); %><br></p>
                                        <p><strong>Bairro: </strong> <br> <% out.println(endereco.getString("bairro")); %><br></p>
                                        <p><strong>Rua: </strong> <br> <% out.println(endereco.getString("rua")); %><br></p>
                                        <p><strong>Numero: </strong> <br> <% out.println("nº "+ endereco.getInt("numero")); %><br></p>
                                        <% }%>
                                </div>      
                                <hr class="mt-2 mb-3"/>
                                <div class="container mt-2 mb-2">
                                    <a href="editarInstituicao.jsp?id=<%out.println(obj.getInt("id"));%>" class="btn btn-success">Editar Usuário</a>
                                </div>
                            </div>      
                        </div>
                                
         
        
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                            
                                
        <% }
        if(obj.getString("perfil").equals("adotante")){
        %>
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5">Informações do(a) Adotante</h1>
                        <div class="container text-center mt-5 mb-5 pb-5">
                            <h3 class="text-center"><% out.println(obj.getString("nome")); %></h3>
                            <hr class="mt-2 mb-3"/>
                            <p class="text-center">
                            <p><strong>CPF:</strong> <br><% out.println(obj.getString("CPF")); %><br></p>
                            <p><strong>Data de Nascimento:</strong><br><% 
                                out.println(obj.getString("dataNascimento")); %><br></p>
                            <hr class="mt-2 mb-3"/>
                                

                            <div class="btn-group-vertical mt-4 mb-2">
                                <button type="button" class="btn btn-primary text-center" data-bs-toggle="popover" data-bs-trigger="focus" title="Contatos" data-bs-placement="top" data-bs-content="<%
                                    if (obj.getJsonArray("contatos").isEmpty())
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
                                    if (obj.isNull("endereco"))
                                            out.println("Não há informações de endereço.");
                                    else
                                    out.println(endereco.getString("estado") + " -");
                                    out.println(endereco.getString("cidade") + " -");
                                    out.println(endereco.getString("bairro") + " -");
                                    out.println(endereco.getString("rua") + " -");
                                    out.println("nº "+ endereco.getInt("numero"));
                                    %>">Endereço</button>
                            </div>
                            <div class="container mt-5">
                                <a href="editarUsuario.jsp?id=<%out.println(obj.getInt("id"));%>" class="btn btn-success">Editar</a>
                            </div>    
                        </div>
                            <a class="btn btn-primary" href="editarContatos.jsp?id=<%out.println(obj.getInt("id"));%>" role="button">Editar Contatos</a>
                    </div>  
                                            
                 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
        <% }
        if (obj.getString("perfil").equals("doador")){
        %>
        <div class="container mt-4 mb-4">
            <h1 class="text-center mb-4 pb-5">Informações do(a) Doador</h1>
                        <div class="container text-center mt-5 mb-5 pb-5">
                            <h3 class="text-center"><% out.println(obj.getString("nome")); %></h3>                            <hr class="mt-2 mb-3"/>
                            <p class="text-center">
                            <p><strong>CPF:</strong> <br><% out.println(obj.getString("CPF")); %><br></p>
                            <p><strong>Data de Nascimento:</strong><br><% 
                                DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:s.S");
                                LocalDate date3 = LocalDate.parse(obj.getString("dataNascimento"), formatter3);
                                out.println(date3); %><br></p>
                            <hr class="mt-2 mb-3"/>
                                
                            
                                <div class="btn-group-vertical mt-4 mb-2">
                                    <button type="button" class="btn btn-primary text-center" data-bs-toggle="popover" data-bs-trigger="focus" title="Contatos" data-bs-placement="top" data-bs-content="<%
                                            if (obj.getJsonArray("contatos").isEmpty())
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
                                        if (obj.isNull("endereco"))
                                                out.println("Não há informações de endereço.");
                                        else
                                        out.println(endereco.getString("estado") + " -");
                                        out.println(endereco.getString("cidade") + " -");
                                        out.println(endereco.getString("bairro") + " -");
                                        out.println(endereco.getString("rua") + " -");
                                        out.println("nº "+ endereco.getInt("numero"));
                                        %>">Endereço</button>
                                </div>                                
                                <div class="container mt-5">
                                    <a href="editarUsuario.jsp?id=<%out.println(obj.getInt("id"));%>" class="btn btn-success">Editar</a>
                                </div>
                            </div>  
                                <a class="btn btn-primary" href="editarContatos.jsp?id=<%out.println(obj.getInt("id"));%>" role="button">Editar Contatos</a>
                        </div>
            <% }} %>
    </body>
</html>
