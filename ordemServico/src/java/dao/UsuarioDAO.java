/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Setor;
import entidade.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author darie
 */
public class UsuarioDAO {

    public String salvar(Object o) {
        Usuario u = (Usuario) o;

        try {
            String sql = "INSERT INTO usuario VALUES (DEFAULT,"
                    + "'" + u.getLogin() + "', "
                    + "'" + u.getSenha() + "', "
                    + "'" + u.getNome() + "', "
                    + "'" + u.getEmail() + "', "
                    + "'" + u.getSetor_id().getId() + "', "
                    + "'" + u.getTipoUsuario() + "', "
                    + "'" + u.getStatus() + "'"
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Usuario u = (Usuario) o;

        try {
            String sql = "UPDATE usuario SET "
                    + "login = '" + u.getLogin() + "', "
                    + "senha = '" + u.getSenha() + "', "
                    + "email = '" + u.getEmail() + "', "
                    + "setor_id = '" + u.getSetor_id().getId() + "', "
                    + "tipoUsuario = '" + u.getTipoUsuario() + "', "
                    + "status = '" + u.getStatus() + "' "
                    + "WHERE id = " + u.getId() + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "UPDATE usuario SET "
                    + "status = 'I' "
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar/excluir usuário: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuario ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Usuario u = new Usuario();

                u.setId(retorno.getInt("id"));
                u.setLogin(retorno.getString("login"));
                u.setNome(retorno.getString("nome"));
                u.setEmail(retorno.getString("email"));
                Setor s = (Setor) new SetorDAO().consultarId(Integer.parseInt(retorno.getString("setor_id")));
                u.setSetor_id(s);
                u.setTipoUsuario(Integer.parseInt(retorno.getString("tipoUsuario")));;
                u.setStatus(retorno.getString("status").charAt(0));

                usuarios.add(u);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuários: " + e);
            return null;
        }

        return usuarios;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM usuario WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Usuario u = new Usuario();

                u.setId(retorno.getInt("id"));
                u.setLogin(retorno.getString("login"));
                u.setNome(retorno.getString("nome"));
                u.setEmail(retorno.getString("email"));
                Setor s = (Setor) new SetorDAO().consultarId(Integer.parseInt(retorno.getString("setor_id")));
                u.setSetor_id(s);
                u.setTipoUsuario(Integer.parseInt(retorno.getString("tipoUsuario")));;
                u.setStatus(retorno.getString("status").charAt(0));

                return u;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário id: " + e);
            return null;
        }
        return null;
    }

    public boolean consultar(Object o) {
        return false;
    }

    public Usuario consultar(String login, String senha) {
        Usuario u = new Usuario();

        try {
            String sql = "SELECT * "
                    + "FROM usuario "
                    + "WHERE "
                    + "login = '" + login + "' AND "
                    + "senha = '" + senha + "' AND status = 'A'";

            System.out.println("SQL: " + sql);
            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (retorno.next()) {
                u.setId(retorno.getInt("id"));
                u.setLogin(retorno.getString("login"));
                u.setSenha(retorno.getString("senha"));
                u.setNome(retorno.getString("nome"));
                u.setEmail(retorno.getString("email"));
                //DAO SETOR...........
                //u.setSetor_id(retorno.getInt("setor_id"));
                u.setTipoUsuario(retorno.getInt("tipoUsuario"));
                u.setStatus(retorno.getString("status").charAt(0));

                String g = "s";
                

                return u;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário autenticação: " + e);
            return null;
        }
        return null;

    }
}
