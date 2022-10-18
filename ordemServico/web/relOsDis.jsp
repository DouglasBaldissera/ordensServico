<%-- 
    Document   : relOsDis
    Created on : 07/11/2017, 20:51:15
    Author     : Douglas
--%>

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
            int idT = Integer.parseInt(request.getParameter("dispositivo_id"));
            System.out.println("ID DA ESCOLHA " + idT);
            byte[] bytes = new OrdemDeServicoDAO().gerarRelatorioTipo(idT);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
