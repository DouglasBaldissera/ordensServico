<%-- 
    Document   : relOsDatas
    Created on : 07/11/2017, 19:37:07
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
        <form name="relOSD" action="/ordemServico/acao?parametro=relOSD" method="post">
            <label>Filtrar ordens de serviço por data de emissão:</label> <br><br>


            <label>Data 1:</label> <input type="date"  maxlength="50" name="data1" required class="form-control">
            <br>
            <br>


            <label>Data 2:</label> <input type="date"  maxlength="50" name="data2" required class="form-control">

            <br><br> <input type='submit' value="Gerar relatório PDF">

        </form>

        <br><br>
        <form name="relOSDataCSV" action="/ordemServico/acao?parametro=relOSDataCSV" method="post">
            <label>Filtrar ordens de serviço por data de emissão:</label> <br><br>


            <label>Data 1:</label> <input type="date"  maxlength="50" name="data1" required class="form-control">
            <br>
            <br>


            <label>Data 2:</label> <input type="date"  maxlength="50" name="data2" required class="form-control">

            <br><br> <input type='submit' value="Gerar relatório CSV">

        </form>


    </body>
</html>

