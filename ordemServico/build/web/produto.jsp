<%-- 
    Document   : produto
    Created on : 04/11/2017, 10:46:10
    Author     : Douglas
--%>

<%@page import="dao.ProdutoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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



            function enviarDados() {
                var http = new getXMLHttpRequestObject();

                var url = "/ordemServico/acao";

                id = document.getElementById("id").value;
                descricao = document.getElementById("descricao").value;


                var parameters = "parametro=cadProduto" + "&id=" + id + "&descricao=" + descricao;
                http.open("POST", url, true);

                //Define dados de cabeçalho
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.setRequestHeader("Content-length", parameters.length);
                http.setRequestHeader("Connection", "close");
                http.onreadystatechange = function () { //Handler function for call back on state change.
                    if (http.readyState == 4) {
                        window.location = "/ordemServico/produto.jsp"; // redireciona para o cadastro
                        alert("Registro salvo!");
                    }
                }
                http.send(parameters);
            }


        </script>
    </head>



    <body>

        <% Produto p = (Produto) request.getAttribute("produto");

            if (p == null) {
                p = new Produto();
            }

        %>


        <h2>Cadastro de produtos</h2>    
        <h4>Campos obrigatórios (*)</h4>
        <form name="cadProduto" action="" id="cadProduto" method="post" onsubmit="enviarDados()">

            <input type="hidden" id="id" name="id" value="<%= p.getId()%>" required class="form-control"> 

            <label>Nome (*)</label><br>
            <input type="text"  maxlength="50" name="descricao" id="descricao" value="<%= p.getDescricao()%>" required class="form-control">
            <br>

            <input type="submit" name="enviar" value="Salvar" class="btn-primary ">
            <input type="reset"  name="b2" value="Limpar">
        </form>

        <br>

    </body>
</html>
