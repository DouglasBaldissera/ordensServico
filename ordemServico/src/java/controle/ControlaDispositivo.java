/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DispositivoDAO;
import dao.SetorDAO;
import entidade.Dispositivo;
import entidade.Setor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darie
 */
public class ControlaDispositivo {

    public boolean cadastrarDispositivo(HttpServletRequest request, HttpServletResponse response) {
        Dispositivo d = new Dispositivo();

        int id = Integer.parseInt(request.getParameter("id"));
        d.setId(id);
        d.setNomeRede(request.getParameter("nomeRede"));
        d.setIp(request.getParameter("ip"));

        Setor s = (Setor) new SetorDAO().consultarId(Integer.parseInt(request.getParameter("setor_id")));
        
        d.setSetor_id(s);
        String retorno = null;

        if (id == 0) {
            retorno = new DispositivoDAO().salvar(d);
        } else {
            retorno = new DispositivoDAO().atualizar(d);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

}
