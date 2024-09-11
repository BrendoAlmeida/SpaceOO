package org.example.util;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CarregadorImagem
{
    public static Image CarregaImagem(String arq)
    {
        URL imgURL = CarregadorImagem.class.getClassLoader().getResource(arq);
        if(imgURL == null)
        {
            System.out.println("Imagem não encontrada");
            return null;
        }
        else
            return new ImageIcon(imgURL).getImage();
    }
}
