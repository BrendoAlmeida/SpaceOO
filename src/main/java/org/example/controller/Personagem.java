package org.example.controller;

import javax.swing.*;
import java.awt.*;

public class Personagem {
    private int[] pos;
    private int tamanho;
    private JPanel sprite;
    private Rectangle hitbox = new Rectangle();
    private Tiro tiro;

    public Personagem(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;
        this.hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);

        this.sprite = new JPanel();
        this.sprite.setBounds(pos[0], pos[1], tamanho, tamanho);
        this.sprite.setBackground(Color.BLACK);

        this.tiro = tiro;
    }

    public JPanel getSprite() {
        return sprite;
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
