package com.projeto.lanhouse.lanhousejavafx.controller;

import com.projeto.lanhouse.lanhousejavafx.model.Jogo;
import com.projeto.lanhouse.lanhousejavafx.service.JogoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.IOException;

public class MainViewController {

    // Instancia o serviço JogoService
    private JogoService jogoService = new JogoService();

    // Variável para armazenar o id do jogo
    private Long idJogoSelecionado;

    @FXML
    private TableView<Jogo> tabelaJogos;  // Sua tabela de jogos

    public void carregarTabelaJogos() {
        try {
            ObservableList<Jogo> listaJogos = jogoService.buscarTodosJogos();
            tabelaJogos.setItems(listaJogos); // Popula a tabela com os jogos da API
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar o erro, por exemplo, exibindo uma mensagem para o usuário
        }
    }


    // Método que captura o clique na tabela e seleciona o jogo
    @FXML
    public void onTabelaJogosClick(MouseEvent event) {
        if (event.getClickCount() == 1) {  // Verifica se o clique foi único
            Jogo jogoSelecionado = tabelaJogos.getSelectionModel().getSelectedItem();
            if (jogoSelecionado != null) {
                idJogoSelecionado = jogoSelecionado.getId();  // Atualiza o ID do jogo selecionado
                System.out.println("Jogo selecionado: " + jogoSelecionado.getNome());
            }
        }
    }


    // Método para definir o ID do jogo selecionado
    public void setIdJogoSelecionado(Long idJogo) {
        this.idJogoSelecionado = idJogo;
    }

//    // Método para abrir a tela de AtualizarJogo
//    @FXML
//    public void abrirTelaAtualizarJogo() {
//        try {
//            if (idJogoSelecionado == null) {
//                System.out.println("Nenhum jogo selecionado para atualização");
//                showAlert(Alert.AlertType.ERROR, "Erro", "Nenhum jogo selecionado para atualização.");
//                return;
//            }
//
//            // Carregar a tela FXML
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projeto/lanhouse/lanhousejavafx/view/AlterarJogo.fxml"));
//            Parent root = loader.load();
//
//            // Obter o controller da tela de atualização
//            AlterarJogoController controller = loader.getController();
//
//            // Buscar o jogo com o ID selecionado e carregar os dados na tela
//            Jogo jogo = jogoService.buscarJogoPorId(idJogoSelecionado);
//            if (jogo == null) {
//                System.out.println("Jogo não encontrado com o ID: " + idJogoSelecionado);
//                return;
//            }
//
//            // Passar os dados do jogo para o controller da tela de atualização
//            controller.carregarDados(jogo);
//
//            // Criar uma nova cena e exibir
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setTitle("Atualizar Jogo");
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Erro ao abrir a tela de atualização", "Não foi possível carregar a tela de atualização.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
//        }
//    }

    // Método para abrir a tela de Adicionar Jogo
    public void abrirTelaAdicionarJogo() {
        abrirNovaTela("/com/projeto/lanhouse/lanhousejavafx/view/AdicionarJogo.fxml", "Adicionar Jogo");
    }

    // Método para abrir a tela de Listar Jogos
    public void abrirTelaListarJogos() {
        abrirNovaTela("/com/projeto/lanhouse/lanhousejavafx/view/ListarJogos.fxml", "Listar Jogos");
    }

    // Método para abrir a tela de Deletar Jogo
    public void abrirTelaDeletarJogo() {
        abrirNovaTela("/com/projeto/lanhouse/lanhousejavafx/view/DeletarJogo.fxml", "Deletar Jogo");
    }

    // Método para abrir uma nova tela
    private void abrirNovaTela(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para exibir alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
