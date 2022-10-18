/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.StatusDAO;
import entidade.Status;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author administrador
 */
public class ControlaStatus {

    public boolean cadastrarStatus (HttpServletRequest request, HttpServletResponse response) {
        Status s = new Status();

        int id = Integer.parseInt(request.getParameter("id"));

        s.setId(id);
        s.setDescricao(request.getParameter("descricao"));
        String retorno = null;

        if (id == 0) {
            retorno = new StatusDAO().salvar(s);
        } else {
            retorno = new StatusDAO().atualizar(s);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

}
