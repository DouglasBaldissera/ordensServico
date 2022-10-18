<%-- 
    Document   : relProdutosFiltro
    Created on : 28/11/2017, 10:31:45
    Author     : administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <label>Id do produto</label> <br>
        <form name="relProdutosFiltro" action="/ordemServico/acao?parametro=relProdutosFiltro" method="post">

            <input type="text"  maxlength="50" name="id" required class="form-control">    




            <input type='submit' value="Pesquisar">

        </form>
    </body>
</html>
