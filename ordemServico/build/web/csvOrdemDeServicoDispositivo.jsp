<%-- 
    Document   : csvOrdemDeServicoDispositivo
    Created on : 03/12/2017, 21:53:19
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
            String idD = request.getParameter("dispositivo_id");
            System.out.println("IDD: " + idD);
            byte[] bytes = new OrdemDeServicoDAO().gerarRelatorioCSVDispositivo(idD);

            String disposition = "attachment; fileName=ordem_de_servico_dispositivo.csv";
            response.setHeader("Content-Disposition", disposition);
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
