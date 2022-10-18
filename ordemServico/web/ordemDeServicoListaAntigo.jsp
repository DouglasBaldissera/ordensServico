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

    <body>
        <h2>Listagem de ordens de serviço</h2>

        <form name='pesquisaOS' action='ordemDeServicoLista.jsp'>
            <label>Nome a pesquisar:</label> <br>
            <input type='text' name='criterio' value='' class="form-control">

            <br>
            <input type='submit' value="Pesquisar">
            <br>
            <br>
            <label>Filtrar ordens de serviço:</label> 
            <br>
            <select id="idOS" name="filtroOS" onclick="">
                <option value="0">Todas</option>
                <option value="1">Abertas</option>
                <option value="2">Fechadas</option>
            </select>

            <br>
            <br>
        </form>


        <%            String criterio = request.getParameter("criterio");

            if (criterio == null) {
                criterio = "";
            }
        %>



        <div class="table-responsive">
            <div class="row col-md-10">

                <table class="table table-condensed table-hover">
                    <thead class="thead-default">
                        <tr>
                            <th>Id</th>
                            <th>Data de emissão</th>
                            <th>Data de entrega</th>
                            <th>Problema Relatado</th>
                            <th>Problema Constatado</th>
                            <th>Solicitante</th>
                            <th>Resolução</th>
                            <th>Dispositivo</th>
                            <th>Tipo</th>
                            <th>Status</th>



                        </tr>
                    </thead>
                    <%
                        String status = request.getParameter("filtroOS");
                        if (status == null) {
                            status = "";
                            criterio += "%' ORDER BY os.id";
                        } else {
                            criterio += "%' ORDER BY os.id AND os.status_id = '" + status + "'";
                        }
                        ArrayList<Object> oss = new OrdemDeServicoDAO().consultar(criterio);

                        for (int i = 0; i < oss.size(); i++) {
                            OrdemDeServico os = (OrdemDeServico) oss.get(i);
                            String data = "";
                            if (os.getDataEntrega() != null) {
                                data = os.getDataEntrega().dataFormatada();
                            }

                            String resolucao = "";
                            if (os.getResolucao() != null) {
                                resolucao = os.getResolucao();
                            }

                            String tipo = "";
                            if (os.getTipo_id() != null) {
                                tipo = os.getTipo_id().getDescricao();
                            }

                            String problemaConstatado = "";
                            if (os.getProblemaConstatado() != null) {
                                problemaConstatado = os.getProblemaConstatado();
                            }


                    %>
                    <tr>
                        <td><%= os.getId()%></td>
                        <td><%=os.getDataEmissao().dataFormatada()%></td>
                        <td><%=data%></td>
                        <td><%=os.getProblemaRelatado()%></td>
                        <td><%=problemaConstatado%></td>
                        <td><%=os.getSolicitante().getNome()%></td>
                        <td><%=resolucao%></td>
                        <td><%=os.getDispositivo_id().getNomeRede()%></td>
                        <td><%=tipo%></td>
                        <td><%=os.getStatus_id().getDescricao()%></td>
                        <td><a href="/ordemServico/acao?parametro=edOrdemDeServico&id=<%= os.getId()%>"><span class="pull-left glyphicon glyphicon-pencil"></span></a></td>

                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
