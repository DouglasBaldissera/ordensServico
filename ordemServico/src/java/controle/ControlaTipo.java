/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.TipoDAO;
import entidade.Tipo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author administrador
 */
public class ControlaTipo {

    public boolean cadastrarTipo(HttpServletRequest request, HttpServletResponse response) {
        Tipo t = new Tipo();

        int id = Integer.parseInt(request.getParameter("id"));

        t.setId(id);
        t.setDescricao(request.getParameter("descricao"));
        String retorno = null;

        if (id == 0) {
            retorno = new TipoDAO().salvar(t);
        } else {
            retorno = new TipoDAO().atualizar(t);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

}
