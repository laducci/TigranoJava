package com.tigrano.view;

import com.tigrano.controller.CompradorController;
import com.tigrano.model.Comprador;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CompradorView extends Application {
    private CompradorController controller = new CompradorController();
    private ListView<Comprador> listaComprador = new ListView<>();

    @Override
    public void start(Stage stage) {
        System.out.println("Tigrano ON");

        TextField emailField = new TextField();
        TextField nomeField = new TextField();
        TextField idadeField = new TextField();
        PasswordField senhaField = new PasswordField();  // Aqui foi trocado

        Button adicionarBtn = new Button("Adicionar Cadastro");
        Button editarBtn = new Button("Editar Cadastro");
        Button deletarBtn = new Button("Remover Cadastro");

        listaComprador.setItems(controller.listarCompradores());

        adicionarBtn.setOnAction(e -> {
            if (listaComprador.getSelectionModel().getSelectedItem() != null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Você está editando um cadastro. Clique em 'Editar Cadastro'.");
                return;
            }

            if (camposValidos(emailField, nomeField, idadeField, senhaField)) {
                try {
                    Comprador novoComprador = new Comprador(
                            emailField.getText(),
                            nomeField.getText(),
                            Integer.parseInt(idadeField.getText()),
                            senhaField.getText()
                    );
                    controller.adicionarComprador(novoComprador);
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro criado com sucesso!");
                    System.out.println("Cadastro criado com sucesso: " + novoComprador.getNome());
                    limparCampos(emailField, nomeField, idadeField, senhaField);
                } catch (Exception ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro ao adicionar cadastro.");
                }
            }
        });

        editarBtn.setOnAction(e -> {
            Comprador selecionado = listaComprador.getSelectionModel().getSelectedItem();
            int index = listaComprador.getSelectionModel().getSelectedIndex();
            if (selecionado != null && index >= 0) {
                if (camposValidos(emailField, nomeField, idadeField, senhaField)) {
                    try {
                        Comprador atualizado = new Comprador(
                                emailField.getText(),
                                nomeField.getText(),
                                Integer.parseInt(idadeField.getText()),
                                senhaField.getText()
                        );
                        controller.atualizarComprador(index, atualizado);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro editado com sucesso!");
                        System.out.println("Cadastro editado: " + atualizado.getNome());
                        limparCampos(emailField, nomeField, idadeField, senhaField);
                        listaComprador.getSelectionModel().clearSelection();
                    } catch (Exception ex) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro ao editar comprador.");
                    }
                }
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um cadastro para editar.");
            }
        });

        deletarBtn.setOnAction(e -> {
            Comprador selecionado = listaComprador.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.removerComprador(selecionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro removido com sucesso!");
                System.out.println("Comprador removido: " + selecionado.getNome());
                limparCampos(emailField, nomeField, idadeField, senhaField);
                listaComprador.getSelectionModel().clearSelection();
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um cadastro para remover.");
            }
        });

        listaComprador.setOnMouseClicked(e -> {
            Comprador p = listaComprador.getSelectionModel().getSelectedItem();
            if (p != null) {
                emailField.setText(p.getEmail());
                nomeField.setText(p.getNome());
                idadeField.setText(String.valueOf(p.getIdade()));
                senhaField.setText(p.getSenha());
            }
        });

        VBox layout = new VBox(10,
                new Label("Email:"), emailField,
                new Label("Nome:"), nomeField,
                new Label("Idade:"), idadeField,
                new Label("Senha:"), senhaField,
                new HBox(10, adicionarBtn, editarBtn, deletarBtn),
                new Label("Compradores Cadastrados:"),
                listaComprador
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Compradores - Tigrano");
        stage.show();
    }

    private void limparCampos(TextField email, TextField nome, TextField idade, TextField senha) {
        email.clear();
        nome.clear();
        idade.clear();
        senha.clear();
    }

    private boolean camposValidos(TextField email, TextField nome, TextField idade, TextField senha) {
        if (email.getText().isEmpty() || nome.getText().isEmpty() || idade.getText().isEmpty() || senha.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios.");
            return false;
        }
        try {
            Integer.parseInt(idade.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Idade deve ser um número inteiro válido.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo, mensagem, ButtonType.OK);
        alerta.showAndWait();
    }
}
