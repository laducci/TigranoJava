package com.tigrano.model;
import java.io.Serializable;

public class Suporte implements Serializable{
    private String assunto;
    private String descricao;

    public Suporte(String assunto, String descricao){
        this.assunto = assunto;
        this.descricao = descricao;
    }

    public String getAssunto(){ return assunto; }
    public void setAssunto(String assunto){this.assunto = assunto; }

    public String getDescricao(){ return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return String.format("Assunto: %s | Descrição: %s", assunto, descricao);
    }
}
