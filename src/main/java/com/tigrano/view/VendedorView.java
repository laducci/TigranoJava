package com.tigrano.view;

import com.tigrano.controller.VendedorController;
import com.tigrano.model.Vendedor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VendedorView extends Application {
    private VendedorController controller = new VendedorController();
    private ListView<Vendedor> listaVendedor = new ListView<>();

    @Override
    public void start(Stage stage) {

        TextField emailField = new TextField();
        TextField nomeField = new TextField();
        TextField idadeField = new TextField();
        PasswordField senhaField = new PasswordField();  // Aqui foi trocado

        Button adicionarBtn = new Button("Adicionar Cadastro");
        Button editarBtn = new Button("Editar Cadastro");
        Button deletarBtn = new Button("Remover Cadastro");

        listaVendedor.setItems(controller.listarVendedores());

        adicionarBtn.setOnAction(e -> {
            if (listaVendedor.getSelectionModel().getSelectedItem() != null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Você está editando um cadastro. Clique em 'Editar Cadastro'.");
                return;
            }

            if (camposValidos(emailField, nomeField, idadeField, senhaField)) {
                try {
                    Vendedor novoVendedor = new Vendedor(
                            emailField.getText(),
                            nomeField.getText(),
                            Integer.parseInt(idadeField.getText()),
                            senhaField.getText()
                    );
                    controller.adicionarVendedor(novoVendedor);
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro criado com sucesso!");
                    System.out.println("Cadastro criado com sucesso: " + novoVendedor.getNome());
                    limparCampos(emailField, nomeField, idadeField, senhaField);
                } catch (Exception ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro ao adicionar cadastro.");
                }
            }
        });

        editarBtn.setOnAction(e -> {
            Vendedor selecionado = listaVendedor.getSelectionModel().getSelectedItem();
            int index = listaVendedor.getSelectionModel().getSelectedIndex();
            if (selecionado != null && index >= 0) {
                if (camposValidos(emailField, nomeField, idadeField, senhaField)) {
                    try {
                        Vendedor atualizado = new Vendedor(
                                emailField.getText(),
                                nomeField.getText(),
                                Integer.parseInt(idadeField.getText()),
                                senhaField.getText()
                        );
                        controller.atualizarVendedor(index, atualizado);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro editado com sucesso!");
                        System.out.println("Cadastro editado: " + atualizado.getNome());
                        limparCampos(emailField, nomeField, idadeField, senhaField);
                        listaVendedor.getSelectionModel().clearSelection();
                    } catch (Exception ex) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro ao editar vendedor.");
                    }
                }
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um cadastro para editar.");
            }
        });

        deletarBtn.setOnAction(e -> {
            Vendedor selecionado = listaVendedor.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.removerVendedor(selecionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro removido com sucesso!");
                System.out.println("Vendedor removido: " + selecionado.getNome());
                limparCampos(emailField, nomeField, idadeField, senhaField);
                listaVendedor.getSelectionModel().clearSelection();
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um cadastro para remover.");
            }
        });

        listaVendedor.setOnMouseClicked(e -> {
            Vendedor p = listaVendedor.getSelectionModel().getSelectedItem();
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
                new Label("Vendedores Cadastrados:"),
                listaVendedor
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Vendedores - Tigrano");
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
