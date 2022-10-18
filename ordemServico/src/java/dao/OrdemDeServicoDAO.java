/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Data;
import apoio.Formatacao;
import entidade.Dispositivo;
import entidade.OrdemDeServico;
import entidade.Status;
import entidade.Tipo;
import entidade.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Douglas
 */
public class OrdemDeServicoDAO {

    public String salvar(Object o) {
        OrdemDeServico os = (OrdemDeServico) o;
        Status st = new Status();
        st.setId(1);

        try {
            String sql = "INSERT INTO ordemdeservico (id, dataEmissao, problemaRelatado, problemaConstatado, resolucao, solicitante, dispositivo_id, status_id) VALUES (DEFAULT,"
                    + "'" + os.getDataEmissao().dataFormatada() + "', "
                    + "'" + os.getProblemaRelatado() + "', "
                    + "'" + os.getProblemaConstatado() + "', "
                    + "'" + os.getResolucao() + "', "
                    + "'" + os.getSolicitante().getId() + "', "
                    + "'" + os.getDispositivo_id().getId() + "', "
                    //+ "'" + os.getTipo_id().getId() + "', "
                    + "'1'"
                    + "); ";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar ordem de serviço: " + e);
            return e.toString();
        }

        return null;
    }

    public String atualizar(Object o) {
        OrdemDeServico os = (OrdemDeServico) o;

        try {
            String sql = "UPDATE ordemdeservico SET "
                    //+ "dataEmissao = '" + os.getDataEmissao() + "' "
                    + "dataEntrega = '" + os.getDataEntrega().dataFormatada() + "', "
                    + "problemaRelatado = '" + os.getProblemaRelatado() + "', "
                    + "problemaConstatado = '" + os.getProblemaConstatado() + "', "
                    + "resolucao = '" + os.getResolucao() + "', "
                    //+ "solicitante = '" + os.getSolicitante().getId() + "' "
                    //+ "atendente = '" + os.getAtendente().getId() + "' "
                    //+ "dispositivo_id = '" + os.getDispositivo_id().getId() + "' "
                    + "tipo_id = '" + os.getTipo_id().getId() + "', "
                    + "status_id = '" + os.getStatus_id().getId() + "' "
                    + "WHERE id = '" + os.getId() + "'";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar ordem de serviço: " + e);
            return e.toString();
        }

        return null;
    }

    public String excluir(int id) {
        try {
            String sql = "UPDATE ordemdeservico SET "
                    + "status_id = '3' "
                    + "WHERE id = " + id + "";

            int resultado = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao inativar ordem de servico: " + e);
            return e.toString();
        }

        return null;
    }

    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> ordensDeServico = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ordemdeservico ORDER BY id";

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                OrdemDeServico os = new OrdemDeServico();
                os.setId(retorno.getInt("id"));
                os.setDataEmissao(Data.criar(retorno.getString("dataEmissao")));
                os.setDataEntrega(Data.criar(retorno.getString("dataEntrega")));
                os.setProblemaRelatado(retorno.getString("problemaRelatado"));
                os.setProblemaConstatado(retorno.getString("problemaConstatado"));

                os.setResolucao(retorno.getString("resolucao"));

                Usuario uS = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("solicitante_id"));
                os.setSolicitante(uS);

                Usuario uA = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("atendente_id"));
                os.setAtendente(uA);

                Dispositivo d = (Dispositivo) new DispositivoDAO().consultarId(retorno.getInt("dispositivo_id"));
                os.setDispositivo_id(d);

                Tipo t = (Tipo) new TipoDAO().consultarId(retorno.getInt("tipo_id"));
                os.setTipo_id(t);

                Status s = (Status) new StatusDAO().consultarId(retorno.getInt("status_id"));

                os.setTipo_id(t);

                ordensDeServico.add(os);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar ordens de serviço: " + e);
            return null;
        }

        return ordensDeServico;
    }

    public boolean verificarRegistroUnico(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> oss = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ordemdeservico os WHERE os.problemaRelatado ILIKE '%" + criterio;
            System.out.println(sql);
            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                OrdemDeServico os = new OrdemDeServico();

                os.setId(retorno.getInt("id"));

                os.setDataEmissao(Data.criar(Formatacao.ajustaDataDMA(retorno.getString("dataEmissao"))));
                //os.setDataEntrega(Data.criar(Formatacao.ajustaDataDMA(retorno.getString("dataEntrega"))));
                if (retorno.getString("dataEntrega") != null) {
                    os.setDataEntrega(Data.criar(Formatacao.ajustaDataDMA(retorno.getString("dataEntrega"))));
                }
                Usuario u = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("solicitante"));

                os.setSolicitante(u);
                os.setProblemaRelatado(retorno.getString("problemaRelatado"));
                os.setProblemaConstatado(retorno.getString("problemaConstatado"));
                os.setResolucao(retorno.getString("resolucao"));

                /*
                Usuario uS = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("solicitante_id"));
                os.setSolicitante(uS);

                Usuario uA = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("atendente_id"));
                os.setAtendente(uA);
                 */
                Dispositivo d = (Dispositivo) new DispositivoDAO().consultarId(retorno.getInt("dispositivo_id"));
                os.setDispositivo_id(d);
                Tipo t = (Tipo) new TipoDAO().consultarId(retorno.getInt("tipo_id"));
                os.setTipo_id(t);

                Status s = (Status) new StatusDAO().consultarId(retorno.getInt("status_id"));
                os.setStatus_id(s);

                oss.add(os);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar ordens de serviço: " + e);
            return oss;
        }

        return oss;

    }

    public Object consultarId(int id) {
        try {
            String sql = "SELECT * FROM ordemdeservico WHERE id = " + id;

            ResultSet retorno = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (retorno.next()) {
                OrdemDeServico os = new OrdemDeServico();

                os.setId(retorno.getInt("id"));
                os.setDataEmissao(Data.criar(Formatacao.ajustaDataDMA(retorno.getString("dataemissao"))));
                //os.setDataEntrega(Data.criar(Formatacao.ajustaDataDMA(retorno.getString("dataEntrega"))));
                os.setProblemaRelatado(retorno.getString("problemaRelatado"));
                //os.setProblemaConstatado(retorno.getString("problemaConstatado"));
                //os.setResolucao(retorno.getString("resolucao"));

                /*
                Usuario uS = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("solicitante_id"));
                os.setSolicitante(uS);

                Usuario uA = (Usuario) new UsuarioDAO().consultarId(retorno.getInt("atendente_id"));
                os.setAtendente(uA);
                 */
                Dispositivo d = (Dispositivo) new DispositivoDAO().consultarId(retorno.getInt("dispositivo_id"));
                os.setDispositivo_id(d);

                return os;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar ordemdeserviço id: " + e);
            return null;
        }
        return null;
    }

    public boolean consultar(Object o) {
        return false;
    }

    public byte[] gerarRelatorio(int idTOS) {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/osStatus.jrxml"));

            Map parameters = new HashMap();

            parameters.put("idStatus", idTOS);

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public byte[] gerarRelatorioData(String data1, String data2) {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relOsData.jrxml"));

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = formato.parse(data1);
            Date d2 = formato.parse(data2);

            Map parameters = new HashMap();
            parameters.put("data1", d1);
            parameters.put("data2", d2);

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public byte[] gerarRelatorioTipo(int idD) {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/relOsTipo.jrxml"));

            Map parameters = new HashMap();

            parameters.put("dispositivo", idD);

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public byte[] gerarRelatorioCSV(String criterios) {
        String resultado = "";
        System.out.println("CRITERIOS: " + criterios);
        ArrayList<Object> oss = new ArrayList<>();
        oss = consultar(" ' OR os.status_id = " + criterios + "");
        //cabeçalho
        resultado += "Id;";
        resultado += "Emissão;";
        resultado += "Entrega;";
        resultado += "Problema constatado;";
        resultado += "Problema relatado;";
        resultado += "Resolução;";
        resultado += "Solicitante;";
        resultado += "Status;";
        resultado += "\n";
        for (int i = 0; i < oss.size(); i++) {
            OrdemDeServico or = (OrdemDeServico) oss.get(i);
            //status
            resultado += or.getId() + ";";
            resultado += or.getDataEmissao().dataFormatada() + ";";
            if (or.getDataEntrega() != null) {
                resultado += or.getDataEntrega().dataFormatada() + ";";
            } else {
                resultado += ";";
            }
            if (or.getProblemaConstatado() != null) {
                resultado += or.getProblemaConstatado() + ";";
            } else {
                resultado += ";";
            }
            if (or.getProblemaRelatado() != null) {
                resultado += or.getProblemaRelatado() + ";";
            } else {
                resultado += ";";
            }
            if (or.getResolucao() != null) {
                resultado += or.getResolucao() + ";";
            } else {
                resultado += ";";
            }
            resultado += or.getSolicitante().getNome() + ";";
            resultado += or.getStatus_id().getDescricao() + ";";
            resultado += "\n";
        }
        return resultado.getBytes();
    }

    public byte[] gerarRelatorioCSVDatas(String d1, String d2) {
        String resultado = "";
        System.out.println("CRITERIOS: " + d1 + " - " + d2);
        ArrayList<Object> oss = new ArrayList<>();
        oss = consultar(" ' OR os.dataemissao BETWEEN '" + d1 + "' AND '" + d2 + "'");
        //cabeçalho
        resultado += "Id;";
        resultado += "Emissão;";
        resultado += "Entrega;";
        resultado += "Problema constatado;";
        resultado += "Problema relatado;";
        resultado += "Resolução;";
        resultado += "Solicitante;";
        resultado += "Status;";
        resultado += "\n";
        for (int i = 0; i < oss.size(); i++) {
            OrdemDeServico or = (OrdemDeServico) oss.get(i);
            //status
            resultado += or.getId() + ";";
            resultado += or.getDataEmissao().dataFormatada() + ";";
            if (or.getDataEntrega() != null) {
                resultado += or.getDataEntrega().dataFormatada() + ";";
            } else {
                resultado += ";";
            }
            if (or.getProblemaConstatado() != null) {
                resultado += or.getProblemaConstatado() + ";";
            } else {
                resultado += ";";
            }
            if (or.getProblemaRelatado() != null) {
                resultado += or.getProblemaRelatado() + ";";
            } else {
                resultado += ";";
            }
            if (or.getResolucao() != null) {
                resultado += or.getResolucao() + ";";
            } else {
                resultado += ";";
            }
            resultado += or.getSolicitante().getNome() + ";";
            resultado += or.getStatus_id().getDescricao() + ";";
            resultado += "\n";
        }
        return resultado.getBytes();
    }

    public byte[] gerarRelatorioCSVDispositivo(String id) {
        String resultado = "";
        System.out.println("CRITERIOS: " + id);
        ArrayList<Object> oss = new ArrayList<>();
        oss = consultar(" ' OR os.dispositivo_id = '" + id + "'");
        //cabeçalho
        resultado += "Id;";
        resultado += "Emissão;";
        resultado += "Entrega;";
        resultado += "Problema constatado;";
        resultado += "Problema relatado;";
        resultado += "Resolução;";
        resultado += "Solicitante;";
        resultado += "Status;";
        resultado += "\n";
        for (int i = 0; i < oss.size(); i++) {
            OrdemDeServico or = (OrdemDeServico) oss.get(i);
            //status
            resultado += or.getId() + ";";
            resultado += or.getDataEmissao().dataFormatada() + ";";
            if (or.getDataEntrega() != null) {
                resultado += or.getDataEntrega().dataFormatada() + ";";
            } else {
                resultado += ";";
            }
            if (or.getProblemaConstatado() != null) {
                resultado += or.getProblemaConstatado() + ";";
            } else {
                resultado += ";";
            }
            if (or.getProblemaRelatado() != null) {
                resultado += or.getProblemaRelatado() + ";";
            } else {
                resultado += ";";
            }
            if (or.getResolucao() != null) {
                resultado += or.getResolucao() + ";";
            } else {
                resultado += ";";
            }
            resultado += or.getSolicitante().getNome() + ";";
            resultado += or.getStatus_id().getDescricao() + ";";
            resultado += "\n";
        }
        return resultado.getBytes();
    }
}
