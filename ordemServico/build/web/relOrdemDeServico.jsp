<%-- 
    Document   : relOrdemDeServico
    Created on : 07/11/2017, 02:17:46
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

            int idT = Integer.parseInt(request.getParameter("filtroOS"));
            System.out.println("ID DA ESCOLHA " + idT);
            //int idT = 1;
            byte[] bytes = new OrdemDeServicoDAO().gerarRelatorio(idT);
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();


        %>
    </body>
</html>
