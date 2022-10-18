<%-- 
    Document   : ordemDeServico
    Created on : 05/11/2017, 15:01:23
    Author     : Douglas
--%>

<%@page import="entidade.Dispositivo"%>
<%@page import="entidade.Dispositivo"%>
<%@page import="dao.DispositivoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.OrdemDeServico"%>
<%@page import="entidade.OrdemDeServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordem de Serviço</title>
    </head>

    <%@include file="menu.jsp" %>
    <script language="JavaScript" src="validaOrdemDeServico.js">
    </script>

    <script type = "text/javascript" >
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
        ;



        function enviarDados() {
            var http = new getXMLHttpRequestObject();

            var url = "/ordemServico/acao";

            id = document.getElementById("id").value;
            problemaR = document.getElementById("problemaR").value;
            idD = document.getElementById("idD").value;


            var parameters = "parametro=cadOrdemDeServico" + "&id=" + id + "&problemaR=" + problemaR + "&idD=" + idD;
            http.open("POST", url, true);

            //Define dados de cabeçalho
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.setRequestHeader("Content-length", parameters.length);
            http.setRequestHeader("Connection", "close");
            http.onreadystatechange = function () { //Handler function for call back on state change.
                if (http.readyState === 4) {
                    window.location = "/ordemServico/ordemDeServico.jsp"; // redireciona para o cadastro
                    alert("Registro salvo!");
                }
            };
            http.send(parameters);
        }




    </script>

    <body>

        <%            OrdemDeServico os = (OrdemDeServico) request.getAttribute("ordemDeServico");

            if (os == null) {
                os = new OrdemDeServico();

            }

        %>


        <h2>Cadastro de Ordens de serviço</h2>
        <h4>Campos obrigatórios (*)</h4>

        <form name="cadOrdemDeServico" action="" id="cadOrdemDeServico" method="post" onsubmit="enviarDados()">

            <input type="hidden" id="id" name="id" value="<%= os.getId()%>" required class="form-control"> 

            <label>Problema relatado (*)</label><br>
            <input type="text" id="problemaR" name="problemaR" value="<%= os.getProblemaRelatado()%>" required class="form-control">
            <br>

            <!--
                        <textarea>
                            
            
                        </textarea>-->


            <br>

            <label>Dispositivo (*)</label><br>


            <select id="idD" name="dispositivo_id">
                <option value="Selecione">Selecione</option>
                <%
                    ArrayList<Object> d = new DispositivoDAO().consultarTodos();

                    for (int i = 0; i < d.size(); i++) {
                        Dispositivo dis = (Dispositivo) d.get(i);

                        if (dis.getId() == os.getDispositivo_id().getId()) {


                %>


                <option value="<%=dis.getId()%> "selected=""> <%=dis.getNomeRede()%>   </option>

                <%
                } else {
                %>
                <option value="<%=dis.getId()%>"> <%=dis.getNomeRede()%>   </option>
                <%
                        }
                    }
                %>
            </select>


            <br>
            <br>

            <input type="submit" name="enviar" value="Salvar" class="btn-primary" onclick="validarDispositivoOS()" >
            <input type="reset"  name="b2" value="Limpar">

        </form>

        <br>


    </body>
</html>
