/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.OrdemDeServico;
import entidade.Status;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class StatusDAO {

    public String salvar(Object o) {
        Status s = (Status) o;

        try {
            String sql = "INSERT INTO status VALUES (DEFAULT,"
                    + "'" + s.getDescricao() + "' "
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar status: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Status s = (Status) o;

        try {
            String sql = "UPDATE status SET "
                    + "descricao = '" + s.getDescricao() + "' "
                    + "WHERE id = " + s.getId() + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar status: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "DELETE status "
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar status: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> statuss = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Status s = new Status();

                s.setId(retorno.getInt("id"));
                s.setDescricao(retorno.getString("descricao"));

                statuss.add(s);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuários: " + e);
            return null;
        }

        return statuss;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM status WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Status s = new Status();

                s.setId(retorno.getInt("id"));
                s.setDescricao(retorno.getString("descricao"));

                return s;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar status id: " + e);
            return null;
        }
        return null;
    }

    public boolean consultar(Object o) {
        return false;
    }

}
