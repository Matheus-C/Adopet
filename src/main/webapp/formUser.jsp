<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Cadastre-se
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
                    <form method="post" action="/register">
                        <label for="tipo" class="form-label">Você é uma:</label>
                        <select name="tipo" id="tipo" class="form-select">
                            <option disabled selected value> -- Selecione uma opção -- </option>
                            <option value="0">instituição</option>
                            <option value="1">pessoa fí­sica</option>
                        </select>
                        <br>
                        <div class="form-group">
                            <label for="perfil" class="form-label">Você quer:</label>
                            <select name="perfil" id="perfil" class="form-select">
                                <option value="instituicao" id="inst">Instituição</option>
                                <option value="adotante">adotar</option>
                                <option value="doador">doar</option>
                            </select>
                        </div>
                        <br>
                        <label for="nome" class="form-label">digite seu nome completo: </label><input type="text" name="nome" id="nome" class="form-control">
                        
                        <label for="login" class="form-label">informe um nome de usuario:</label><input type="text" name="login" id="login" class="form-control">

                        <label for="senha" class="form-label">informe uma senha:</label><input type="password" name="senha" id="senha" class="form-control">

                        
                        <div class="form-group">
                            <label for="estado" class="form-label">Estado:</label>
                            <input type="text" name="estado" id="estado" class="form-control">
                            <label for="cidade" class="form-label">Cidade:</label>
                            <input type="text" name="cidade" id="cidade" class="form-control">
                            <label for="bairro" class="form-label">Bairro:</label>
                            <input type="text" name="bairro" id="bairro" class="form-control">
                            <label for="rua" class="form-label">Rua:</label>
                            <input type="text" name="rua" id="rua" class="form-control">
                            <label for="numero" class="form-label">Número:</label>
                            <input type="number" name="numero" id="numero" class="form-control">
                            <label for="complemento" class="form-label">Complemento:</label>
                            <input type="text" name="complemento" id="complemento" class="form-control">
                        </div>
                        <br>
                        <div class="form-group" id="pessoaFisica">
                            <label for="cpf" class="form-label">CPF:</label>
                            <input type="text" name="cpf" id="cpf" class="form-control">
                            <label for="dataNascimento" class="form-label">Data de nascimento:</label>
                            <input type="date" name="dataNascimento" id="dataNascimento" class="form-control">
                        </div>
                        <div class="form-group" id="instituicao">
                            <label for="numAnimais" class="form-label">Número de animais que estão sob a sua tutela:</label>
                            <input type="num" name="numAnimais" id="numAnimais" class="form-control">
                            <label for="capacidade" class="form-label">Quantos animais a sua instituição suporta:</label>
                            <input type="num" name="capacidade" id="capacidade" class="form-control">
                            <label for="numRegistro" class="form-label">Número do registro:</label>
                            <input type="text" name="numRegistro" id="numRegistro" class="form-control">
                            <label for="certificacoes" class="form-label">Certificações</label>
                            <input type="text" name="certificacoes" id="certificacoes" class="form-control">
                            <label for="razaoSocial" class="form-label">Razão Social:</label>
                            <input type="text" name="razaoSocial" id="razaoSocial" class="form-control">
                            <label for="dataFundacao" class="form-label">Data de fundação:</label>
                            <input type="date" name="dataFundacao" id="dataFundacao" class="form-control">
                        </div>
                        <br>
                        <input type="submit" name="sub" id="sub" class="btn btn-success">
                        
                    </form>
                </div>
        </div>
        <jsp:include page="scripts.html"/>
    </body>
</html>