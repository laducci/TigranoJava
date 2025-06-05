
package com.tigrano.controller;

import com.tigrano.model.Produto;
import com.tigrano.util.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProdutoController {
    private ObservableList<Produto> produtos;
    private final String caminhoArquivo = "produtos.dat";

    public ProdutoController() {
        List<Produto> lista = (List<Produto>) Persistencia.recuperar(caminhoArquivo);
        if (lista != null) {
            produtos = FXCollections.observableArrayList(lista);
        } else {
            produtos = FXCollections.observableArrayList();
        }
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        salvar();
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
        salvar();
    }

    public void atualizarProduto(int index, Produto produtoAtualizado) {
        produtos.set(index, produtoAtualizado);
        salvar();
    }

    public ObservableList<Produto> listarProdutos() {
        return produtos;
    }

    private void salvar() {
        Persistencia.salvar(new java.util.ArrayList<>(produtos), caminhoArquivo);
    }
}
