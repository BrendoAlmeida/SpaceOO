package org.example.view;

import org.example.controller.ControllerJogo;
import org.example.controller.Inimigo;
import org.example.controller.Tiro;

import javax.swing.*;
import java.awt.*;

public class fase1 {
    private JPanel mainPanel;
    private Inimigo[] inimigos;
    private ControllerJogo controllerJogo;
    private int[] dimencao = new int[]{800, 600};
    private int fatorDimencao = 50;

    public fase1() {

        JFrame frame = new JFrame("Space Invaders");
        mainPanel.setLayout(null);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(dimencao[0], dimencao[1]);
        frame.setVisible(true);

        controllerJogo = new ControllerJogo(mainPanel, dimencao, fatorDimencao, new Inimigo(new int[]{0, 0}, fatorDimencao, new Tiro()));
    }
}
