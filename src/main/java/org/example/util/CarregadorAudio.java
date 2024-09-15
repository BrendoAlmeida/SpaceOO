package org.example.util;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import javax.sound.*;

public class CarregadorAudio
{
    public static AudioInputStream CarregarAudio(String arq)
    {
        URL audioUrl = CarregadorAudio.class.getClassLoader().getResource(arq);
        if(audioUrl == null)
        {
            System.out.println("áudio não encontrado!");
            return  null;
        }
        else
            try{
                return AudioSystem.getAudioInputStream(audioUrl);
            }
            catch(IOException | UnsupportedAudioFileException e){
                e.printStackTrace();
                return null;
            }
    }
}
