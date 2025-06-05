package com.tigrano.controller;

import com.tigrano.model.Vendedor;
import com.tigrano.util.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class VendedorController {
    private ObservableList<Vendedor> vendedores;
    private final String caminhoArquivo = "vendedor.dat";


    public VendedorController() {
        List<Vendedor> lista = (List<Vendedor>) Persistencia.recuperar(caminhoArquivo);
        if (lista != null) {
            vendedores = FXCollections.observableArrayList(lista);
        } else {
            vendedores = FXCollections.observableArrayList();
        }
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
        salvar();
    }

    public void removerVendedor(Vendedor vendedor) {
        vendedores.remove(vendedor);
        salvar();
    }

    public void atualizarVendedor(int index, Vendedor vendedorAtualizado) {
        vendedores.set(index, vendedorAtualizado);
        salvar();
    }

    public ObservableList<Vendedor> listarVendedores() {
        return vendedores;
    }

    private void salvar() {
        Persistencia.salvar(new java.util.ArrayList<>(vendedores), caminhoArquivo);
    }
}




