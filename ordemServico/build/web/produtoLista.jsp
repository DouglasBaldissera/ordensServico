<%-- 
    Document   : produtoLista
    Created on : 04/11/2017, 13:50:59
    Author     : Douglas
--%>

<%@page import="entidade.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Ordens de serviço</title>
    </head>

    <%@include file="menu.jsp" %>

    <body>
        <h2>Listagem de produtos</h2>

        <div class="table-responsive">
            <div class="row col-md-10">

                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Descrição</th>
                            <th>Situação</th>
                            <th>Edição</th>
                            <th>Exclusão</th>
                        </tr>
                    </thead>
                    <%                        ArrayList<Object> produtos = new ProdutoDAO().consultarTodos();

                        for (int i = 0; i < produtos.size(); i++) {
                            Produto p = (Produto) produtos.get(i);
                    %>
                    <tr>
                        <td><%= p.getId()%></td>
                        <td><%= p.getDescricao()%></td>
                        <td><%=p.getStatus()%></td>
                        <td><a href="/ordemServico/acao?parametro=edProduto&id=<%= p.getId()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>
                        <td><a href="/ordemServico/acao?parametro=exProduto&id=<%= p.getId()%>"><span class="pull-left glyphicon glyphicon-trash"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
