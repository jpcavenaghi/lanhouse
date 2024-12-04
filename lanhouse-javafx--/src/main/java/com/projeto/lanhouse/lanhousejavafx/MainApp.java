package com.projeto.lanhouse.lanhousejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projeto/lanhouse/lanhousejavafx/view/login.fxml"));
        AnchorPane root = loader.load();

        // Configura a cena e exibe
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Tela de Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}