<%-- 
    Document   : usuarioLista
    Created on : 25/09/2017, 00:13:12
    Author     : darie
--%>

<%@page import="entidade.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Ordens de serviço</title>
    </head>

    <%@include file="menu.jsp" %>

    <body>
        <h2>Listagem de usuários</h2>

        <div class="table-responsive">
            <div class="row col-md-11">


                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Login</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Setor</th>
                            <th>Tipo de usuário</th>
                            <th>Status</th>
                            <th>Edição</th>
                            <th>Exclusão</th>
                        </tr>
                    </thead>
                    <%
                        ArrayList<Object> usuarios = new UsuarioDAO().consultarTodos();

                        for (int i = 0; i < usuarios.size(); i++) {
                            Usuario us = (Usuario) usuarios.get(i);
                            String tipoUsu = "";
                            if (us.getTipoUsuario() == 1) {
                                tipoUsu = "Administrador";
                            } else if (us.getTipoUsuario() == 2) {
                                tipoUsu = "Usuário padrão";
                            }
                    %>
                    <tr>
                        <td><%= us.getId()%></td>
                        <td><%= us.getLogin()%></td>
                        <td><%= us.getNome()%></td>
                        <td><%= us.getEmail()%></td>
                        <td><%= us.getSetor_id().getNome()%></td>
                        <td><%= tipoUsu%></td>
                        <td><%= us.getStatus()%></td>
                        <td><a href="/ordemServico/acao?parametro=edUsuario&id=<%= us.getId()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>
                        <td><a href="/ordemServico/acao?parametro=exUsuario&id=<%= us.getId()%>"><span class="pull-left glyphicon glyphicon-trash"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>