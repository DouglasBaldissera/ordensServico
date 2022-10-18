<%-- 
    Document   : login
    Created on : 20/09/2017, 13:03:51
    Author     : darie
--%>

<%@page import="classe.Mensagem"%>
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


        <link href="css/csslogin.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>



        <%
            String mensagem = (String) request.getAttribute("mensagem");
        %>


        <script src="https://use.typekit.net/ayg4pcz.js"></script>
        <script>try {
                Typekit.load({async: true});
            } catch (e) {
            }</script>

        <div class="container">
            <h1 class="welcome text-center"> <br> Bem-vindo <br> </h1>
            <div class="card card-container">
                <h2 class='login_title text-center'>Ordem de serviço</h2>
                <hr>

                <form class="form-signin" name="login" action="/ordemServico/acao?parametro=login" method="post">
                    <span id="reauth-email" class="reauth-email"></span>
                    <p class="input_title">Login</p>
                    <input type="text" name="login" id="inputEmail" class="login_box" placeholder="login" required autofocus>
                    <p class="input_title">Senha</p>
                    <input type="password" id="inputPassword" name="senha" class="login_box" placeholder="senha" required>
                    <div id="remember" class="checkbox">
                        <label>

                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary" type="submit">Login</button>
                    <input type="hidden" value="Login e senha inválidos">
                    <%
                        if (mensagem != null) {
                    %>
                    <font size="2" color="red" face="Tahoma">	
                    <%=mensagem%>
                    </font> 
                    <%
                        }
                    %>

                    <br>
                    <br>


<!--                    <a href="usuario.jsp" class="btn btn-" > Criar conta</a>-->


                </form><!-- /form -->
            </div><!-- /card-container -->
        </div><!-- /container -->





        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="boots/js/bootstrap.min.js"></script>
    </body>
</html>