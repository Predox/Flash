package com.flash.editorimagem.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/upload")
public ResponseEntity<String> uploadImagem(
        @RequestParam("arquivo") MultipartFile arquivo,
        @RequestParam("nome") String nome,
        HttpSession session) {

    Long usuarioId = (Long) session.getAttribute("usuarioId");

    if (usuarioId == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não logado. Faça login para enviar imagens.");
    }

    if (arquivo == null || arquivo.isEmpty()) {
        return ResponseEntity.badRequest().body("Nenhum arquivo foi enviado ou o arquivo está vazio.");
    }

    if (nome == null || nome.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("O nome da imagem não pode estar vazio.");
    }

    try {
        byte[] bytes = arquivo.getBytes();

        String sql = "INSERT INTO imagens (usuario_id, nome, imagem) VALUES (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, usuarioId);
            ps.setString(2, nome);
            ps.setBytes(3, bytes);
            return ps;
        });

        return ResponseEntity.ok("Imagem enviada com sucesso!");
    } catch (IOException ioEx) {
        ioEx.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao ler o arquivo enviado: " + ioEx.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno ao salvar a imagem no banco: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }
}

@GetMapping("/imagem/{id}")
public void getImagem(@PathVariable Long id, HttpServletResponse response) {
    try {
        String sql = "SELECT imagem FROM imagens WHERE id = ?";
        byte[] imagem = jdbcTemplate.queryForObject(sql, byte[].class, id);

        response.setContentType("image/jpeg"); // Altere para "image/png" se necessário
        response.getOutputStream().write(imagem);
        response.getOutputStream().flush();
    } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}

@GetMapping("/listar")
public ResponseEntity<List<Long>> listarImagens(HttpSession session) {
    Long usuarioId = (Long) session.getAttribute("usuarioId");

    if (usuarioId == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    String sql = "SELECT id FROM imagens WHERE usuario_id = ?";
    List<Long> ids = jdbcTemplate.queryForList(sql, Long.class, usuarioId);

    return ResponseEntity.ok(ids);
}
}
