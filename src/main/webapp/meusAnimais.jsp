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
            URI uri = new URI("http://localhost:8080/Adopet/sample/");
            wt = client.target(uri);
            wt.path("pets/cadastrados");
            Invocation.Builder builder = wt.request(MediaType.APPLICATION_JSON);
            Response resposta = builder.get();
            int status = resposta.getStatus();
            String res = resposta.readEntity(String.class);*/
            String res = "{\"pets\":[{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1},{\"id\":1,\"adotado\":false,\"altura\":10,\"fotos\":\"https://img.r7.com/images/cachorro-que-parece-pompom-13012022171412951?dimensions=771x420\",\"gastoMensal\":10,\"nome\":\"Kiko\",\"observacoes\":\"Encontrado próximo a praia de Itacoatiara.\",\"peso\":10,\"dataNascimento\":\"2022-06-18 21:00:00.0\",\"variacao\":1}]}";
            JsonReaderFactory factory = Json.createReaderFactory(null);
            JsonReader jsonReader = factory.createReader(new StringReader(res));
            JsonObject jsonObject = jsonReader.readObject();
            JsonArray jsonArray = jsonObject.getJsonArray("pets");
        %>
        <jsp:include page="menu.jsp"/>
        <div class="container col-sm-8">
        <div class="row">
        <%
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.getJsonObject(i);
        %>
            <div class="col-sm-4 mt-3 mb-3">
                <div class="card" style="width: 18rem;">
                    <!-- Passar imagem do pet -->
                    <% out.println("<img class=\"card-img-top\" src=" + obj.getString("fotos") + " alt=\"Imagem\">");%>
                    <div class="card-body">
                        <h5 class="card-title"><% out.println(obj.getString("nome")); %></h5>
                        <p class="card-text"><% out.println(obj.getString("observacoes")); %></p>
                        <a href="editAnimal.jsp?id=<% out.println(obj.getInt("id")); %>" class="btn btn-primary">Editar</a>
                        <a href="delAnimal.jsp?id=<% out.println(obj.getInt("id")); %>" class="btn btn-danger">Deletar</a>
                    </div>
                </div>
            </div>
        <% } %>
        </div>
        </div>
        <jsp:include page="scripts.html"/>
    </body>
</html>