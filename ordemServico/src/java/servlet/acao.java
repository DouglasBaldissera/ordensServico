/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controle.ControlaDispositivo;
import controle.ControlaOrdemDeServico;
import controle.ControlaProduto;
import controle.ControlaSetor;
import controle.ControlaStatus;
import controle.ControlaTipo;
import controle.ControlaUsuario;
import dao.DispositivoDAO;
import dao.OrdemDeServicoDAO;
import dao.ProdutoDAO;
import dao.SetorDAO;
import dao.UsuarioDAO;
import entidade.Dispositivo;
import entidade.OrdemDeServico;
import entidade.Produto;
import entidade.Setor;
import entidade.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabricio.pretto
 */
public class acao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        System.out.println("PASSEI no GET");
//        String dado = request.getParameter("cor");
//        System.out.println("Cor que veio: " + dado);
        String parametro = request.getParameter("parametro");
        System.out.println("PARAMETRO: " + parametro);
        //EDIÇÃO DE UM USUÁRIO
        if (parametro.equals("edUsuario")) {
            //VERIFICA SE O USUÁRIO É ADMINISTRADOR
            if (new ControlaUsuario().entrarUsuario(request, response)) {
                int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
                Usuario usuario = (Usuario) new UsuarioDAO().consultarId(id);

                request.setAttribute("usuario", usuario);
                encaminharPagina("usuario.jsp", request, response);
            } else {
                encaminharPagina("entraUsuario.jsp", request, response);
            }

//       EXLUSÃO DE UM USUÁRIO
        } else if (parametro.equals("exUsuario")) {

            if (new ControlaUsuario().entrarUsuario(request, response)) {

                int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

                String retorno = new UsuarioDAO().excluir(id);

                request.setAttribute("origem", "usuario.jsp");

                if (retorno == null) {
                    encaminharPagina("sucessoExclusao.jsp", request, response);
                } else {
                    encaminharPagina("erro.jsp", request, response);
                }

                encaminharPagina("usuario.jsp", request, response);
            } else {
                encaminharPagina("entraUsuario.jsp", request, response);
            }

//      LOGOUT DO USUÁRIO        
        } else if (parametro.equals("logout")) {
            System.out.println("LOGOUTTTTTT");
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            response.sendRedirect("login.jsp");

//      EDIÇÃO DO SETOR
        } else if (parametro.equals("edSetor")) {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Setor setor = (Setor) new SetorDAO().consultarId(id);
            request.setAttribute("setor", setor);
            encaminharPagina("setor.jsp", request, response);

//      EXCLUSÃO DO SETOR
        } else if (parametro.equals("exSetor")) {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

            String retorno = new SetorDAO().excluir(id);

            request.setAttribute("origem", "setor.jsp");

            if (retorno == null) {
                encaminharPagina("sucessoExclusao.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
//      EDIÇÃO DO DISPOSITIVO
        } else if (parametro.equals("edDispositivo")) {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Dispositivo dispositivo = (Dispositivo) new DispositivoDAO().consultarId(id);
            request.setAttribute("dispositivo", dispositivo);
            encaminharPagina("dispositivo.jsp", request, response);
//      EXCLUSÃO DO DISPOSITIVO
        } else if (parametro.equals("exDispositivo")) {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            String retorno = new DispositivoDAO().excluir(id);

            request.setAttribute("origem", "dispositivo.jsp");

            if (retorno == null) {
                encaminharPagina("sucessoExclusao.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
//         EDIÇÃO DO PRODUTO
        } else if (parametro.equals("edProduto")) {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            Produto produto = (Produto) new ProdutoDAO().consultarId(id);
            request.setAttribute("produto", produto);
            encaminharPagina("produto.jsp", request, response);

//      EXCLUSÃO DO PRODUTO
        } else if (parametro.equals("exProduto")) {
            int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
            String retorno = new ProdutoDAO().excluir(id);

            request.setAttribute("origem", "produto.jsp");

            if (retorno == null) {
                encaminharPagina("sucessoExclusao.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
            //EDIÇÃO DA OS
        } else if (parametro.equals("edOrdemDeServico")) {
            boolean adm = new ControlaUsuario().entrarUsuario(request, response);

            if (adm) {
                int id = Integer.parseInt(String.valueOf(request.getParameter("id")));

                OrdemDeServico os = (OrdemDeServico) new OrdemDeServicoDAO().consultarId(id);
                request.setAttribute("ordemDeServicoEdi", os);
                encaminharPagina("ordemDeServicoEd.jsp", request, response);

            } else {
                encaminharPagina("entraUsuario.jsp", request, response);
            }

            //VERIFICAÇÃO DA PERMISSÃO USUÁRIO
        } else if (parametro.equals("entraUsuario")) {

            if (new ControlaUsuario().entrarUsuario(request, response)) {

                encaminharPagina("usuario.jsp", request, response);
            } else {
                encaminharPagina("entraUsuario.jsp", request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        System.out.println("PASSEI no POST");
        //CADASTRO DE USUÁRIO
        String parametro = request.getParameter("parametro");
        String combo = request.getParameter("comboUsuario");
        System.out.println("PARAAAAAAMETRO: " + parametro);

        if (parametro.equals("cadUsuario")) {

            request.setAttribute("origem", "usuario.jsp");

            if (new ControlaUsuario().cadastrarUsuario(request, response)) {
                request.setAttribute("mensagem", "Sucesso");
                //encaminharPagina("usuario.jsp", request, response);
                response.sendRedirect("usuario.jsp");
                //out.println("ok");

            } else {
                response.sendRedirect("usuario.jsp");
            }
            //AUTENTICAÇÃO DO USUÁRIO
        } else if (parametro.equals("login")) {
            if (new ControlaUsuario().autenticarUsuario(request, response)) {
                encaminharPagina("menu.jsp", request, response);
            } else {
                //request.setAttribute("akk", combo);
                //coloca a mensagem
                request.setAttribute("mensagem", "Login e senha inválidos!");
                encaminharPagina("login.jsp", request, response);
                // encaminharPagina("erro.jsp", request, response);
            }

            //CADASTRO DE SETOR
        } else if (parametro.equals("cadSetor")) {
            request.setAttribute("origem", "setor.jsp");

            if (new ControlaSetor().cadastrarSetor(request, response)) {
                if (Integer.parseInt(request.getParameter("id")) == 0) {
                    encaminharPagina("setor.jsp", request, response);
                    System.out.println("enviaa");

                } else {
                    //encaminharPagina("setorLista.jsp", request, response);
                    response.sendRedirect("setorLista.jsp");
                    System.out.println("enviaaa2");
                }

//encaminharPagina("sucesso.jsp", request, response);
                out.println("ok");

            } else {
                //encaminharPagina("erro.jsp", request, response);
                out.println("erro");
            }

            //CADASTRO DE DISPOSITIVO
        } else if (parametro.equals("cadDispositivo")) {
            request.setAttribute("origem", "dispositivo.jsp");

            if (new ControlaDispositivo().cadastrarDispositivo(request, response)) {
                encaminharPagina("dispositivo.jsp", request, response);

            } else {
                response.sendRedirect("dispositivoLista.jsp");
            }

            //CADASTRO DE PRODUTO
        } else if (parametro.equals("cadProduto")) {
            request.setAttribute("origem", "produto.jsp");

            if (new ControlaProduto().cadastrarProduto(request, response)) {

                out.println("ok");

            } else {

                out.println("erro");
            }
            //CADASTRO DE TIPOS DE SERVIÇO
        } else if (parametro.equals("cadTipo")) {
            request.setAttribute("origem", "tipo.jsp");
            if (new ControlaTipo().cadastrarTipo(request, response)) {

                out.println("ok");

            } else {

                out.println("erro");
            }

            //CADASTRO DE TIPOS DE SERVIÇO
        } else if (parametro.equals("cadStatus")) {
            request.setAttribute("origem", "status.jsp");
            if (new ControlaStatus().cadastrarStatus(request, response)) {

                out.println("ok");

            } else {

                out.println("erro");
            }

            //CADASTRO DE ORDEM DE SERVIÇO
        } else if (parametro.equals("cadOrdemDeServico")) {

            request.setAttribute("origem", "ordemDeServico.jsp");
            int id = Integer.parseInt(request.getParameter("id"));

            //CADASTRO DE UMA NOVA OS
            if (id == 0) {
                if (new ControlaOrdemDeServico().cadastrarOrdemDeServico(request, response)) {
                    out.println("ok");
                    //encaminharPagina("sucesso.jsp", request, response);
                } else {
                    out.println("erro");
                    //encaminharPagina("erro.jsp", request, response);
                }
                //ATUALIZAÇÃO DE UMA OS
            } else {
                if (new ControlaOrdemDeServico().atualizarOrdemDeServico(request, response)) {
                    response.sendRedirect("usuario.jsp");
                    //encaminharPagina("sucesso.jsp", request, response);
                } else {
                    response.sendRedirect("usuario.jsp");
                    //encaminharPagina("erro.jsp", request, response);
                }
            }

//RELATÓRIO OS 
        } else if (parametro.equals("relOS")) {

            //request.setAttribute("origem", "relOsStatus.jsp");
//            encaminharPagina("relOrdemDeServico.jsp", request, response);
            //response.sendRedirect("relOrdemDeServico.jsp");
            response.sendRedirect("relOrdemDeServico.jsp?filtroOS=" + request.getParameter("filtroOS"));

            //RELATÓRIO OS POR DATAS PDF
        } else if (parametro.equals("relOSD")) {
            //request.setAttribute("origem", "relOsDatas.jsp");
            //encaminharPagina("relOrdemDeServicoDatas.jsp", request, response);
            response.sendRedirect("relOrdemDeServicoDatas.jsp?data1=" + request.getParameter("data1") + "&data2=" + request.getParameter("data2"));

            //RELATÓRIO OS POR DISPOSITIVOS PDF
        } else if (parametro.equals("relOsDispositivo")) {
            //request.setAttribute("origem", "relOsTipos.jsp");
            //encaminharPagina("relOsDis.jsp", request, response);
            response.sendRedirect("relOsDis.jsp?dispositivo_id=" + request.getParameter("dispositivo_id"));

            //Relatório de produtos PDF
        } else if (parametro.equals("relProdutosFiltro")) {
            request.setAttribute("origem", "relProdutosFiltro.jsp");
//            encaminharPagina("relProdutos.jsp", request, response);

            //Relatório OS CSV
        } else if (parametro.equals("relOSCSV")) {

            response.sendRedirect("csvOrdemDeServico.jsp?filtroOS=" + request.getParameter("filtroOS"));

            //geraCSV gCSV = new geraCSV(sql, request, response);
//            gCSV.gerarCSV(sql, request, response);
            //RELATÓRIO OS POR DATAS CSV
        } else if (parametro.equals("relOSDataCSV")) {
            response.sendRedirect("csvOrdemDeServicoDatas.jsp?data1=" + request.getParameter("data1") + "&data2=" + request.getParameter("data2"));

            //RELATÓRIO OS POR DISPOSITIVOS CSV
        } else if (parametro.equals("relOSDispositivoCSV")) {
            response.sendRedirect("csvOrdemDeServicoDispositivo.jsp?dispositivo_id=" + request.getParameter("dispositivo_id"));
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar página: " + e);
        }
    }

    private void encaminharPaginaComSemObjeto(String pagina, HttpServletResponse response) throws IOException {
        response.sendRedirect(pagina);
    }

}
