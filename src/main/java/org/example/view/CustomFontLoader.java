package org.example.view;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CustomFontLoader {

    public Font loadCustomFont() {
        // Carrega o arquivo de fonte da pasta resources

        URL fontURL = getClass().getClassLoader().getResource("fonts/space_invaders.ttf");

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
