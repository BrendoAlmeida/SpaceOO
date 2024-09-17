package org.example.view;

import org.example.controller.ControllerJogo;
import org.example.controller.Inimigo;
import org.example.controller.Personagem;
import org.example.controller.Tiro;

import javax.swing.*;
import java.awt.*;

public class fase1a {
    private JPanel mainPanel;
    private ControllerJogo controllerJogo;
    private int[] dimencao = new int[]{800, 600};
    private int fatorDimencao = 50;

    public fase1a() {
        JFrame frame = new JFrame("Space Invaders");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        int contentWidth = 800;
        int contentHeight = 600;
        mainPanel.setPreferredSize(new Dimension(contentWidth, contentHeight));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();

        Insets insets = frame.getInsets();
        int frameWidth = contentWidth + insets.left + insets.right;
        int frameHeight = contentHeight + insets.top + insets.bottom;
        frame.setSize(frameWidth, frameHeight);

        frame.setVisible(true);
        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao,
                new Inimigo(new int[]{0, 0}, fatorDimencao, new Tiro(new int[]{0,0}, new int[]{2,10})),
                new Personagem(new int[]{0, 0}, fatorDimencao, new Tiro(new int[]{0,0}, new int[]{2,10}))
        );
    }
}
