/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author darie
 */
public class Dispositivo {

    private int id;
    private String nomeRede;
    private String ip;
    private Setor setor_id;
    private char status;

    public Dispositivo() {
        id = 0;
        nomeRede = "";
        ip = "";
        Setor s = new Setor();
        s.setNome("");
        setor_id = s;
        status = 'A';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeRede() {
        return nomeRede;
    }

    public void setNomeRede(String nomeRede) {
        this.nomeRede = nomeRede;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Setor getSetor_id() {
        return setor_id;
    }

    public void setSetor_id(Setor setor_id) {
        this.setor_id = setor_id;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
