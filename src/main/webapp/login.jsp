<%-- 
    Document   : login
    Created on : 6 de jul. de 2022, 16:56:50
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Login
        </title>
        <jsp:include page="cabecalho.html"/>
    </head>
<body>
    <jsp:include page="menu.jsp"/>
    <br>
    <div class="container col-sm-5">
        <img id="imglogin" src="midia/pets.jpg" alt="Pets">
    </div>
    <div class="container col-sm-5">
        <form method="post" action="/login" class="row gx-3 gy-2 align-items-center">
            <div class="form-group">
                <label for="login">Nome de usuario:</label>
                <input class="form-control" type="text" id="login" name="login">
                
                <label for="senha">Senha:</label>
                <input class="form-control" type="text" id="senha" name="senha">
            </div>
            <input type="submit" name="sub" id="sub" class="btn btn-success">
        </form>
    </div>
    <jsp:include page="scripts.html"/>
</body>
</html>
