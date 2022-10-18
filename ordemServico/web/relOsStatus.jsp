<%-- 
    Document   : relOsStatus
    Created on : 07/11/2017, 02:27:43
    Author     : Douglas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordens de serviço</title>
    </head>
    <body>
        <form id="relOS" name="relOS" action="/ordemServico/acao?parametro=relOS" method="post">
            <label>Filtrar ordens de serviço:</label> <br>
            <select id="filtroOS" name="filtroOS">
                <option value="1">Abertas</option>
                <option value="2">Fechadas</option>
            </select>

            <input type='submit' value="Gerar relatório PDF">



        </form>

        <br>

        <form id="relOSCSV" name="relOSCSV" target="_blank" action="/ordemServico/acao?parametro=relOSCSV" method="post">
            <label>Filtrar ordens de serviço:</label> <br>
            <select id="filtroOS" name="filtroOS">
                <option value="1">Abertas</option>
                <option value="2">Fechadas</option>
            </select>
            <input type='submit' value="Gerar relatório CSV">
        </form>
    </body>
</html>
