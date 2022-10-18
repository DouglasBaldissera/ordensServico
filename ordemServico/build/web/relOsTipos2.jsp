<%-- 
    Document   : relOsTipos
    Created on : 07/11/2017, 20:43:09
    Author     : Douglas
--%>

<%@page import="entidade.Dispositivo"%>
<%@page import="dao.DispositivoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script lang="JavaScript" src="relTipos.js">
    </head>
    <body>
        <form name="relOSDis" action="/ordemServico/acao?parametro=relOSDis" method="post">
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


                    <input type='submit' value="Pesquisar">
          
            

        </form>
    </body>
</html>
