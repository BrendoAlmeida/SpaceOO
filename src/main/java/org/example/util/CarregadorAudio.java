package org.example.util;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class CarregadorAudio
{
    public static Clip CarregarAudio(String arq)
    {
        URL audioUrl = CarregadorAudio.class.getClassLoader().getResource(arq);
        if(audioUrl == null)
        {
            System.out.println("áudio não encontrado!");
            return  null;
        }
        else
            try{
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(audioUrl));
                if(clip == null)
                {
                    System.out.println("áudio não encontrado!");
                    return  null;
                }
                else
                    return clip;
            }
            catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){
                throw new RuntimeException(e);
            }
    }
}
