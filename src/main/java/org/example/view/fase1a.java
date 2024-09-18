package org.example.view;

import org.example.controller.*;

import javax.swing.*;
import java.awt.*;

public class fase1a {
    private JPanel mainPanel;
    private ControllerJogo controllerJogo;
    private int[] dimencao = new int[]{750, 600};
    private int fatorDimencao = 50;

    public fase1a() {
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
                new Inimigo(new int[]{0, 0}, fatorDimencao, new Tiro(new int[]{0,0}, new int[]{2,10}, 7, 1, 1, true)),
                new Personagem(new int[]{0, 0}, fatorDimencao, new Tiro(new int[]{0,0}, new int[]{2,10}, 7, 1, -1, false)),
                new Parede(new int[]{0, 0}, fatorDimencao*2),
                3
        );
    }
}
