package com.projeto.lanhouse.lanhousejavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class AdicionarJogoController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField generoField;

    @FXML
    private TextField desenvolvedorField;

    @FXML
    private TextField classificacaoEtariaField;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @FXML
    public void adicionarJogo() {
        try {
            // Obter valores dos campos
            String nome = nomeField.getText();
            String genero = generoField.getText();
            String desenvolvedor = desenvolvedorField.getText();
            String classificacaoEtaria = classificacaoEtariaField.getText();

            // Validar campos (opcional)
            if (nome.isEmpty() || genero.isEmpty() || desenvolvedor.isEmpty() || classificacaoEtaria.isEmpty()) {
                showAlert("Erro", "Todos os campos são obrigatórios!", Alert.AlertType.ERROR);
                return;
            }

            // Criar o JSON do jogo
            String jsonBody = String.format("""
            {
                "nome": "%s",
                "genero": "%s",
                "desenvolvedor": "%s",
                "classificacaoEtaria": "%s"
            }
            """, nome, genero, desenvolvedor, classificacaoEtaria);

            // Criar a requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/jogos")) // Ajuste para o endpoint correto da sua API
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // Enviar a requisição
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o código de status
            if (response.statusCode() == 201) { // Status de sucesso para criação
                showAlert("Sucesso", "Jogo adicionado com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                String errorMessage = "Falha ao adicionar jogo: " + response.body();
                if (response.statusCode() == 400) {
                    // Caso tenha um erro específico, por exemplo, dados inválidos
                    errorMessage = "Dados inválidos: " + response.body();
                } else if (response.statusCode() == 500) {
                    // Caso ocorra erro no servidor
                    errorMessage = "Erro no servidor: " + response.body();
                }
                showAlert("Erro", errorMessage, Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
