<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
            Navegar
        </title>
    <jsp:include page="cabecalho.html"/>
    </head>
    <body>
        <%
            Integer index = Integer.valueOf(request.getParameter("index"));
            //Iterar as variações e passar id como value
            /*WebTarget wt;
            Client client = ClientBuilder.newClient();
            URI uri = new URI("http://localhost:8080/Adopet/sample/");
            wt = client.target(uri);
            wt.path("pets/encontrar");
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
        <div class="container col-sm-5">
            <img class="fixed-bottom" id="imgForm" src="midia/pets.jpg" alt="Pets">
        </div>
        <div class="row d-flex justify-content-center">
        <%
            List<JsonObject> jsonObjs = new ArrayList<JsonObject>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.getJsonObject(i);
                jsonObjs.add(obj);
            }
        %>
            <div class="col-sm-4 mt-3">
                <div class="card" style="width: 18rem;">
                    <!-- Passar imagem do pet -->
                    <% out.println("<img class=\"card-img-top\" src=\"" + jsonObjs.get(index).getString("fotos") + "\" alt=\"Imagem\">");%>
                    <div class="card-body">
                        <h5 class="card-title"><% out.println(jsonObjs.get(index).getString("nome")); %></h5>
                        <p class="card-text"><% out.println(jsonObjs.get(index).getString("observacoes")); %></p>
                        <div class="d-flex justify-content-between">
                            <form method="post" action="/pets/<%=jsonObjs.get(index).getInt("id")%>/desejo">
                                <input type="submit" name="sub" id="sub" class="btn btn-success" value="Favoritar">
                            </form>
                            <a href="navegar.jsp?index=<% if(jsonObjs.size()>index+1){out.println(index+1);}else{out.println(0);} %>" class="btn btn-danger">Próximo</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <jsp:include page="scripts.html"/>
    </body>
</html>