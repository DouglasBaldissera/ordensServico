<%-- 
    Document   : csvOrdemDeServicoDatas
    Created on : 03/12/2017, 21:37:25
    Author     : Douglas
--%>

<%@page import="dao.OrdemDeServicoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordens de </title>
    </head>
    <body>
        <%
            String data1 = request.getParameter("data1");
            String data2 = request.getParameter("data2");
            System.out.println("DATA1: " + data1 + " - " + data2);
            byte[] bytes = new OrdemDeServicoDAO().gerarRelatorioCSVDatas(data1, data2);

            String disposition = "attachment; fileName=ordem_de_servico_datas.csv";
            response.setHeader("Content-Disposition", disposition);
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>

