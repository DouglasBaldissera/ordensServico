<%-- 
    Document   : menu
    Created on : 20/09/2017, 13:48:38
    Author     : dariel
--%>

<%@page import="dao.UsuarioDAO"%>
<%@page import="entidade.Usuario"%>
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Ordens de serviço</title>

        <!-- Bootstrap -->
        <link href="boots/css/bootstrap.min.css" rel="stylesheet">


    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="menu.jsp">Início</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastro <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/ordemServico/acao?parametro=entraUsuario">Usuário</a></li>
                                <li><a href="setor.jsp">Setor</a></li>
                                <li><a href="dispositivo.jsp">Dispositivo</a></li>
                                <li><a href="produto.jsp">Produto</a></li>
                                <li><a href="tipo.jsp">Tipos de ordens de serviço</a></li>
                                <li><a href="status.jsp">Status das ordens de serviço</a></li>
                                <li><a href="ordemDeServico.jsp">Ordem de serviço</a></li>
                                <li role="separator" class="divider"></li>
                            </ul>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Listagens <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="usuarioLista.jsp">Usuários</a></li>
                                <li><a href="setorLista.jsp">Setores</a></li>
                                <li><a href="dispositivoLista.jsp">Dispositivos</a></li>
                                <li><a href="produtoLista.jsp">Produtos</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="ordemDeServicoLista.jsp">Ordens de serviço</a></li>
                                <li role="separator" class="divider"></li>

                            </ul>
                        </li>


                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="relOsStatus.jsp" target="_blank">Ordens de serviço</a></li>
                                <li><a href="relOsDatas.jsp" target="_blank">Ordens de serviço por datas</a></li>
                                <li><a href="relOsDispositivos.jsp" target="_blank">Ordens de serviço por dispositivos</a></li>
                                <li><a href="relProdutosFiltro.jsp" target="_blank">Produtos</a></li>
                                <li role="separator" class="divider"></li>
                                <li role="separator" class="divider"></li>
                                <!--                                <li><a href="#">One more separated link</a></li>-->
                            </ul>
                        </li>




                        <!-- <li class="active"><a href="#">Relatórios <span class="sr-only">(current)</span></a></li>


                        <li><a href="#">Outros</a></li>
                        
                        -->

                        <%HttpSession sessao = request.getSession();

                            Usuario user = (Usuario) sessao.getAttribute("usuarioLogado");

                            String nomeUsu = user.getNome();


                        %>
                    </ul>
                    <!--                    <form class="navbar-form navbar-left">
                                            <div class="form-group">
                                                <input type="text" class="form-control" placeholder="Search">
                                            </div>
                                            <button type="submit" class="btn btn-default">Pesquisar</button>
                                        </form>-->
                    <ul class="nav navbar-nav navbar-right">
                        <!--                        <li><a href="#">Link</a></li>-->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=nomeUsu%><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/ordemServico/acao?parametro=edUsuario">Editar conta atual</a></li> 
                                <li><a href="/ordemServico/acao?parametro=logout">Sair</a></li>
                                <!--                                <li><a href="#">Another action</a></li>
                                                                <li><a href="#">Something else here</a></li>
                                                                <li role="separator" class="divider"></li>
                                                                <li><a href="#">Separated link</a></li>-->
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="boots/js/bootstrap.min.js"></script>
    </body>
</html>
