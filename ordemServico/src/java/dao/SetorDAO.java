/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Setor;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author darie
 */
public class SetorDAO {

    public String salvar(Object o) {
        Setor s = (Setor) o;

        try {
            String sql = "INSERT INTO setor VALUES (DEFAULT,"
                    + "'" + s.getNome() + "', "
                    + "'A'"
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar setor: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Setor s = (Setor) o;

        try {
            String sql = "UPDATE setor SET "
                    + "nome = '" + s.getNome() + "' "
                    + "WHERE id = " + s.getId() + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar setor: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "UPDATE setor SET "
                    + "status = 'I' "
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar setor: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> setores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM setor ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Setor s = new Setor();

                s.setId(retorno.getInt("id"));
                s.setNome(retorno.getString("nome"));
                s.setStatus(retorno.getString("status").charAt(0));

                setores.add(s);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar setores: " + e);
            return null;
        }

        return setores;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> setores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM setor WHERE nome ILIKE '%" + criterio + "%' ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Setor s = new Setor();

                s.setId(retorno.getInt("id"));
                s.setNome(retorno.getString("nome"));
                s.setStatus(retorno.getString("status").charAt(0));

                setores.add(s);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar setores: " + e);
            return null;
        }

        return setores;

    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM setor WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Setor s = new Setor();

                s.setId(retorno.getInt("id"));
                s.setNome(retorno.getString("nome"));
                s.setStatus(retorno.getString("status").charAt(0));

                return s;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar setor id: " + e);
            return null;
        }
        return null;
    }

    public boolean consultar(Object o) {
        return false;
    }

    public Setor consultarid(String nome) {
        Setor s = new Setor();

        try {
            String sql = "SELECT * "
                    + "FROM setor "
                    + "WHERE "
                    + "nome = '" + nome + "'";

            System.out.println("SQL: " + sql);
            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (retorno.next()) {
                s.setNome(retorno.getString("nome"));
                return s;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário autenticação: " + e);
            return null;
        }
        return null;
    }

}
