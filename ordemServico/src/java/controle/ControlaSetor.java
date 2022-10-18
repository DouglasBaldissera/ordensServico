/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.SetorDAO;
import dao.UsuarioDAO;
import entidade.Setor;
import entidade.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author darie
 */
public class ControlaSetor {

    public boolean cadastrarSetor(HttpServletRequest request, HttpServletResponse response) {
        Setor s = new Setor();

        int id = Integer.parseInt(request.getParameter("id"));

        s.setId(id);
        s.setNome(request.getParameter("nome"));
        String retorno = null;

        if (id == 0) {
            retorno = new SetorDAO().salvar(s);
        } else {
            retorno = new SetorDAO().atualizar(s);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }
}
