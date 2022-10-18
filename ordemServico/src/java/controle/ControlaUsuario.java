/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import apoio.Validacao;
import dao.SetorDAO;
import dao.UsuarioDAO;
import entidade.Setor;
import entidade.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabricio.pretto
 */
public class ControlaUsuario {

    public boolean autenticarUsuario(HttpServletRequest request, HttpServletResponse response) {
        Usuario u = new Usuario();

        u.setLogin(String.valueOf(request.getParameter("login")));
        u.setSenha(Validacao.criptografarSenhaA(request.getParameter("senha")));
        u = new UsuarioDAO().consultar(u.getLogin(), u.getSenha());
        

        if (u != null) {
            if (u.getStatus() == 'A') {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuarioLogado", u);

                return true;
            }
        }
        return false;
    }

    public boolean cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        Usuario u = new Usuario();

        int id = Integer.parseInt(request.getParameter("id"));

        u.setId(id);
        u.setLogin(request.getParameter("login"));
        u.setSenha(Validacao.criptografarSenhaA(request.getParameter("senha")));
        u.setNome(request.getParameter("nome"));
        u.setEmail(request.getParameter("email"));
        Setor s = (Setor) new SetorDAO().consultarId(Integer.parseInt(request.getParameter("setor_id")));
        u.setSetor_id(s);
        u.setTipoUsuario(Integer.parseInt(request.getParameter("tipoUsuario")));

        u.setStatus('A');
        //u.setSenha(request.getParameter("senha"));
        String retorno = null;

        if (id == 0) {
            retorno = new UsuarioDAO().salvar(u);
        } else {
            retorno = new UsuarioDAO().atualizar(u);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean entrarUsuario(HttpServletRequest request, HttpServletResponse response) {

        HttpSession sessao = request.getSession();

        Usuario user = (Usuario) sessao.getAttribute("usuarioLogado");
        //System.out.println("usuaroio: " + user.getLogin() + " senha: " + user.getSenha());

        Usuario u = (Usuario) new UsuarioDAO().consultar(user.getLogin(), user.getSenha());

        //System.out.println("usuaroio: " + u.getLogin() + " tipooo: " + u.getTipoUsuario());
        if (u.getTipoUsuario() == 1) {
            return true;
        }

        return false;
    }

}
