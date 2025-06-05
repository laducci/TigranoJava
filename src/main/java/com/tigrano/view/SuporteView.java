package com.tigrano.view;


import com.tigrano.controller.SuporteController;
import com.tigrano.model.Suporte;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SuporteView extends Application {
    private SuporteController controller = new SuporteController();
    private ListView<Suporte> listaSuportes = new ListView<>();

    @Override
    public void start(Stage stage) {
        System.out.println("Tigrano ON");

        TextField assuntoField = new TextField();
        TextField descricaoField = new TextField();

        Button adicionarBtn = new Button("Adicionar Suporte");
        Button editarBtn = new Button("Editar Suporte");
        Button deletarBtn = new Button("Remover Suporte");

        listaSuportes.setItems(controller.listarSuportes());

        adicionarBtn.setOnAction(e -> {
            if (listaSuportes.getSelectionModel().getSelectedItem() != null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Você está editando um suporte. Clique em 'Editar Suporte'.");
                return;
            }

            if (camposValidos(assuntoField, descricaoField)) {
                try {
                    Suporte novoSuporte = new Suporte(
                            assuntoField.getText(),
                            descricaoField.getText()
                    );
                    controller.adicionarSuporte(novoSuporte);
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Suporte requisitado com sucesso!");
                    System.out.println("Suporte requisitado com sucesso: " + novoSuporte.getAssunto());
                    limparCampos(assuntoField, descricaoField);
                } catch (Exception ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro ao requisitar suporte.");
                }
            }
        });

        editarBtn.setOnAction(e -> {
            Suporte selecionado = listaSuportes.getSelectionModel().getSelectedItem();
            int index = listaSuportes.getSelectionModel().getSelectedIndex();
            if (selecionado != null && index >= 0) {
                if (camposValidos(assuntoField, descricaoField)) {
                    try {
                        Suporte atualizado = new Suporte(
                                assuntoField.getText(),
                                descricaoField.getText()
                        );
                        controller.atualizarSuporte(index, atualizado);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Suporte editado com sucesso!");
                        System.out.println("Suporte editado: " + atualizado.getAssunto());
                        limparCampos(assuntoField, descricaoField);
                        listaSuportes.getSelectionModel().clearSelection();
                    } catch (Exception ex) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro ao editar suporte.");
                    }
                }
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um suporte para editar.");
            }
        });

        deletarBtn.setOnAction(e -> {
            Suporte selecionado = listaSuportes.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.removerSuporte(selecionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Suporte removido com sucesso!");
                System.out.println("Suporte removido: " + selecionado.getAssunto());
                limparCampos(assuntoField, descricaoField);
                listaSuportes.getSelectionModel().clearSelection();
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um suporte para remover.");
            }
        });

        listaSuportes.setOnMouseClicked(e -> {
            Suporte s = listaSuportes.getSelectionModel().getSelectedItem();
            if (s != null) {
                assuntoField.setText(s.getAssunto());
                descricaoField.setText(s.getDescricao());
            }
        });

        VBox layout = new VBox(10,
                new Label("Assunto:"), assuntoField,
                new Label("Descrição:"), descricaoField,
                new HBox(10, adicionarBtn, editarBtn, deletarBtn),
                new Label("Suportes Requisitados:"),
                listaSuportes
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Requisição de Suporte - Tigrano");
        stage.show();
    }

    private void limparCampos(TextField assunto, TextField desc ) {
        assunto.clear(); desc.clear();
    }

    private boolean camposValidos(TextField assunto, TextField desc) {
        if (assunto.getText().isEmpty() || desc.getText().isEmpty()) {
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


