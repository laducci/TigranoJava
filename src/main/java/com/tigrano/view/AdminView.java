package com.tigrano.view;

import com.tigrano.controller.AdminController;
import com.tigrano.model.Admin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminView extends Application {
    private AdminController controller = new AdminController();
    private ListView<Admin> listaAdministradores = new ListView<>();

    @Override
    public void start(Stage stage) {

        TextField idField = new TextField();
        TextField nomeField = new TextField();
        TextField emailField = new TextField();
        PasswordField senhaField = new PasswordField();

        Button adicionarBtn = new Button("Adicionar Admin");
        Button editarBtn = new Button("Editar Admin");
        Button deletarBtn = new Button("Remover Admin");

        listaAdministradores.setItems(controller.listarAdministradores());

        adicionarBtn.setOnAction(e -> {
            if (listaAdministradores.getSelectionModel().getSelectedItem() != null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Você está editando um admin. Clique em 'Editar Admin'.");
                return;
            }

            if (camposValidos(idField, nomeField, emailField, senhaField)) {
                try {
                    Admin novoAdmin = new Admin(
                            idField.getText(),
                            nomeField.getText(),
                            emailField.getText(),
                            senhaField.getText()
                    );
                    controller.adicionarAdmin(novoAdmin);
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Admin criado com sucesso!");
                    System.out.println("Admin criado com sucesso: " + novoAdmin.getNome());
                    limparCampos(idField, nomeField, emailField, senhaField);
                } catch (Exception ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro ao adicionar admin.");
                }
            }
        });

        editarBtn.setOnAction(e -> {
            Admin selecionado = listaAdministradores.getSelectionModel().getSelectedItem();
            int index = listaAdministradores.getSelectionModel().getSelectedIndex();
            if (selecionado != null && index >= 0) {
                if (camposValidos(idField, nomeField, emailField, senhaField)) {
                    try {
                        Admin atualizado = new Admin(
                                idField.getText(),
                                nomeField.getText(),
                                emailField.getText(),
                                senhaField.getText()
                        );
                        controller.atualizarAdmin(index, atualizado);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Admin editado com sucesso!");
                        System.out.println("Admin editado: " + atualizado.getNome());
                        limparCampos(idField, nomeField, emailField, senhaField);
                        listaAdministradores.getSelectionModel().clearSelection();
                    } catch (Exception ex) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro ao editar admin.");
                    }
                }
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um admin para editar.");
            }
        });

        deletarBtn.setOnAction(e -> {
            Admin selecionado = listaAdministradores.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.removerAdmin(selecionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Admin removido com sucesso!");
                System.out.println("Admin removido: " + selecionado.getNome());
                limparCampos(idField, nomeField, emailField, senhaField);
                listaAdministradores.getSelectionModel().clearSelection();
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um admin para remover.");
            }
        });

        listaAdministradores.setOnMouseClicked(e -> {
            Admin a = listaAdministradores.getSelectionModel().getSelectedItem();
            if (a != null) {
                idField.setText(a.getId());
                nomeField.setText(a.getNome());
                emailField.setText(a.getEmail());
                senhaField.setText(a.getSenha());
            }
        });

        VBox layout = new VBox(10,
                new Label("ID:"), idField,
                new Label("Nome:"), nomeField,
                new Label("Email:"), emailField,
                new Label("Senha:"), senhaField,
                new HBox(10, adicionarBtn, editarBtn, deletarBtn),
                new Label("Administradores Cadastrados:"),
                listaAdministradores
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Administradores - Tigrano");
        stage.show();
    }

    private void limparCampos(TextField id, TextField nome, TextField email, TextField senha) {
        id.clear();
        nome.clear();
        email.clear();
        senha.clear();
    }

    private boolean camposValidos(TextField id, TextField nome, TextField email, TextField senha) {
        if (id.getText().isEmpty() || nome.getText().isEmpty() || email.getText().isEmpty() || senha.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo, mensagem, ButtonType.OK);
        alerta.showAndWait();
    }
}
