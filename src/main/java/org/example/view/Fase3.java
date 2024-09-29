package org.example.view;

import org.example.controller.ControllerJogo;
import org.example.controller.Inimigo;
import org.example.controller.Parede;
import org.example.controller.Personagem;
import org.example.model.modelInimigo;
import org.example.model.modelPersonagem;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Fase3 extends JPanel{
    private ControllerJogo controllerJogo;
    private Dimension dimencao = new Dimension(750, 600);
    private int fatorDimencao = 50;

    public Fase3(Clip clip, Clip clipAnterior) {
        JFrame frame = new JFrame("Space Invaders");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(dimencao);
        mainPanel.setBackground(Color.BLACK);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();

        Insets insets = frame.getInsets();
        int frameWidth = dimencao.width + insets.left + insets.right;
        int frameHeight = dimencao.height + insets.top + insets.bottom;
        frame.setSize(frameWidth, frameHeight);

        Personagem personagem = modelPersonagem.getPersonagem(2);
        Inimigo inimigo = modelInimigo.getInimigo(3);

        Parede parede = new Parede(new Dimension(fatorDimencao*2, fatorDimencao*2), 30);

        frame.setVisible(true);
        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao, inimigo, personagem, parede, 1, 1);

        if (clipAnterior != null) {
            clipAnterior.stop();
        }
        clip.start();
    }
}
