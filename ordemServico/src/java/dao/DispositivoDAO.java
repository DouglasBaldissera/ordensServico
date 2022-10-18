/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import entidade.Dispositivo;
import entidade.Setor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author darie
 */
public class DispositivoDAO {

    public String salvar(Object o) {
        Dispositivo d = (Dispositivo) o;

        try {
            String sql = "INSERT INTO dispositivo VALUES (DEFAULT,"
                    + "'" + d.getNomeRede() + "', "
                    + "'" + d.getIp() + "', "
                    + "'" + d.getSetor_id().getId() + "', "
                    + "'A'"
                    + ")";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar dispositivo: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        Dispositivo d = (Dispositivo) o;

        try {
            String sql = "UPDATE dispositivo SET "
                    + "nomerede = '" + d.getNomeRede() + "', "
                    + "ip = '" + d.getIp() + "', "
                    + "setor_id = '" + d.getSetor_id().getId() + "' "
                    + "WHERE id = " + d.getId();

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar dispositivo: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "UPDATE dispositivo SET "
                    + "status = 'I' "
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar dispositivo: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> dispositivos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM dispositivo ORDER BY nomerede";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Dispositivo d = new Dispositivo();

                d.setId(retorno.getInt("id"));
                d.setNomeRede(retorno.getString("nomerede"));
                d.setIp(retorno.getString("ip"));
                //Setor s = new Setor();
                Setor s = (Setor) new SetorDAO().consultarId(retorno.getInt("setor_id"));

                //s = (Setor) s.setId(retorno.getInt("setor_id"));
                d.setSetor_id(s);

                d.setStatus(retorno.getString("status").charAt(0));
                dispositivos.add(d);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar dispositivos: " + e);
            return null;
        }

        return dispositivos;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM dispositivo WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                Dispositivo d = new Dispositivo();
                d.setId(retorno.getInt("id"));
                d.setNomeRede(retorno.getString("nomerede"));
                d.setIp(retorno.getString("ip"));
                //Setor s = new Setor();
                Setor s = (Setor) new SetorDAO().consultarId(retorno.getInt("setor_id"));
                d.setSetor_id(s);
                System.out.println("CASA: " + s.getNome());

                d.setStatus(retorno.getString("status").charAt(0));

                return d;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar dispositivo id: " + e);
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
                    + "FROM dispositivo "
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

    public byte[] gerarRelatorio() {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();

            byte[] bytes = null;
            ArrayList<Object> dis = consultarTodos();
            for (int i = 0; i < dis.size(); i++) {
                Dispositivo d = (Dispositivo) dis.get(i);
                String x = d.getId() + ";" + d.getNomeRede();
                
                
            }
            

            //byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatorio: " + e);
        }
        return null;
    }
}
