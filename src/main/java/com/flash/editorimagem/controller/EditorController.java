package com.flash.editorimagem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
@CrossOrigin(origins = "*")
public class EditorController {

    @PostMapping("/editar")
    public ResponseEntity<byte[]> editarImagem(
        @RequestParam("imagem") MultipartFile imagem,
        @RequestParam(defaultValue = "0") int saturacao,
        @RequestParam(defaultValue = "0") int exposicao,
        @RequestParam(defaultValue = "0") int contraste,
        @RequestParam(defaultValue = "0") int realce,
        @RequestParam(defaultValue = "0") int sombras,
        @RequestParam(defaultValue = "0") int brancos,
        @RequestParam(defaultValue = "0") int pretos,
        @RequestParam(defaultValue = "0") int textura
    ) {
        try {
            BufferedImage original = ImageIO.read(imagem.getInputStream());
            BufferedImage editada = aplicarEfeitos(original, saturacao, exposicao, contraste, realce, sombras, brancos, pretos, textura);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(editada, "png", baos);

            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(baos.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private BufferedImage aplicarEfeitos(BufferedImage img, int saturacao, int exposicao, int contraste, int realce, int sombras, int brancos, int pretos, int textura) {
        BufferedImage nova = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        float fatorContraste = (259f * (contraste + 255)) / (255 * (259 - contraste));
        float fatorSaturacao = saturacao / 100f;
        int brilho = exposicao;

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color cor = new Color(img.getRGB(x, y));
                int r = cor.getRed();
                int g = cor.getGreen();
                int b = cor.getBlue();

                // Exposição (brilho)
                r += brilho;
                g += brilho;
                b += brilho;

                // Contraste
                r = (int) (fatorContraste * (r - 128) + 128);
                g = (int) (fatorContraste * (g - 128) + 128);
                b = (int) (fatorContraste * (b - 128) + 128);

                // Saturação
                float[] hsb = Color.RGBtoHSB(clamp(r), clamp(g), clamp(b), null);
                hsb[1] = clamp(hsb[1] + fatorSaturacao, 0f, 1f);
                int rgbSaturado = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                Color finalCor = new Color(rgbSaturado);

                r = finalCor.getRed();
                g = finalCor.getGreen();
                b = finalCor.getBlue();

                // Pontos brancos e pretos
                r = Math.min(255, r + brancos);
                g = Math.min(255, g + brancos);
                b = Math.min(255, b + brancos);

                r = Math.max(0, r - pretos);
                g = Math.max(0, g - pretos);
                b = Math.max(0, b - pretos);

                // Sombras (tons médios)
                r += sombras / 4;
                g += sombras / 4;
                b += sombras / 4;

                // Textura = aumento do contraste leve
                if (textura != 0) {
                    float fatorTextura = (259f * (textura + 255)) / (255f * (259 - textura));
                    r = (int) (fatorTextura * (r - 128) + 128);
                    g = (int) (fatorTextura * (g - 128) + 128);
                    b = (int) (fatorTextura * (b - 128) + 128);
                }

                nova.setRGB(x, y, new Color(clamp(r), clamp(g), clamp(b)).getRGB());
            }
        }

        return nova;
    }

    private int clamp(int v) {
        return Math.max(0, Math.min(255, v));
    }

    private float clamp(float v, float min, float max) {
        return Math.max(min, Math.min(max, v));
    }
}


