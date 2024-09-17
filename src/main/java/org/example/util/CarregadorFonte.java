package org.example.util;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CarregadorFonte
{
    /*
    *
    * @arq = String do caminho da fonte no formato: fonts/(nome do arquivo)
    */

    public static Font CarregaFonte(String arq)
    {
        // Carrega o arquivo de fonte da pasta resources
        URL fontURL = CarregadorFonte.class.getClassLoader().getResource(arq);

        if (fontURL == null) {
            System.out.println("Fonte não encontrada!");
            return null;
        }
        else
            try {
                return Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream()).deriveFont(30f);
            }
            catch (FontFormatException | IOException e)
            {
                e.printStackTrace();
                return null;
            }
    }
}
