package org.example.util;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CarregadorImagem
{
    /*
    *
    * @arq no formato img/(nome do arquivo com a extensão dele)
     */
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
    public static ImageIcon CarregaIcone(String arq,int wd, int hg)
    {
        URL imgURL = CarregadorImagem.class.getClassLoader().getResource(arq);
        if(imgURL == null)
        {
            System.out.println("Imagem não encontrada");
            return null;
        }
        else
        {
            ImageIcon img = new ImageIcon(imgURL);
            return new ImageIcon(img.getImage().getScaledInstance(wd,hg,Image.SCALE_DEFAULT));
        }
    }
}
