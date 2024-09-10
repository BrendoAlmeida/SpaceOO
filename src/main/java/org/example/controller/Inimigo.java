package org.example.controller;

import javax.swing.*;
import java.awt.*;

public class Inimigo {
    private int tamanho;
    private int[] pos;
    private Tiro tiro;
    private Rectangle hitbox = new Rectangle();
    private JPanel sprite;

    public Inimigo(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;
        this.hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);

        this.sprite = new JPanel();
        this.sprite.setBounds(pos[0], pos[1], tamanho, tamanho);
        this.sprite.setBackground(Color.RED);

        this.tiro = tiro;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public JPanel getSprite() {
        return sprite;
    }

    public Tiro getTiro() {
        return tiro;
    }

    public void setTiro(Tiro tiro) {
        this.tiro = tiro;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Inimigo clone() {
        return new Inimigo(this.pos, this.tamanho, this.tiro);
    }
}
