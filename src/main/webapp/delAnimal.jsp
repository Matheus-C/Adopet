<%//@page import="javax.json.JsonReader"%>
<%//@page import="javax.json.JsonArray"%>
<%//@page import="javax.json.JsonObject"%>
<%//@page import="java.io.StringReader"%>
<%//@page import="javax.json.JsonReaderFactory"%>
<%//@page import="javax.json.Json"%>
<%//@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Deletar pet
        </title>
    <jsp:include page="cabecalho.html"/>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <div class="row col">
            <div class="container col-sm-5">
                <img class="fixed-bottom" id="imgForm" src="midia/pets.jpg" alt="Pets">
            </div>
            <div class="container col-sm-7">
                <form method="post" action="/pets/<%= request.getParameter("id") %>">
                    <p class="mt-5">Tem certeza que quer apagar este pet?</p>
                    <input type="submit" name="sub" id="sub" class="btn btn-success" value="Apagar">
                    <a href="meusAnimais.jsp" class="btn btn-danger">Cancelar</a>
                </form>
            </div>
        </div>
        <jsp:include page="scripts.html"/>
    </body>
</html>