<%-- 
    Document   : usuario
    Created on : 23/09/2017, 19:16:39
    Author     : darie
--%>

<%@page import="entidade.Setor"%>
<%@page import="dao.SetorDAO"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordens de serviço</title>

        <script lang="JavaScript" src="validaUsuario.js">
        </script>

        <script>
            type = "text/javascript"
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

                var url = "/ordemDeServico/acao";

                id = document.getElementById("login").value;
                nome = document.getElementById("nome").value;
                email = document.getElementById("email").value;
                senha = document.getElementById("senha").value;

                var parameters = "parametro=cadUsuario" + "&id=" + id + "&nome=" + nome + "&email=" + email + "&senha=" + senha;
                http.open("POST", url, true);

                //Define dados de cabeçalho
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.setRequestHeader("Content-length", parameters.length);
                http.setRequestHeader("Connection", "close");
                http.onreadystatechange = function () { //Handler function for call back on state change.
                    if (http.readyState == 4) {
                        window.location = "/ProspexaoT/usuario.jsp"; // redireciona para o cadastro
                        alert("Registro salvo!");
                    }
                }
                http.send(parameters);
            }

        </script>
    </head>

    <%@include file="menu.jsp" %>



    <body>



        <%            Usuario usuario = (Usuario) request.getAttribute("usuario");

            if (usuario == null) {
                usuario = new Usuario();
            }

        %>




        <%             String mensagem = (String) request.getAttribute("mensagem");
            System.out.println("MENSAGEM: " + mensagem);

        %>


        <h2>Cadastro de usuários</h2>
        <h4>Campos obrigatórios (*)</h4>

        <form name="cadUsuario" action="/ordemServico/acao?parametro=cadUsuario" method="post" onsubmit="alertSucesso() ">
            <input type="hidden" name="id" value="<%= usuario.getId()%>" required class="form-control"> 

            <label>Login (*)</label><br>
            <input type="text" name="login" value="<%= usuario.getLogin()%>" required class="form-control">
            <br>

            <label>Nome (*)</label><br>
            <input type="text" name="nome" value="<%= usuario.getNome()%>" required class="form-control">
            <br>

            <label>E-mail (*)</label><br>
            <input type="email" name="email" value="<%= usuario.getEmail()%>" required class="form-control">
            <br>


            <label>Setor (*)</label><br>
            <select id="idS" name="setor_id">
                <option value="Selecione">Selecione</option>
                <%                    ArrayList<Object> s = new SetorDAO().consultarTodos();

                    for (int i = 0; i < s.size(); i++) {
                        Setor set = (Setor) s.get(i);

                        if (set.getId() == user.getSetor_id().getId()) {


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





            <label>Senha (*)</label><br>
            <input type="password" id="senha" name="senha" required class="form-control">
            <br>

            <label>Confirmar Senha (*)</label><br>
            <input type="password" id="confirmaSenha" name="confirmaSenha" required class="form-control">
            <br>


            <label>Tipo de usuário (*)</label><br>


            <select id="tipoUsuario" name="tipoUsuario">


                <option value="0" >Selecione</option>
                <option value="1">Administrador</option>
                <option value="2">Usuário padrão</option>

            </select>


            <br>
            <br>





            <input type="submit" name="enviar" value="Salvar" data-toggle="modal" data-target="#myModal" class="btn-primary" onclick="validarSenha(), validarSetorUsuario(), validarTipoUsuario()">

            <input type="reset"  name="resetar" value="Limpar">


        </form>



        <!-- Consultar modal -->

        <!-- Modal -->
        <%            if (mensagem
                    != null) {
        %>    
        <!--        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">
                                    Registro salvo com sucesso!
                                </h4>
                            </div>
                            <div class="modal-body" id="modal-body">
        
                            </div>
                        </div>
                    </div>
                </div>-->
        <%
            }
        %>






        <br>


        <br>

    </body>
</html>