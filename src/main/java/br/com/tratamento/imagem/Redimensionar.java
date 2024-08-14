package br.com.tratamento.imagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Redimensionar {

    private final int LARGURA_ESPECIFICACAO = 600;
    private final int ALTURA_ESPECIFICACAO = 480;
    private final int LARGURA_RETANGULO_PRETO = 60;
    private final int LARGURA_ESPECIFICACAO_COM_RETANGULO_PRETO = LARGURA_ESPECIFICACAO + LARGURA_RETANGULO_PRETO;

    public BufferedImage lendoImagem(String pathArquivo) {

        BufferedImage imagemOriginal = null;

        try {
            File arquivo = new File(pathArquivo);
            imagemOriginal = ImageIO.read(arquivo);

            System.out.println("largura original: " + imagemOriginal.getWidth());
            System.out.println("altura original: " + imagemOriginal.getHeight());

        } catch (IOException e) {
            System.out.println("Ocorreu um problema ao ler a imagem: " + e);
        }

        return imagemOriginal;
    }

    public BufferedImage redimensionandoImagem(BufferedImage imagemOriginal) {

        Image imagemEscala600x480 = imagemOriginal.getScaledInstance(LARGURA_ESPECIFICACAO, ALTURA_ESPECIFICACAO, Image.SCALE_SMOOTH);
        BufferedImage imagemRedimencionada = new BufferedImage(LARGURA_ESPECIFICACAO, ALTURA_ESPECIFICACAO, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagemRedimencionada.createGraphics();
        g2d.drawImage(imagemEscala600x480, 0, 0, null);
        g2d.dispose();

        System.out.println("largura após redimensionamento: " + imagemRedimencionada.getWidth());
        System.out.println("altura após redimensionamento: " + imagemRedimencionada.getHeight());

        return imagemRedimencionada;

    }

    public BufferedImage incluindoRetanguloPreto(BufferedImage imagemRedimencionada) {
        BufferedImage imagemEscala660x480 = new BufferedImage(LARGURA_ESPECIFICACAO_COM_RETANGULO_PRETO, ALTURA_ESPECIFICACAO, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dPreto = imagemEscala660x480.createGraphics();
        g2dPreto.drawImage(imagemRedimencionada, 0, 0, null);
        g2dPreto.setColor(Color.BLACK);
        g2dPreto.fillRect(LARGURA_ESPECIFICACAO, 0, LARGURA_RETANGULO_PRETO, ALTURA_ESPECIFICACAO);
        g2dPreto.dispose();

        System.out.println("largura após incluir retangulo preto: " + imagemEscala660x480.getWidth());
        System.out.println("altura após incluir retangulo preto: " + imagemEscala660x480.getHeight());

        return imagemEscala660x480;
    }

    public BufferedImage preparandoImagem(String pathArquivo) {
        BufferedImage imagemOriginal = lendoImagem(pathArquivo);
        BufferedImage imagemRedimensionada600x480 = redimensionandoImagem(imagemOriginal);
        BufferedImage imagemRedimensionada660x480 = incluindoRetanguloPreto(imagemRedimensionada600x480);
        return imagemRedimensionada660x480;
    }

}
