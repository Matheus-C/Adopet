<%//@page import="javax.json.JsonReader"%>
<%//@page import="javax.json.JsonArray"%>
<%//@page import="javax.json.JsonObject"%>
<%//@page import="java.io.StringReader"%>
<%//@page import="javax.json.JsonReaderFactory"%>
<%//@page import="javax.json.Json"%>
<%//@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Meus pets
        </title>
    <jsp:include page="cabecalho.html"/>
    </head>
    <body>
        <%
            //Iterar as variações e passar id como value
            /*WebTarget wt;
            Client client = ClientBuilder.newClient();
            URI uri = new URI("http://localhost:8080/Adopet/");
            wt = client.target(uri);
            wt.path("resources/pets/encontrar");
            Invocation.Builder builder = wt.request(MediaType.APPLICATION_JSON);
            Response resposta = builder.get();
            int status = resposta.getStatus();
            String res = resposta.readEntity(String.class);*/
            /*String res = "{\"pets\":[{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"www.fotos.com/foto123.jpg\",\"gastoMensal\":10,\"nome\":\"String\",\"observacoes\":\"String\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1}]}";
            JsonReaderFactory factory = Json.createReaderFactory(null);
            JsonReader jsonReader = factory.createReader(new StringReader(res));
            JsonObject jsonObject = jsonReader.readObject();
            JsonArray jsonArray = jsonObject.getJsonArray("pets");*/
        %>
        <jsp:include page="menu.jsp"/>
        <div class="container col-sm-8">
        <div class="row">
        <%
            //for (int i = 0; i < jsonArray.size(); i++) {
                //JsonObject obj = jsonArray.getJsonObject(i);
            for (int i = 0; i < 10; i++) { 
        %>
            <div class="col-sm-4 mt-3 mb-3">
                <div class="card" style="width: 18rem;">
                    <!-- Passar imagem do pet -->
                    <% /*out.println("<img class=\"card-img-top\" src=" + obj.getString("fotos") + " alt=\"Imagem\">");*/ out.println("<img class=\"card-img-top\" src=\"midia/pets.jpg\" alt=\"Imagem\">"); %>
                    <div class="card-body">
                        <h5 class="card-title"><% /*out.println(obj.getString("nome"));*/ out.println("Kiko"); %></h5>
                        <p class="card-text"><% /*out.println(obj.getString("observacoes"));*/ out.println("Kiko é um cachorro de porte médio que encontrei abandonado na praia."); %></p>
                        <a href="editAnimal.jsp?id=<% /*obj.getInt("id");*/ out.println(i); %>" class="btn btn-primary">Editar</a>
                        <a href="delAnimal.jsp?id=<% /*obj.getInt("id");*/ out.println(i); %>" class="btn btn-danger">Deletar</a>
                    </div>
                </div>
            </div>
        <% } %>
        </div>
        </div>
        <jsp:include page="scripts.html"/>
    </body>
</html>