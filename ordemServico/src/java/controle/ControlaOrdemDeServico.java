/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import apoio.Data;
import dao.DispositivoDAO;
import dao.OrdemDeServicoDAO;
import dao.ProdutoDAO;
import dao.ProdutosDAO;
import dao.TipoDAO;
import entidade.Dispositivo;
import entidade.OrdemDeServico;
import entidade.Produto;
import entidade.Produtos;
import entidade.Status;
import entidade.Tipo;
import entidade.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Douglas
 */
public class ControlaOrdemDeServico {

    public boolean cadastrarOrdemDeServico(HttpServletRequest request, HttpServletResponse response) {
        OrdemDeServico os = new OrdemDeServico();

        //int id = Integer.parseInt(request.getParameter("id"));
        //os.setId(id);
        os.setDataEmissao(Data.criarDataAtual());
        os.setProblemaRelatado(request.getParameter("problemaR"));
        os.setProblemaConstatado(" ");
        os.setResolucao(" ");

        HttpSession sessao = request.getSession();
        Usuario user = (Usuario) sessao.getAttribute("usuarioLogado");

        os.setSolicitante(user);
        Dispositivo d = (Dispositivo) new DispositivoDAO().consultarId(Integer.parseInt(request.getParameter("idD")));
        os.setDispositivo_id(d);

        /*Status sta = new Status();

        
        sta.setId(1);
        os.setStatus_id(sta);
         */
        //System.out.println("ID STATUS " + os.getStatus_id().getId());
        String retorno = null;

        retorno = new OrdemDeServicoDAO().salvar(os);
        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizarOrdemDeServico(HttpServletRequest request, HttpServletResponse response) {
        OrdemDeServico os = new OrdemDeServico();

        int id = Integer.parseInt(request.getParameter("id"));
        os.setId(id);
        os.setDataEntrega(Data.criarDataAtual());
        os.setProblemaConstatado(request.getParameter("problemaC"));
        os.setProblemaRelatado(request.getParameter("problemaR"));
        os.setResolucao(request.getParameter("resolucao"));
        String x = request.getParameter("produtos");
        String y = "Selecione";
        System.out.println("XXXXXXXXX: " + x + " YYYYYYY: " + y);
        if (!x.equals(y)) {

            Produtos prod = new Produtos();

            prod.setOrdemDeServico_id(os);

            Produto p = (Produto) new ProdutoDAO().consultarId(Integer.parseInt(request.getParameter("produtos")));
            prod.setProduto_id(p);
            prod.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

            ProdutosDAO pd = new ProdutosDAO();
            pd.salvar(prod);
        }

        Status s = new Status();
        s.setId(2);
        os.setStatus_id(s);

        Tipo t = (Tipo) new TipoDAO().consultarId(Integer.parseInt(request.getParameter("tipoOS")));
        os.setTipo_id(t);

        //VER USUÁAAAAAAAAAAAAAAARIO
        //***********************************************************
        //Dispositivo d = (Dispositivo) new DispositivoDAO().consultarId(Integer.parseInt(request.getParameter("dispositivo_id")));
        //os.setDispositivo_id(d);

        /*Status sta = new Status();

        
        sta.setId(1);
        os.setStatus_id(sta);
         */
        //System.out.println("ID STATUS " + os.getStatus_id().getId());
        String retorno = null;

        retorno = new OrdemDeServicoDAO().atualizar(os);

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

}
