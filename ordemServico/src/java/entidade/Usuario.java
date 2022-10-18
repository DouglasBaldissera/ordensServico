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
public class Usuario {

    private int id;
    private String login;
    private String senha;
    private String nome;
    private String email;
    private Setor setor_id;
    private int tipoUsuario;
    private char status;

    public Usuario() {
        id = 0;
        login = "";
        senha = "";
        nome = "";
        email = "";
        Setor s = new Setor();
        s.setId(0);
        setor_id = s;
        tipoUsuario = 0;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Setor getSetor_id() {
        return setor_id;
    }

    public void setSetor_id(Setor setor_id) {
        this.setor_id = setor_id;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
