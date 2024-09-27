package org.example.view;

import org.example.controller.*;
import org.example.model.modelInimigo;
import org.example.model.modelPersonagem;
import org.example.model.modelTiro;
import org.example.util.CarregadorAudio;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Fase1 extends JPanel{
    private ControllerJogo controllerJogo;
    //private Dimension dimencao = new Dimension(750, 600);
    private Dimension dimencao = new Dimension(1000, 800);

    private int fatorDimencao = 50;
    private static Clip faseMusica = CarregadorAudio.CarregarAudio("audio/TemaFases.wav");

    public static void MudarMusica()
    {
        if (FramePrincipal.clip != null)
            FramePrincipal.PararMusicaMenu();

        faseMusica.start();
        faseMusica.loop((int)faseMusica.getMicrosecondLength());
    }

    public Fase1() {
        //JFrame frame = new JFrame("Space Invaders");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(dimencao);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setVisible(true);
        mainPanel.requestFocus();

        this.setLayout(null);
        this.setPreferredSize(dimencao);
        this.setBackground(Color.BLACK);
        this.add(mainPanel);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(mainPanel);
        //frame.pack();

        Insets insets = FramePrincipal.pnPrincipal.getInsets();
        int frameWidth = dimencao.width + insets.left + insets.right;
        int frameHeight = dimencao.height + insets.top + insets.bottom;
        //frame.setSize(frameWidth, frameHeight);
        this.setSize(frameWidth,frameHeight);
        mainPanel.setSize(frameWidth,frameHeight);
        Personagem personagem = modelPersonagem.getPersonagem(1);
        Inimigo inimigo = modelInimigo.getInimigo(1);

        Parede parede = new Parede(new Dimension(fatorDimencao*2, fatorDimencao*2), 30);

        //frame.setVisible(true);
        this.setVisible(true);
        //controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao, inimigo, personagem, parede, 3);
        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao, inimigo, personagem, parede, 3);
    }
}
