package org.example.view;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CarregaRecursos {
    public static Font CarregaFonte(String arq)
    {
        // Carrega o arquivo de fonte da pasta resources

        URL fontURL = CarregaRecursos.class.getClassLoader().getResource(arq);

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
