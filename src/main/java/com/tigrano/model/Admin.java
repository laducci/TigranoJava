package com.tigrano.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private String id;
    private String nome;
    private String email;
    private String senha;

    public Admin(String id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return String.format("ID: %s | Nome: %s | Email: %s | Senha: %s", id, nome, email, senha);
    }

}
