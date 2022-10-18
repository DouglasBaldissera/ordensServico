/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ProdutoDAO;
import entidade.Produto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Douglas
 */
public class ControlaProduto {
    public boolean cadastrarProduto(HttpServletRequest request, HttpServletResponse response) {
        Produto p = new Produto();

        int id = Integer.parseInt(request.getParameter("id"));
        p.setId(id);
        p.setDescricao(request.getParameter("descricao"));
        
        String retorno = null;

        if (id == 0) {
            retorno = new ProdutoDAO().salvar(p);
        } else {
            retorno = new ProdutoDAO().atualizar(p);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }
    
}
