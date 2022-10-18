<%-- 
    Document   : relOrdemDeServicoDatas
    Created on : 28/11/2017, 09:12:55
    Author     : administrador
--%>

<%@page import="apoio.Formatacao"%>
<%@page import="dao.OrdemDeServicoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String data1 = request.getParameter("data1");

            String data2 = request.getParameter("data2");
            data1 = Formatacao.ajustaDataDMA(data1);
            data2 = Formatacao.ajustaDataDMA(data2);
            System.out.println("DATA 1: " + data1);
            System.out.println("DATA 2: " + data2);

            byte[] bytes = new OrdemDeServicoDAO().gerarRelatorioData(data1, data2);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>

