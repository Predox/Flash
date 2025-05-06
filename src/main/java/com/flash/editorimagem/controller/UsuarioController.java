package com.flash.editorimagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.flash.editorimagem.model.usuarios;

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
    public String loginUsuario(@RequestBody usuarios usuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ? AND senha = ?";
        
        // Executa a consulta para verificar se existe um usuário com o e-mail e senha informados
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuario.getEmail(), usuario.getSenha());

        if (count != null && count > 0) {
            return "Login bem-sucedido!";
        } else {
            return "Usuário ou senha inválidos.";
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
}