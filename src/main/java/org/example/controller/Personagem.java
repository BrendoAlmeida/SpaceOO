package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;

public class Personagem {
    private int[] pos;
    private int tamanho;
    private JPanel panel;
    private Image sprite;
    private Rectangle hitbox = new Rectangle();
    private Tiro tiro;

    public Personagem(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;
        this.hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);

        this.sprite = new ImageIcon(CarregadorImagem.CarregaImagem("img/player1.png")).getImage().getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);

        this.panel = new JPanel();
        this.panel.setBounds(pos[0], pos[1], tamanho, tamanho);
        this.panel.setBackground(Color.BLACK);

        this.tiro = tiro;
    }

    public JPanel getPanel(){
        return panel;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
