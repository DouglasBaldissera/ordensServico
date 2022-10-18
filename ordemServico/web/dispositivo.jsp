<%-- 
    Document   : dispositivo
    Created on : 17/09/2017, 16:12:40
    Author     : darie
--%>
<%@page import="dao.SetorDAO"%>
<%@page import="entidade.Setor"%>
<%@page import="dao.DispositivoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Dispositivo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordem de Serviço</title>
    </head>

    <%@include file="menu.jsp" %>
    
    <script language="JavaScript" src="validaDispositivo.js">
    
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
        function alertSucesso() {
            alert("Registro salvo!");
        }

        function enviarDados() {
            var http = new getXMLHttpRequestObject();

            var url = "/ordemServico/acao";

            id = document.getElementById("id").value;
            nomeRede = document.getElementById("nomeRede").value;
            ip = document.getElementById("ip").value;
            setor_id = document.getElementById("setor_id").value;

            if (setor_id === "Selecione") {
                alert("Selecione um setor");
                return;
            }

            var parameters = "parametro=cadDispositivo" + "&id=" + id + "&nomeRede=" + nomeRede + "&ip=" + ip + "&setor_id=" + setor_id;
            http.open("POST", url, true);

            //Define dados de cabeçalho
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.setRequestHeader("Content-length", parameters.length);
            http.setRequestHeader("Connection", "close");
            http.onreadystatechange = function () { //Handler function for call back on state change.
                if (http.readyState == 4) {
                    window.location = "/ordemServico/dispositivo.jsp"; // redireciona para o cadastro
                    alert("Registro salvo!");
                }
            }
            http.send(parameters);
        }


    </script>




    <body>

        <%          Dispositivo dis = (Dispositivo) request.getAttribute("dispositivo");

            if (dis == null) {
                dis = new Dispositivo();

            }

        %>


        <h2>Cadastro de dispositivos</h2>
        <h4>Campos obrigatórios (*)</h4>

        <form name="cadDispositivo" action="/ordemServico/acao?parametro=cadDispositivo" id="cadDispositivo" method="post" onsubmit="alertSucesso()">
            <input type="hidden" name="id" id="id" value="<%= dis.getId()%>" required class="form-control"> 

            <label>Nome do computador (*)</label><br>
            <input type="text" id="nomeRede" name="nomeRede" value="<%= dis.getNomeRede()%>" required class="form-control">
            <br>

            <label>Número IP (*)</label><br>
            <input id="ip" pattern="[0-9]{3}[.]{1}[0-9]{3}[.]{1}[0-9]{3}[.]{1}[0-9]{3}" type="text" maxlength="15" name="ip" placeholder="xxx.xxx.xxx.xxx" value="<%= dis.getIp()%>" required class="form-control" x-moz-errormessage="Ip inválido." >





            <br>

            <label>Setor (*)</label><br>


            <select id="setor_id" name="setor_id">
                <option value="Selecione">Selecione</option>
                <%                    ArrayList<Object> s = new SetorDAO().consultarTodos();

                    for (int i = 0; i < s.size(); i++) {
                        Setor set = (Setor) s.get(i);

                        if (set.getId() == dis.getSetor_id().getId()) {


                %>


                <option value="<%=set.getId()%> "selected=""> <%=set.getNome()%>   </option>

                <%
                } else {
                %>
                <option value="<%=set.getId()%>"> <%=set.getNome()%>   </option>
                <%
                        }
                    }
                %>
            </select>


            <br>
            <br>

            <input type="submit" name="enviar" value="Salvar" id="Salvar" class="btn-primary" onclick="validarSetorDispositivo()" >
            <input type="reset"  name="b2" value="Limpar">

        </form>

        <br>


    </body>
</html>
