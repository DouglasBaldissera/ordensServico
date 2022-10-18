<%-- 
    Document   : ordemDeServicoLista
    Created on : 05/11/2017, 17:56:46
    Author     : Douglas
--%>

<%@page import="entidade.OrdemDeServico"%>
<%@page import="dao.OrdemDeServicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <meta http-equiv="refresh" content="5(SIGNIFICA QUE IRÁ ATUALIZAR AUTOMÁTICAMENTE EM 5 segundos);url=ordemDeServicoLista.jsp">


        <title>Ordens de serviço</title>
    </head>

    <%@include file="menu.jsp" %>

    <script type="text/javascript">
        function getXMLHttpRequestObject()
        {
            var objetoAjax;

            if (window.XMLHttpRequest) { // tenta carregar componente Mozilla/safari
                objetoAjax = new XMLHttpRequest();
            } else if (window.ActiveXObject) { // se falhar, tenta carregar o ActiveX do IE :-(
                try {
                    objetoAjax = new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        objetoAjax = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (ex) {
                        objetoAjax = false;
                    }
                }
            }
            return objetoAjax;
        }

        function enviarDados() {
            var http = new getXMLHttpRequestObject();

            criterio = document.getElementById("criterio").value;
            status = document.getElementById("status").value;
            http.onreadystatechange = function () { //Handler function for call back on state change.
                if (http.readyState === 4) {
                    document.getElementById("lista").innerHTML = this.responseText;
                }
            };

            http.open("GET", "ordemDeServicoTabela.jsp?criterio=" + criterio + "&status=" + status, true);
            http.send();

            event.preventDefault();
        }

    </script>

    <body onload="enviarDados()">
        <h2>Listagem de ordens de serviço</h2>

        <form name='pesquisaOS'>
            <label>Nome a pesquisar:</label> <br>
            <input onkeyup="enviarDados()" id="criterio" type='text' name='criterio' value='' class="form-control">

            <br>



            <label>Filtrar ordens de serviço:</label> 
            <br>
            <select id="status" name="status" onclick="enviarDados()">
                <option value="0">Todas</option>
                <option value="1">Abertas</option>
                <option value="2">Fechadas</option>
            </select>

            <br>
            <br>
        </form>

        <div class="table-responsive" id="lista">

        </div>
    </body>
</html>
