<%-- 
    Document   : dispositivoLista
    Created on : 23/09/2017, 15:06:09
    Author     : darie
--%>

<%@page import="entidade.Dispositivo"%>
<%@page import="dao.DispositivoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Ordens de serviço</title>
    </head>

    <%@include file="menu.jsp" %>

    <body>
        <h2>Listagem de dispositivos</h2>

        <div class="table-responsive">
            <div class="row col-md-10">

                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Nome na rede</th>
                            <th>Ip</th>
                            <th>Setor</th>
                            <th>Situação</th>
                            <th>Edição</th>
                            <th>Exclusão</th>
                        </tr>
                    </thead>
                    <%                        ArrayList<Object> dispositivos = new DispositivoDAO().consultarTodos();

                        for (int i = 0; i < dispositivos.size(); i++) {
                            Dispositivo d = (Dispositivo) dispositivos.get(i);
                    %>
                    <tr>
                        <td><%= d.getId()%></td>
                        <td><%= d.getNomeRede()%></td>
                        <td><%= d.getIp()%></td>
                        <td><%= d.getSetor_id().getNome()%></td>
                        <td><%= d.getStatus()%></td>
                        <td><a href="/ordemServico/acao?parametro=edDispositivo&id=<%= d.getId()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>
                        <td><a href="/ordemServico/acao?parametro=exDispositivo&id=<%= d.getId()%>"><span class="pull-left glyphicon glyphicon-trash"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>