<%-- 
    Document   : relatorio
    Created on : 01/06/2015, 08:07:05
    Author     : fabricio
--%>
<%@page import="dao.OrdemDeServicoDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            byte[] bytes = new OrdemDeServicoDAO().gerarRelatorioCSV(request.getParameter("filtroOS"));

            String disposition = "attachment; fileName=ordem_de_servico.csv";
            response.setHeader("Content-Disposition", disposition);
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
