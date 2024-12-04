package com.projeto.lanhouse.lanhousejavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.projeto.lanhouse.lanhousejavafx.model.Jogo;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ListarJogosController {

    @FXML
    private TableView<Jogo> tableJogos;

    @FXML
    private TableColumn<Jogo, Long> colId;

    @FXML
    private TableColumn<Jogo, String> colNome;

    @FXML
    private TableColumn<Jogo, String> colGenero;

    @FXML
    private TableColumn<Jogo, String> colDesenvolvedor;

    @FXML
    private TableColumn<Jogo, String> colClassificacaoEtaria;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDesenvolvedor.setCellValueFactory(new PropertyValueFactory<>("desenvolvedor"));
        colClassificacaoEtaria.setCellValueFactory(new PropertyValueFactory<>("classificacaoEtaria"));

        carregarDados();
    }

    private void carregarDados() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/jogos"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                List<Jogo> jogos = mapper.readValue(response.body(), new TypeReference<List<Jogo>>() {});
                tableJogos.getItems().setAll(jogos);
            } else {
                System.err.println("Erro ao carregar dados: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

