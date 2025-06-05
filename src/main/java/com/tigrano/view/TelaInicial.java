package com.tigrano.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaInicial extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("Tigrano ON");

        Button btnProduto = new Button("Cadastrar Produto");
        Button btnComprador = new Button("Cadastrar Comprador");
        Button btnAdmin = new Button("Cadastrar Admin");
        Button btnSuporte = new Button("Cadastrar Suporte");
        Button btnVendedor = new Button("Cadastrar Vendedor");

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

        btnAdmin.setOnAction(e -> {
            try {
                new AdminView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnSuporte.setOnAction(e -> {
            try {
                new SuporteView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnVendedor.setOnAction(e ->{
            try{
                new VendedorView().start(new Stage());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20, btnAdmin, btnProduto, btnComprador, btnSuporte, btnVendedor);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 600);
        stage.setTitle("Tigrano - Tela Inicial");
        stage.setScene(scene);
        stage.show();
    }
}
