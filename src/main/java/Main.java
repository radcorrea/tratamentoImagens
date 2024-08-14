import br.com.tratamento.Armazenando.Armazenando;
import br.com.tratamento.imagem.Redimensionar;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        BufferedImage imagemEscala660x480_1 = new Redimensionar().preparandoImagem("src/main/resources/acidente-campinas.jpg");
        new Armazenando().armazenandoImagem("1234", imagemEscala660x480_1);

    }
}