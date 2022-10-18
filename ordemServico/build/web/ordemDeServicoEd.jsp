<%-- 
    Document   : ordemDeServicoEd
    Created on : 05/11/2017, 18:53:31
    Author     : Douglas
--%>

<%@page import="entidade.Produto"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="entidade.Tipo"%>
<%@page import="dao.TipoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Dispositivo"%>
<%@page import="dao.DispositivoDAO"%>
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
    <script language="JavaScript" src="validaOrdemDeServico.js"></script>

    <script>
        function alertSucesso() {
            alert("Registro salvo!");
        }
    </script>

    <body>

        <%            OrdemDeServico os = (OrdemDeServico) request.getAttribute("ordemDeServicoEdi");
            System.out.println("dasdasdsada " + os.getProblemaRelatado());
            if (os == null) {
                os = new OrdemDeServico();

            }
            System.out.println("TEEEEEEEEE: " + os.getDataEmissao().dataFormatada());
        %>


        <h2>Cadastro de Ordens de serviço</h2>
        <h4>Campos obrigatórios (*)</h4>

        <form name="cadOrdemDeServico" action="/ordemServico/acao?parametro=cadOrdemDeServico" method="post" onsubmit="alertSucesso()">
            <input type="hidden" name="id" value="<%= os.getId()%>" required class="form-control"> 

            <label>Data de emissão (*)</label><br>
            <input readonly="true" type="text" name="data" value="<%= os.getDataEmissao().dataFormatada()%>" required class="form-control">
            <br>

            <label>Problema relatado (*)</label><br>
            <input readonly="true" type="text" name="problemaR" value="<%= os.getProblemaRelatado()%>" required class="form-control">
            <br>

            <label>Problema constatado (*)</label><br>
            <input type="text" name="problemaC" value="<%= os.getProblemaConstatado()%>" required class="form-control">
            <br>

            <label>Resolução (*)</label><br>
            <input type="text" name="resolucao" value="<%= os.getResolucao()%>" required class="form-control">

            <br>



            <label>Produto substituído &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</label> 
            <label>Quantidade </label><br>

            <!--            PRODUTOOOOOOOOOOOOOOOOS       -->
            <select id="idP" name="produtos">
                <option value="Selecione">Selecione</option>
                <%
                    ArrayList<Object> p = new ProdutoDAO().consultarTodos();

                    for (int i = 0; i < p.size(); i++) {
                        Produto prod = (Produto) p.get(i);


                %>
                <option value="<%=prod.getId()%>"> <%=prod.getDescricao()%>   </option>


                <%
                    }
                %>   
            </select>
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;                                
            <select id="quantidade" name="quantidade">
                <option value="0">Selecione</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>

            </select>

            <br>
            <br>

            <label>Dispositivo (*)</label><br>


            <select id="idD" disabled="true" name="dispositivo_id">
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





            <label>Tipo de serviço (*)</label><br>
            <select id="idT" name="tipoOS">
                <option value="Selecione">Selecione</option>
                <%
                    ArrayList<Object> tipos = new TipoDAO().consultarTodos();

                    for (int i = 0; i < tipos.size(); i++) {
                        Tipo t = (Tipo) tipos.get(i);

                %>

                <option value="<%=t.getId()%>"> <%=t.getDescricao()%>   </option>

                <%
                    }

                %>
            </select>





            <br>
            <br>

            <input type="submit" name="enviar" value="Salvar" class="btn-primary" onclick="validarDispositivoOS(), validarTipoOS(), validarProdutos()" >
            <input type="reset"  name="b2" value="Limpar">

        </form>
        <a href="../src/java/servlet/acao.java"></a>

        <br>


    </body>
</html>
