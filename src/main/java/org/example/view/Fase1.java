package org.example.view;

import org.example.controller.ControllerJogo;
import org.example.controller.Inimigo;
import org.example.controller.Personagem;
import org.example.controller.Tiro;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class Fase1 extends JPanel{
    private ControllerJogo controllerJogo;
    private int[] dimencao = new int[]{800, 600};
    private int fatorDimencao = 50;

    public Fase1(Clip clip, Clip clipAnterior) {
//        controllerJogo = new ControllerJogo(this, dimencao, fatorDimencao, new Inimigo(new int[]{0, 0}, fatorDimencao, new Tiro(new int[]{0,0}, new int[]{2,10})), new Personagem(new int[]{0, 0}, fatorDimencao, new Tiro(new int[]{0,0}, new int[]{2,10})));
        if (clipAnterior != null) {
            clipAnterior.stop();
        }
        clip.start();
    }
}
