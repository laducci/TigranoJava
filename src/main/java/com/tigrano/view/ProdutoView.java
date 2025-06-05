
package com.tigrano.view;

import com.tigrano.controller.ProdutoController;
import com.tigrano.model.Produto;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProdutoView extends Application {
    private ProdutoController controller = new ProdutoController();
    private ListView<Produto> listaProdutos = new ListView<>();

    @Override
    public void start(Stage stage) {

        TextField nomeField = new TextField();
        TextField categoriaField = new TextField();
        TextField descricaoField = new TextField();
        TextField precoField = new TextField();
        CheckBox ativoCheck = new CheckBox("Ativo");

        Button adicionarBtn = new Button("Adicionar Produto");
        Button editarBtn = new Button("Editar Produto");
        Button deletarBtn = new Button("Remover Produto");

        listaProdutos.setItems(controller.listarProdutos());

        adicionarBtn.setOnAction(e -> {
            if (listaProdutos.getSelectionModel().getSelectedItem() != null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Você está editando um produto. Clique em 'Editar Produto'.");
                return;
            }

            if (camposValidos(nomeField, categoriaField, descricaoField, precoField)) {
                try {
                    Produto novoProduto = new Produto(
                        nomeField.getText(),
                        categoriaField.getText(),
                        descricaoField.getText(),
                        Double.parseDouble(precoField.getText()),
                        ativoCheck.isSelected()
                    );
                    controller.adicionarProduto(novoProduto);
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Produto criado com sucesso!");
                    System.out.println("Produto criado com sucesso: " + novoProduto.getNome());
                    limparCampos(nomeField, categoriaField, descricaoField, precoField, ativoCheck);
                } catch (Exception ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro ao adicionar produto.");
                }
            }
        });

        editarBtn.setOnAction(e -> {
            Produto selecionado = listaProdutos.getSelectionModel().getSelectedItem();
            int index = listaProdutos.getSelectionModel().getSelectedIndex();
            if (selecionado != null && index >= 0) {
                if (camposValidos(nomeField, categoriaField, descricaoField, precoField)) {
                    try {
                        Produto atualizado = new Produto(
                            nomeField.getText(),
                            categoriaField.getText(),
                            descricaoField.getText(),
                            Double.parseDouble(precoField.getText()),
                            ativoCheck.isSelected()
                        );
                        controller.atualizarProduto(index, atualizado);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Produto editado com sucesso!");
                        System.out.println("Produto editado: " + atualizado.getNome());
                        limparCampos(nomeField, categoriaField, descricaoField, precoField, ativoCheck);
                        listaProdutos.getSelectionModel().clearSelection();
                    } catch (Exception ex) {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro ao editar produto.");
                    }
                }
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um produto para editar.");
            }
        });

        deletarBtn.setOnAction(e -> {
            Produto selecionado = listaProdutos.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.removerProduto(selecionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Produto removido com sucesso!");
                System.out.println("Produto removido: " + selecionado.getNome());
                limparCampos(nomeField, categoriaField, descricaoField, precoField, ativoCheck);
                listaProdutos.getSelectionModel().clearSelection();
            } else {
                mostrarAlerta(Alert.AlertType.WARNING, "Selecione um produto para remover.");
            }
        });

        listaProdutos.setOnMouseClicked(e -> {
            Produto p = listaProdutos.getSelectionModel().getSelectedItem();
            if (p != null) {
                nomeField.setText(p.getNome());
                categoriaField.setText(p.getCategoria());
                descricaoField.setText(p.getDescricao());
                precoField.setText(String.valueOf(p.getPreco()));
                ativoCheck.setSelected(p.isAtivo());
            }
        });

        VBox layout = new VBox(10,
            new Label("Nome:"), nomeField,
            new Label("Categoria:"), categoriaField,
            new Label("Descrição:"), descricaoField,
            new Label("Preço:"), precoField,
            ativoCheck,
            new HBox(10, adicionarBtn, editarBtn, deletarBtn),
            new Label("Produtos Cadastrados:"),
            listaProdutos
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Produtos - Tigrano");
        stage.show();
    }

    private void limparCampos(TextField nome, TextField cat, TextField desc, TextField preco, CheckBox ativo) {
        nome.clear(); cat.clear(); desc.clear(); preco.clear(); ativo.setSelected(false);
    }

    private boolean camposValidos(TextField nome, TextField cat, TextField desc, TextField preco) {
        if (nome.getText().isEmpty() || cat.getText().isEmpty() || desc.getText().isEmpty() || preco.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios.");
            return false;
        }
        try {
            Double.parseDouble(preco.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Preço deve ser um número válido.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo, mensagem, ButtonType.OK);
        alerta.showAndWait();
    }
}
