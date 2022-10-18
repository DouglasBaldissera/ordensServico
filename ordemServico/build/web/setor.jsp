<%-- 
    Document   : setor
    Created on : 20/09/2017, 21:08:23
    Author     : darie
--%>

<%@page import="dao.SetorDAO"%>
<%@page import="entidade.Setor"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordens de serviço</title>
        <%@include file="menu.jsp" %>



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
                nome = document.getElementById("nome").value;

                var parameters = "parametro=cadSetor" + "&id=" + id + "&nome=" + nome;
                http.open("POST", url, true);


                //Define dados de cabeçalho
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.setRequestHeader("Content-length", parameters.length);
                http.setRequestHeader("Connection", "close");
                http.onreadystatechange = function () { //Handler function for call back on state change.
                    if (http.readyState == 4) {
                        window.location = "setorLista.jsp"; // redireciona para o cadastro
                        alert("Registro salvo!");
                    }
                }
                http.send(parameters);
            }


        </script>




    </head>




    <body>

        <% Setor s = (Setor) request.getAttribute("setor");

            if (s == null) {
                s = new Setor();
            }
        %>


        <h2>Cadastro de setores</h2>    
        <h4>Campos obrigatórios (*)</h4>
        <form name="cadSetor" action="/ordemServico/acao?parametro=cadSetor" id="cadSetor" method="post" onsubmit="alertSucesso()">


            <input type="hidden" name="id" id="id" value="<%= s.getId()%>" required class="form-control"> 

            <label>Nome (*)</label><br>
            <input type="text"  maxlength="50" name="nome" id="nome"  value="<%= s.getNome()%>" required class="form-control">
            <br>

            <input type="submit" href=""name="enviar" value="Salvar" id="Salvar" class="btn-primary" >
            <input type="reset"  name="b2" value="Limpar">
        </form>

        <br>

        <!--        <%
            ArrayList<Object> setor2 = new SetorDAO().consultarTodos();

        %>

        <select name="comboUsuario">
            <option value="0">Selecione</option>

        <%                for (int i = 0; i < setor2.size(); i++) {
                Setor s2 = (Setor) setor2.get(i);
        %>

        <option value="<%= s2.getId()%>"><%= s2.getNome()%></option>

        <%                    }
        %>

    </select>
    <br>-->
        <br>

    </body>
</html>
