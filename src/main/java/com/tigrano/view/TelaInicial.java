package com.tigrano.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaInicial extends Application {

    @Override
    public void start(Stage stage) {
        Button btnProduto = new Button("Cadastrar Produto");
        Button btnComprador = new Button("Cadastrar Comprador");

        btnProduto.setOnAction(e -> {
            try {
                new ProdutoView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnComprador.setOnAction(e -> {
            try {
                new CompradorView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20, btnProduto, btnComprador);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Tigrano - Tela Inicial");
        stage.setScene(scene);
        stage.show();
    }
}
