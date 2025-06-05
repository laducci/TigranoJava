
package com.tigrano.controller;

import com.tigrano.model.Comprador;
import com.tigrano.model.Produto;
import com.tigrano.util.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CompradorController {
    private ObservableList<Comprador> compradores;
    private final String caminhoArquivo = "comprador.dat";

    public CompradorController() {
        List<Comprador> lista = (List<Comprador>) Persistencia.recuperar(caminhoArquivo);
        if (lista != null) {
            compradores = FXCollections.observableArrayList(lista);
        } else {
            compradores = FXCollections.observableArrayList();
        }
    }

    public void adicionarComprador(Comprador comprador) {
        compradores.add(comprador);
        salvar();
    }

    public void removerComprador(Comprador comprador) {
        compradores.remove(comprador);
        salvar();
    }

    public void atualizarComprador(int index, Comprador compradorAtualizado) {
        compradores.set(index, compradorAtualizado);
        salvar();
    }

    public ObservableList<Comprador> listarCompradores() {
        return compradores;
    }

    private void salvar() {
        Persistencia.salvar(new java.util.ArrayList<>(compradores), caminhoArquivo);
    }
}
