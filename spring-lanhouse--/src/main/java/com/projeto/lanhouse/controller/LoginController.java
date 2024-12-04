package com.projeto.lanhouse.controller;

import com.projeto.lanhouse.model.Usuario;
import com.projeto.lanhouse.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        if (usuarioService.autenticar(usuario)) {
            return "Login bem-sucedido!";
        } else {
            return "Usuário ou senha inválidos!";
        }
    }
}
