/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Produto;
import entidade.Setor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Douglas
 */
public class ProdutoDAO {

    public String salvar(Object o) {
        Produto p = (Produto) o;

        try {
            String sql = "INSERT INTO produto VALUES (DEFAULT,"
                    + "'" + p.getDescricao() + "', "
                    + "'A'"
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar produto: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Produto p = (Produto) o;

        try {
            String sql = "UPDATE produto SET "
                    + "descricao = '" + p.getDescricao() + "' "
                    + "WHERE id = " + p.getId() + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "UPDATE produto SET "
                    + "status = 'I' "
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar produto: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Produto p = new Produto();

                p.setId(retorno.getInt("id"));
                p.setDescricao(retorno.getString("descricao"));
                p.setStatus(retorno.getString("status").charAt(0));

                produtos.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuários: " + e);
            return null;
        }

        return produtos;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM produto WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Produto p = new Produto();

                p.setId(retorno.getInt("id"));
                p.setDescricao(retorno.getString("descricao"));
                p.setStatus(retorno.getString("status").charAt(0));

                return p;
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

    public Setor consultarid(String descricao) {
        Setor s = new Setor();

        try {
            String sql = "SELECT * "
                    + "FROM produto "
                    + "WHERE "
                    + "descricao = '" + descricao + "'";

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

    public byte[] gerarRelatorio(int id) {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relProdutos.jrxml"));

            Map parameters = new HashMap();
            parameters.put("id", id);

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatorio: " + e);
        }
        return null;
    }
}
