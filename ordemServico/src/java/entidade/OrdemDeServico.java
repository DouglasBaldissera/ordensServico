/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import apoio.Data;

public class OrdemDeServico {

    private int id = 0;
    private Data dataEmissao;
    private Data dataEntrega;
    private String problemaRelatado = "";
    private String problemaConstatado = "";
    private String resolucao = "";
    private Usuario solicitante;
    private Usuario atendente;
    private Dispositivo dispositivo_id;
    private Tipo tipo_id;
    private Status status_id;

    public OrdemDeServico() {
        id = 0;
        problemaRelatado = "";
        problemaConstatado = "";
        resolucao = "";
        Dispositivo d = new Dispositivo();
        dispositivo_id = d;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Data dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Data getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Data dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public Dispositivo getDispositivo_id() {
        return dispositivo_id;
    }

    public void setDispositivo_id(Dispositivo dispositivo_id) {
        this.dispositivo_id = dispositivo_id;
    }

    public Tipo getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(Tipo tipo_id) {
        this.tipo_id = tipo_id;
    }

    public Status getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Status status_id) {
        this.status_id = status_id;
    }

    public String getProblemaRelatado() {
        return problemaRelatado;
    }

    public void setProblemaRelatado(String problemaRelatado) {
        this.problemaRelatado = problemaRelatado;
    }

    public String getProblemaConstatado() {
        return problemaConstatado;
    }

    public void setProblemaConstatado(String problemaConstatado) {
        this.problemaConstatado = problemaConstatado;
    }

}
