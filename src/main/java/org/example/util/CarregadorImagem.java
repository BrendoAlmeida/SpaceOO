package org.example.util;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
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
            URL templateURL = CarregadorImagem.class.getClassLoader().getResource("img/template.png");
            assert  templateURL != null;
            return new ImageIcon(templateURL).getImage();
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
            URL templateURL = CarregadorImagem.class.getClassLoader().getResource("img/template.png");
            assert  templateURL != null;
            return new ImageIcon(templateURL);
        }
        else
        {
            ImageIcon img = new ImageIcon(imgURL);
            return new ImageIcon(img.getImage().getScaledInstance(wd,hg,Image.SCALE_DEFAULT));
        }
    }
}
