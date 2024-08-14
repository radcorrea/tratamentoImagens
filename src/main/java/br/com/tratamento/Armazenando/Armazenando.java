package br.com.tratamento.Armazenando;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Armazenando {

    private static int numeroSequencial = 1;

    public static  int gerandoNumeroSequencial() {
        return numeroSequencial++;
    }

    public String gerandoPath(String codigoEquipamento) {

        Date dataHoraAtual = new Date();
        String ano = new SimpleDateFormat("yyyy").format(dataHoraAtual);
        String mes = new SimpleDateFormat("MM").format(dataHoraAtual);
        String dia = new SimpleDateFormat("dd").format(dataHoraAtual);
        String horaMinutoSegundo = new SimpleDateFormat("HHmmss").format(dataHoraAtual);

        String nomeArquivo = ano+mes+dia+horaMinutoSegundo + "-" + codigoEquipamento + "-" + gerandoNumeroSequencial() + ".png";
        System.out.println("Nome do arquivo gerado" + nomeArquivo);

        File path = new File("imagens" + File.separator + ano + File.separator + mes + File.separator + dia);

        if (!path.exists() && !path.mkdirs()) {
            throw new RuntimeException("Ocorreu um erro ao criar o diretório");
        }

        String pathArmazenamento = "imagens" + File.separator + ano + File.separator + mes + File.separator + dia + File.separator + nomeArquivo;
        System.out.println("Path Armazenamento: " + pathArmazenamento);
        return pathArmazenamento;
    }

    public void salvando(BufferedImage imagemEscala660x480, String pathArmazenamento) {

        try {
            File saida = new File(pathArmazenamento);
            ImageIO.write(imagemEscala660x480, "png", saida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void armazenandoImagem(String codigoEquipamento, BufferedImage imagemEscala660x480) {
        String pathArmazenamento = gerandoPath(codigoEquipamento);
        salvando(imagemEscala660x480, pathArmazenamento);
    }

}
