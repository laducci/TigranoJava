package com.tigrano.controller;

import com.tigrano.model.Admin;
import com.tigrano.util.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AdminController {
    private ObservableList<Admin> administradores;
    private final String caminhoArquivo = "Admin.dat";

    public AdminController() {
        List<Admin> lista = (List<Admin>) Persistencia.recuperar(caminhoArquivo);
        if (lista != null) {
            administradores = FXCollections.observableArrayList(lista);
        } else {
            administradores = FXCollections.observableArrayList();
        }
    }

    public void adicionarAdmin(Admin admin) {
        administradores.add(admin);
        salvar();
    }

    public void removerAdmin(Admin admin) {
        administradores.remove(admin);
        salvar();
    }

    public void atualizarAdmin(int index, Admin adminAtualizado) {
        administradores.set(index, adminAtualizado);
        salvar();
    }

    public ObservableList<Admin> listarAdministradores() {
        return administradores;
    }

    private void salvar() {
        Persistencia.salvar(new java.util.ArrayList<>(administradores), caminhoArquivo);
    }
}
