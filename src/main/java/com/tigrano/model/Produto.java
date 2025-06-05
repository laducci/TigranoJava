
package com.tigrano.model;
import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private String categoria;
    private String descricao;
    private double preco;
    private boolean ativo;

    public Produto(String nome, String categoria, String descricao, double preco, boolean ativo) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return String.format("Nome: %s | Categoria: %s | Preço: R$%.2f | Status: %s",
                nome,
                categoria,
                preco,
                ativo ? "Ativo" : "Inativo"
        );
    }
}
