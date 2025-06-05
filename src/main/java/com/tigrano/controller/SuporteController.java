package com.tigrano.controller;

import com.tigrano.model.Suporte;
import com.tigrano.util.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SuporteController {
    private ObservableList<Suporte> suportes;
    private final String caminhoArquivo = "suporte.dat";

    public SuporteController() {
        List<Suporte> lista = (List<Suporte>) Persistencia.recuperar(caminhoArquivo);
        if (lista != null) {
            suportes = FXCollections.observableArrayList(lista);
        } else {
            suportes = FXCollections.observableArrayList();
        }
    }

    public void adicionarSuporte(Suporte suporte) {
        suportes.add(suporte);
        salvar();
    }

    public void removerSuporte(Suporte suporte) {
        suportes.remove(suporte);
        salvar();
    }

    public void atualizarSuporte(int index, Suporte suporteAtualizado) {
        suportes.set(index, suporteAtualizado);
        salvar();
    }

    public ObservableList<Suporte> listarSuportes() {
        return suportes;
    }

    private void salvar() {
        Persistencia.salvar(new java.util.ArrayList<>(suportes), caminhoArquivo);
    }
}

