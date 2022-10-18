<%-- 
    Document   : csvDispositivo
    Created on : 28/11/2017, 09:28:11
    Author     : administrador
--%>

<%@page import="dao.DispositivoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            byte[] bytes = new DispositivoDAO().;

            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
