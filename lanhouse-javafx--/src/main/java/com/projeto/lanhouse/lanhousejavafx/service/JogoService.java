package com.projeto.lanhouse.lanhousejavafx.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.lanhouse.lanhousejavafx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JogoService {

    private static final String API_URL = "http://localhost:8080/api/jogos/";

    // Método para buscar todos os jogos da API
    public ObservableList<Jogo> buscarTodosJogos() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int status = connection.getResponseCode();

        if (status == HttpURLConnection.HTTP_OK) {
            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                ObjectMapper objectMapper = new ObjectMapper();
                // Mapeando a resposta JSON para uma lista de jogos
                List<Jogo> jogos = objectMapper.readValue(reader, objectMapper.getTypeFactory().constructCollectionType(List.class, Jogo.class));
                return FXCollections.observableArrayList(jogos);
            }
        } else {
            throw new IOException("Erro ao buscar os jogos da API.");
        }
    }

    // Método para buscar um jogo por ID via API
    public Jogo buscarJogoPorId(Long id) throws Exception {
        URL url = new URL(API_URL + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int status = connection.getResponseCode();

        if (status == HttpURLConnection.HTTP_OK) {
            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(reader, Jogo.class);
            }
        } else {
            throw new Exception("Erro ao buscar o jogo.");
        }
    }

    // Método para deletar um jogo
    public boolean deletarJogo(Long idJogo) throws IOException {
        URL url = new URL(API_URL + idJogo);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/json");

        connection.setDoOutput(false);

        int responseCode = connection.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_NO_CONTENT; // 204
    }

    // Método para atualizar o jogo via API
    // Método para atualizar o jogo via API
    public Jogo atualizarJogo(Jogo jogo) throws Exception {
        URL url = new URL(API_URL + jogo.getId());  // URL para atualizar o jogo
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT"); // Método PUT para atualizar
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true); // Definir como true para enviar o corpo da requisição

        // Convertendo o objeto Jogo para JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInputString = objectMapper.writeValueAsString(jogo);

        // Usar o OutputStream para enviar os dados no corpo da requisição
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input);  // Envia os dados do jogo no corpo da requisição
        }

        // Verificar a resposta da requisição
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            // Se a resposta for 200 OK, retornamos o jogo atualizado
            return jogo;
        } else {
            throw new Exception("Erro ao atualizar o jogo.");
        }
    }

}
