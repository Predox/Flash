package com.flash.editorimagem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.flash.editorimagem.model.usuarios;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Injeção do JdbcTemplate para trabalhar com JDBC

    @PostMapping("/adicionar")
    public String adicionarUsuario(@RequestBody usuarios usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        // Executa a inserção no banco de dados
        int result = jdbcTemplate.update(sql, usuario.getNome(), usuario.getEmail(), usuario.getSenha());

        if (result > 0) {
            return "Usuário adicionado com sucesso!";
        } else {
            return "Erro ao adicionar usuário.";
        }
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable int id) {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        int result = jdbcTemplate.update(sql, id);

        if (result > 0) {
            return "Usuário excluído com sucesso!";
        } else {
            return "Erro ao excluir usuário.";
        }
    }

    @PostMapping("/login")
public ResponseEntity<String> loginUsuario(@RequestBody usuarios usuario, HttpSession session) {
    String sql = "SELECT id FROM usuarios WHERE email = ? AND senha = ?";
    Long idUsuario = jdbcTemplate.queryForObject(sql, Long.class, usuario.getEmail(), usuario.getSenha());
    if (idUsuario != null) {
        session.setAttribute("usuarioId", idUsuario);
        return ResponseEntity.ok("Login bem-sucedido!");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
    }
}

    @GetMapping("/teste-conexao")
    public String testarConexao() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "Conexão com o banco de dados OK!";
        } catch (Exception e) {
            return "Erro ao conectar com o banco de dados: " + e.getMessage();
        }
    }

    @GetMapping("/perfil/dados")
public ResponseEntity<Map<String, Object>> getDadosPerfil(HttpSession session) {
    Long usuarioId = (Long) session.getAttribute("usuarioId");

    if (usuarioId == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    String sqlUsuario = "SELECT nome, email FROM usuarios WHERE id = ?";
    Map<String, Object> dadosUsuario = jdbcTemplate.queryForMap(sqlUsuario, usuarioId);

    Map<String, Object> resposta = new HashMap<>();
    resposta.put("nome", dadosUsuario.get("nome"));
    resposta.put("email", dadosUsuario.get("email"));

    return ResponseEntity.ok(resposta);
}
}