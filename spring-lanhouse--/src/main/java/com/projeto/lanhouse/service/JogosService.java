package com.projeto.lanhouse.service;

import com.projeto.lanhouse.model.Jogos;
import com.projeto.lanhouse.repository.JogosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogosService {

    @Autowired
    private JogosRepository jogosRepository;

    public List<Jogos> listarTodos() {
        return jogosRepository.findAll();
    }

    public Jogos buscarPorId(Long id) {
        return jogosRepository.findById(id).orElse(null);
    }

    public Jogos salvar(Jogos jogos) {
        return jogosRepository.save(jogos);
    }

    // Método de atualização
    public Jogos atualizar(Long id, Jogos jogo) {
        // Verifica se o jogo existe
        if (!jogosRepository.existsById(id)) {
            throw new RuntimeException("Jogo não encontrado para atualização.");
        }
        jogo.setId(id);  // Define o ID do jogo a ser atualizado
        return jogosRepository.save(jogo);  // Salva o jogo atualizado
    }

    public void deletar(Long id) {
        // Verifica se o jogo existe antes de tentar deletar
        if (!jogosRepository.existsById(id)) {
            throw new RuntimeException("Jogo não encontrado"); // Lança uma exceção caso o jogo não exista
        }
        jogosRepository.deleteById(id); // Deleta o jogo se existir
    }
}
