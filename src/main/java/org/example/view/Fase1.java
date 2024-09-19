package org.example.view;

import org.example.controller.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Fase1 extends JPanel{
    private ControllerJogo controllerJogo;
    private int[] dimencao = new int[]{750, 600};
    private int fatorDimencao = 50;

    public Fase1(Clip clip, Clip clipAnterior) {
        JFrame frame = new JFrame("Space Invaders");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(dimencao[0], dimencao[1]));
        mainPanel.setBackground(Color.BLACK);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();

        Insets insets = frame.getInsets();
        int frameWidth = dimencao[0] + insets.left + insets.right;
        int frameHeight = dimencao[1] + insets.top + insets.bottom;
        frame.setSize(frameWidth, frameHeight);

        frame.setVisible(true);
        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao,
                new Inimigo(fatorDimencao, new Tiro(new int[]{2,10}, 7, 1, 1, true)),
                new Personagem(fatorDimencao, new Tiro(new int[]{2,10}, 7, 1, -1, false)),
                new Parede(fatorDimencao*2),
                3
        );

        if (clipAnterior != null) {
            clipAnterior.stop();
        }
        clip.start();
    }
}
