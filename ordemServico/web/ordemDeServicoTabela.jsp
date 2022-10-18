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


        <title>Ordens de serviço</title>
    </head>

    <body>


        <%
            String criterio = request.getParameter("criterio");
            String status = request.getParameter("status");
            if (criterio == null) {
                criterio = "";
            }
            if (status == null) {
                status = "";
            } else {
                if (!status.equals("0")) {
                    status = "os.status_id = " + status;
                }
                else
                {
                    status = "";
                }
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
                        String sql = "";
                        sql += criterio + "%'";
                        if (!sql.isEmpty() && !status.isEmpty()) {
                            sql += " AND ";
                        }

                        sql += status;
                        sql += " ORDER BY os.id";
                        ArrayList<Object> oss = new OrdemDeServicoDAO().consultar(sql);

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
