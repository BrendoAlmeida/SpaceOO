package org.example.util;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.*;

public class CarregadorAudio
{
    private void CarregarAudio(String arq)
    {
        InputStream audio = CarregadorAudio.class.getResourceAsStream("/"+arq);

    }


}
