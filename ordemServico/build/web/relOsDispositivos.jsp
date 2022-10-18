<%-- 
    Document   : relOsTipos
    Created on : 28/11/2017, 19:14:13
    Author     : Douglas
--%>

<%@page import="dao.DispositivoDAO"%>
<%@page import="dao.DispositivoDAO"%>
<%@page import="entidade.Dispositivo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordens de serviço</title>
        <script lang="JavaScript" src="relTipos.js"></script>
    </head>

    <body>
        <form name="relOsDispositivo" action="/ordemServico/acao?parametro=relOsDispositivo" method="post">
            <label>Filtrar ordens de serviço:</label> <br>

            <select id="idD" name="dispositivo_id">
                <option value="Selecione">Selecione</option>
                <%
                    ArrayList<Object> d = new DispositivoDAO().consultarTodos();

                    for (int i = 0; i < d.size(); i++) {
                        Dispositivo dis = (Dispositivo) d.get(i);


                %>
                <option value="<%=dis.getId()%>"> <%=dis.getNomeRede()%>   </option>
                <%
                    }
                %>


            </select>


            <input type='submit' value="Gerar relatório PDF">



        </form>

        <br>

        <form name="relOSDispositivoCSV" action="/ordemServico/acao?parametro=relOSDispositivoCSV" method="post">
            <label>Filtrar ordens de serviço:</label> <br>

            <select id="idD" name="dispositivo_id">
                <option value="Selecione">Selecione</option>
                <%
                    ArrayList<Object> di = new DispositivoDAO().consultarTodos();

                    for (int i = 0; i < di.size(); i++) {
                        Dispositivo dis = (Dispositivo) di.get(i);


                %>
                <option value="<%=dis.getId()%>"> <%=dis.getNomeRede()%>   </option>
                <%
                    }
                %>


            </select>


            <input type='submit' value="Gerar relatório CSV">



        </form>



    </body>
</html>