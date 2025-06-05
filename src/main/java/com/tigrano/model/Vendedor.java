package com.tigrano.model;

import java.io.Serializable;

public class Vendedor implements Serializable {
    private String email;
    private String nome;
    private int idade;
    private String senha;

    public Vendedor(String email, String nome, int idade, String senha) {
        this.email = email;
        this.nome = nome;
        this.idade = idade;
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

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Email: %s | Idade: %d", nome, email, idade);
    }
}
