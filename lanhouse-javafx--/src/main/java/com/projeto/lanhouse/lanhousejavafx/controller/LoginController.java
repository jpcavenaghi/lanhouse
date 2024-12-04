package com.projeto.lanhouse.lanhousejavafx.controller;

import com.projeto.lanhouse.lanhousejavafx.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // URL da API de login
    private static final String API_URL = "http://localhost:8080/api/login";

    // Método chamado quando o botão de login é pressionado
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            // Chama a função que autentica com a API
            if (autenticar(username, password)) {
                System.out.println("Login bem-sucedido!");
                redirecionarParaMainView();  // Chama o método para redirecionar
            } else {
                showAlert(AlertType.ERROR, "Erro", "Usuário ou senha inválidos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erro", "Erro ao tentar fazer o login.");
        }
    }

    // Método que faz a requisição para a API de login
    private boolean autenticar(String username, String password) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Corpo da requisição com o nome de usuário e senha
        String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();

        // Se a resposta for 200 OK, o login foi bem-sucedido
        return responseCode == HttpURLConnection.HTTP_OK;
    }

    // Método para redirecionar para a MainView.fxml
    private void redirecionarParaMainView() throws IOException {
        // Carrega a MainView.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projeto/lanhouse/lanhousejavafx/view/MainView.fxml"));
        VBox root = loader.load(); // Usa VBox em vez de AnchorPane

        // Cria a nova cena
        Scene scene = new Scene(root);

        // Obtém o estágio atual (presumivelmente o estágio do login)
        Stage stage = (Stage) usernameField.getScene().getWindow();

        // Altera a cena
        stage.setScene(scene);
        stage.show();
    }

    // Método para exibir alertas
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
