package com.flash.editorimagem.controller;

import com.flash.editorimagem.model.presets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/presets")
public class PresetController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Inserir novo preset com imagem
    @PostMapping("/adicionar")
    public String salvarPreset(@RequestParam String nome,
                               @RequestParam int saturacao,
                               @RequestParam int exposicao,
                               @RequestParam int contraste,
                               @RequestParam int realce,
                               @RequestParam int sombras,
                               @RequestParam int brancos,
                               @RequestParam int pretos,
                               @RequestParam int textura) {

        // SQL para inserir as configurações no banco de dados
        String sql = "INSERT INTO presets (nome, saturacao, exposicao, contraste, realce, sombras, brancos, pretos, textura) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Executando o SQL
        try {
            jdbcTemplate.update(sql, nome, saturacao, exposicao, contraste, realce, sombras, brancos, pretos, textura);
            return "Preset salvo com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao salvar preset.";
        }
    }

    // Listar todos os presets
    @GetMapping("/todos")
    public List<presets> listarPresets() {
        String sql = "SELECT * FROM presets";
        return jdbcTemplate.query(sql, new RowMapper<presets>() {
            @Override
            public presets mapRow(ResultSet rs, int rowNum) throws SQLException {
                presets p = new presets();
                p.setPreset_id(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSaturacao(rs.getInt("saturacao"));
                p.setExposicao(rs.getInt("exposicao"));
                p.setContraste(rs.getInt("contraste"));
                p.setRealce(rs.getInt("realce"));
                p.setSombras(rs.getInt("sombras"));
                p.setBrancos(rs.getInt("brancos"));
                p.setPretos(rs.getInt("pretos"));
                p.setTextura(rs.getInt("textura"));
                
                return p;
            }
        });
    }

    // Buscar preset por ID
    @GetMapping("/{id}")
    public presets buscarPreset(@PathVariable int id) {
        String sql = "SELECT * FROM presets WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<presets>() {
            @Override
            public presets mapRow(ResultSet rs, int rowNum) throws SQLException {
                presets p = new presets();
                p.setPreset_id(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSaturacao(rs.getInt("saturacao"));
                p.setExposicao(rs.getInt("exposicao"));
                p.setContraste(rs.getInt("contraste"));
                p.setRealce(rs.getInt("realce"));
                p.setSombras(rs.getInt("sombras"));
                p.setBrancos(rs.getInt("brancos"));
                p.setPretos(rs.getInt("pretos"));
                p.setTextura(rs.getInt("textura"));
                
                return p;
            }
        }, id);
    }
    // Deleta um preset por ID
    @DeleteMapping("/delete/{id}")
    public String deletePresets(@PathVariable int id) {
        String sql = "DELETE FROM presets WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected > 0) {
            return "Preset deletado com sucesso!";
        } else {
            return "Preset não encontrado ou não foi possível deletar.";
        }
    }
}
