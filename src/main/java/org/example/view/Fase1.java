package org.example.view;

import org.example.controller.*;
import org.example.model.modelInimigo;
import org.example.model.modelPersonagem;
import org.example.model.modelTiro;
import org.example.util.CarregadorAudio;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class Fase1 extends JPanel{
    private static ControllerJogo controllerJogo;
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

    public Fase1(int perso, int scr) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(dimencao);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setVisible(true);

        this.setLayout(null);
        this.setPreferredSize(dimencao);
        this.setBackground(Color.BLACK);
        this.add(mainPanel);

        Insets insets = FramePrincipal.pnPrincipal.getInsets();
        int frameWidth = dimencao.width + insets.left + insets.right;
        int frameHeight = dimencao.height + insets.top + insets.bottom;
        this.setSize(frameWidth,frameHeight);

        System.out.println(perso);
        mainPanel.setSize(frameWidth,frameHeight);
        Personagem personagem = modelPersonagem.getPersonagem(perso);
        Inimigo inimigo = modelInimigo.getInimigo(1);

        Parede parede = new Parede(new Dimension(fatorDimencao*2, fatorDimencao*2), 30);

        this.setVisible(true);
        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao, inimigo, personagem, parede, 3,1, -1,scr);
    }

    public Fase1(Personagem perso,int scr)
    {//chamada quando o personagem é editado
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(dimencao);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setVisible(true);

        this.setLayout(null);
        this.setPreferredSize(dimencao);
        this.setBackground(Color.BLACK);
        this.add(mainPanel);

        Insets insets = FramePrincipal.pnPrincipal.getInsets();
        int frameWidth = dimencao.width + insets.left + insets.right;
        int frameHeight = dimencao.height + insets.top + insets.bottom;
        this.setSize(frameWidth,frameHeight);

        System.out.println(perso);
        mainPanel.setSize(frameWidth,frameHeight);
        Inimigo inimigo = modelInimigo.getInimigo(1);

        Parede parede = new Parede(new Dimension(fatorDimencao*2, fatorDimencao*2), 30);

        this.setVisible(true);
        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao, inimigo, perso, parede, 3,1, -1,scr);
    }
}
