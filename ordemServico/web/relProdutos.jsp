<%-- 
    Document   : relProduto
    Created on : 28/11/2017, 09:57:52
    Author     : administrador
--%>

<%@page import="dao.ProdutoDAO"%>
<%@page import="dao.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            
            int x = Integer.parseInt(request.getParameter("id"));
            byte[] bytes = new ProdutoDAO().gerarRelatorio(x);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>