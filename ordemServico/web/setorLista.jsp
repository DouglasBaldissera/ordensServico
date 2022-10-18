<%-- 
    Document   : setorLista
    Created on : 23/09/2017, 13:40:32
    Author     : darie
--%>

<%@page import="entidade.Setor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.SetorDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Ordens de serviço</title>
    </head>

    <%@include file="menu.jsp" %>

    <body>
        <h2>Listagem de setores</h2>

        <form name='pesquisaSetor' action='setorLista.jsp'>
            <label>Nome a pesquisar:</label> <br>
            <input type='text' name='criterio' value='' class="form-control">

            <br>
            <input type='submit' value="Pesquisar">

        </form>

        <%
            String criterio = request.getParameter("criterio");

            if (criterio == null) {
                criterio = "";
            }
        %>



        <div class="table-responsive">
            <div class="row col-md-10">

                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Nome</th>
                            <th>Situação</th>
                            <th>Edição</th>
                            <th>Exclusão</th>
                        </tr>
                    </thead>
                    <%
                        ArrayList<Object> setores = new SetorDAO().consultar(criterio);

                        for (int i = 0; i < setores.size(); i++) {
                            Setor s = (Setor) setores.get(i);
                    %>
                    <tr>
                        <td><%= s.getId()%></td>
                        <td><%= s.getNome()%></td>
                        <td><%=s.getStatus()%></td>
                        <td><a href="/ordemServico/acao?parametro=edSetor&id=<%= s.getId()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>
                        <td><a href="/ordemServico/acao?parametro=exSetor&id=<%= s.getId()%>"><span class="pull-left glyphicon glyphicon-trash"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
