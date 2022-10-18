/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Status;
import entidade.Tipo;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class TipoDAO {

    public String salvar(Object o) {
        Tipo t = (Tipo) o;

        try {
            String sql = "INSERT INTO tipo VALUES (DEFAULT,"
                    + "'" + t.getDescricao() + "' "
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar tipo: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Tipo t = (Tipo) o;

        try {
            String sql = "UPDATE tipo SET "
                    + "descricao = '" + t.getDescricao() + "' "
                    + "WHERE id = " + t.getId() + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar tipo: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "DELETE tipo"
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao exluir tipo: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> tipos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tipo ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Tipo t = new Tipo();

                t.setId(retorno.getInt("id"));
                t.setDescricao(retorno.getString("descricao"));

                tipos.add(t);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar tipo de OS: " + e);
            return null;
        }

        return tipos;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM tipo WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Tipo t = new Tipo();

                t.setId(retorno.getInt("id"));
                t.setDescricao(retorno.getString("descricao"));

                return t;
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
