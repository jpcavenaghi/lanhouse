package com.projeto.lanhouse.service;

import com.projeto.lanhouse.model.Usuario;
import org.springframework.stereotype.Service;  // Adicionando a anotação @Service
import java.sql.*;

@Service  // Anotação que indica ao Spring que essa classe é um serviço
public class UsuarioService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/lanhouse";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // Método para autenticar usuário
    public boolean autenticar(Usuario usuario) {
        String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Se houver um registro, o login foi bem-sucedido
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
