/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.OrdemDeServico;
import entidade.Produto;
import entidade.Produtos;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public class ProdutosDAO {

    public String salvar(Object o) {
        Produtos p = (Produtos) o;

        try {
            String sql = "INSERT INTO produtos VALUES ("
                    + "'" + p.getOrdemDeServico_id().getId() + "', "
                    + "'" + p.getProduto_id().getId() + "', "
                    + "'" + p.getQuantidade() + "' "
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar produto: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Produtos p = (Produtos) o;

        try {
            String sql = "UPDATE produtos SET "
                    + "OrdemDeServico_id = '" + p.getOrdemDeServico_id() + "' "
                    + "Produto_id = '" + p.getProduto_id() + "' "
                    + "quantidade = '" + p.getQuantidade() + "'; ";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar produtos: " + e);
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
            String sql = "SELECT * FROM produtos ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Produtos prod = new Produtos();
                OrdemDeServico os = (OrdemDeServico) new OrdemDeServicoDAO().consultarId(retorno.getInt("OrdemDeServico_id"));
                Produto p = (Produto) new ProdutoDAO().consultarId(retorno.getInt("Produto_id"));
                prod.setOrdemDeServico_id(os);
                prod.setProduto_id(p);
                prod.setQuantidade(retorno.getInt("quantidade"));

                produtos.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar produtos: " + e);
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
            //NÃOOOOOOOOOOOOOOOOOOOOOOOO FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
            String sql = "SELECT * FROM produtos WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Produtos p = new Produtos();

//                p.setOrdemDeServico_id(retorno.getInt("OrdemDeServico_id"));
//                p.setProduto_id(retorno.getInt("Produto_id"));
//                p.setQuantidade(retorno.getInt("quantidade"));

                return p;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar produtos: " + e);
            return null;
        }
        return null;
    }

    public boolean consultar(Object o) {
        return false;
    }

    public Produtos consultarid(String descricao) {
        Produtos p = new Produtos();

        try {
            String sql = "SELECT * "
                    + "FROM produtos "
                    + "WHERE "
                    + "descricao = '" + descricao + "'";

            System.out.println("SQL: " + sql);
            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
//NÃAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAO FUNCIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
            if (retorno.next()) {
//                p.setOrdemDeServico_id(retorno.getInt("OrdemDeServico_id"));
                return p;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar produtos: " + e);
            return null;
        }
        return null;
    }
}
