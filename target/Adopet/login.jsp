<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="cabecalho.html"/>
        <script src="js/response.js"></script>
    </head>
<body>
    <jsp:include page="menu.jsp"/>
    <br>
    <div class="container col-sm-5 mt-5 mb-5" id="content">
        <form id="formLogin" class="row gx-3 gy-2 align-items-center">
            <div class="form-group mb-5">
                <label for="login">Nome de usuario:</label>
                <input class="form-control" type="text" id="login" name="login">
                
                <label for="senha">Senha:</label>
                <input class="form-control" type="text" id="senha" name="senha">
            </div>
            <input type="submit" name="buttonLogin" id="buttonLogin" class="btn btn-success">
        </form>
    </div>
  
</body>
</html>
