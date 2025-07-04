package com.flash.editorimagem;

import com.flash.editorimagem.model.usuarios;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private String gerarEmailComTimestamp(String prefixo) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        String timestamp = agora.format(formatter);
        return prefixo + timestamp + "@example.com";
    }

    @Test
    void deveAdicionarUsuarioComSucesso() {
        usuarios novo = new usuarios();
        novo.setNome("Pedro Teste");
        novo.setEmail(gerarEmailComTimestamp("pedroteste"));
        novo.setSenha("123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<usuarios> request = new HttpEntity<>(novo, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/usuarios/adicionar",
                request,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Cadastro bem-sucedido"));
    }

    @Test
    void deveFazerLoginComUsuarioValido() {
        usuarios novo = new usuarios();
        String emailComTimestamp = gerarEmailComTimestamp("marialogin");
        novo.setNome("Maria Login");
        novo.setEmail(emailComTimestamp);
        novo.setSenha("456");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<usuarios> criarRequest = new HttpEntity<>(novo, headers);

        restTemplate.postForEntity("http://localhost:" + port + "/usuarios/adicionar", criarRequest, String.class);

        //Faz o login
        usuarios login = new usuarios();
        login.setEmail(emailComTimestamp);
        login.setSenha("456");

        HttpEntity<usuarios> loginRequest = new HttpEntity<>(login, headers);

        ResponseEntity<String> loginResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/usuarios/login",
                loginRequest,
                String.class
        );

        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertTrue(loginResponse.getBody().contains("Login bem-sucedido"));
    }
}
