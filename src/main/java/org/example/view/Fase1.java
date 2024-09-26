package org.example.view;

import org.example.controller.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

import static org.example.view.FramePrincipal.pnPrincipal;

public class Fase1 extends JPanel{
    //private ControllerJogo controllerJogo;
    private static ControllerJogo controllerJogo;
    //private Dimension dimencao = new Dimension(750, 600);
    private static Dimension dimencao = new Dimension(750, 600);
    //private int fatorDimencao = 50;
    private static int fatorDimencao = 50;
    private static JPanel mainPn = new JPanel();

    public Fase1(Clip clip, Clip clipAnterior) {
        //JFrame frame = new JFrame("Space Invaders");
        //JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(null);
        //mainPanel.setPreferredSize(dimencao);
        //mainPanel.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setPreferredSize(dimencao);
        this.setBackground(Color.BLACK);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(mainPanel);
        this.add(mainPn);
        //frame.pack();

        Insets insets = pnPrincipal.getInsets();
        int frameWidth = dimencao.width + insets.left + insets.right;
        int frameHeight = dimencao.height + insets.top + insets.bottom;
        this.setSize(frameWidth,frameHeight);
        //frame.setSize(frameWidth, frameHeight);

        //Inimigo inimigo = new Inimigo(new Dimension(fatorDimencao, fatorDimencao) , new Tiro(new Dimension(2,10), 10, 1, 1, true), 1, 2, 50);
        //Inimigo inimigo = new Inimigo(new Dimension(fatorDimencao, fatorDimencao) , new TiroPersegue(new Dimension(2,10), 10, 1, 1, true), 1, 2, 50);

        //Personagem personagem = new Personagem(new Dimension(fatorDimencao, fatorDimencao), new Tiro(new Dimension(2,10), 10, 1, -1, false), 3, 5, 50);
        //Parede parede = new Parede(new Dimension(fatorDimencao*2, fatorDimencao*2), 30);

        this.setVisible(true);
        //frame.setVisible(true);
        //controllerJogo = new ControllerJogo(this, dimencao, fatorDimencao, inimigo, personagem, parede, 3);

        if (clipAnterior != null) {
            clipAnterior.stop();
        }
        clip.start();
    }

    public static void initFase1()
    {
        //Inimigo inimigo = new Inimigo(new Dimension(fatorDimencao, fatorDimencao) , new Tiro(new Dimension(2,10), 10, 1, 1, true), 1, 2, 50);
        Inimigo inimigo = new Inimigo(new Dimension(fatorDimencao, fatorDimencao) , new TiroPersegue(new Dimension(2,10), 10, 1, 1, true), 1, 2, 50);

        Personagem personagem = new Personagem(new Dimension(fatorDimencao, fatorDimencao), new Tiro(new Dimension(2,10), 10, 1, -1, false), 3, 5, 50);
        Parede parede = new Parede(new Dimension(fatorDimencao*2, fatorDimencao*2), 30);

        //frame.setVisible(true);
        controllerJogo = new ControllerJogo(mainPn, dimencao, fatorDimencao, inimigo, personagem, parede, 3);
    }
}
