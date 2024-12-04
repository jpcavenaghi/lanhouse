package com.projeto.lanhouse.controller;

import com.projeto.lanhouse.model.Jogos;
import com.projeto.lanhouse.service.JogosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogosController {

    @Autowired
    private JogosService jogosService;

    @GetMapping
    public List<Jogos> listar() {
        return jogosService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogos> buscar(@PathVariable Long id) {
        Jogos jogo = jogosService.buscarPorId(id);
        if (jogo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o jogo não for encontrado
        }
        return new ResponseEntity<>(jogo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jogos> criar(@RequestBody Jogos jogos) {
        Jogos jogoCriado = jogosService.salvar(jogos);
        return new ResponseEntity<>(jogoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogos> atualizar(@PathVariable Long id, @RequestBody Jogos jogos) {
        Jogos jogoExistente = jogosService.buscarPorId(id);
        if (jogoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o jogo não for encontrado
        }

        jogos.setId(id); // Atualiza o ID do jogo
        Jogos jogoAtualizado = jogosService.salvar(jogos);
        return new ResponseEntity<>(jogoAtualizado, HttpStatus.OK); // Retorna o jogo atualizado com 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            jogosService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content se o jogo for deletado com sucesso
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o jogo não for encontrado
        }
    }
}
