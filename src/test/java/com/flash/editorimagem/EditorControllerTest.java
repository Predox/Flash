package com.flash.editorimagem;

import com.flash.editorimagem.controller.EditorController;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class EditorControllerTest {

    @Test
    void testEditarImagemRetornaImagem() throws Exception {
        // Cria uma imagem(vermelha)
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        byte[] imagemBytes = baos.toByteArray();

        // Simula envio de arquivo
        MockMultipartFile multipartFile = new MockMultipartFile("imagem", "imagem.png", "image/png", imagemBytes);

        // Chama o controller
        EditorController controller = new EditorController();
        ResponseEntity<byte[]> resposta = controller.editarImagem(multipartFile, 50, 0, 50, 0, 0, 0, 0, 0);

        // Verifica a resposta
        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody().length > 0);
    }
}
