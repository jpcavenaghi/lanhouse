package com.projeto.lanhouse.lanhousejavafx.controller;

import com.projeto.lanhouse.lanhousejavafx.service.JogoService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DeletarJogoController {

    @FXML
    private TextField idJogoTextField;

    private JogoService jogoService = new JogoService(); // O serviço que se comunica com a API

    // Método que é chamado quando o botão "Deletar Jogo" é pressionado
    public void deletarJogo() {
        try {
            String idJogoTexto = idJogoTextField.getText();
            if (idJogoTexto.isEmpty()) {
                showAlert(AlertType.ERROR, "Erro", "Por favor, insira o ID do jogo.");
                return;
            }

            Long idJogo = Long.parseLong(idJogoTexto);

            // Chama o serviço para deletar o jogo
            boolean sucesso = jogoService.deletarJogo(idJogo);

            if (sucesso) {
                showAlert(AlertType.INFORMATION, "Sucesso", "Jogo deletado com sucesso.");
            } else {
                showAlert(AlertType.ERROR, "Erro", "Falha ao deletar o jogo. O jogo não foi encontrado.");
            }

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erro", "ID inválido. Por favor, insira um número válido.");
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Erro", "Erro de conexão com a API.");
            e.printStackTrace(); // Imprime o erro detalhado no console
        }
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