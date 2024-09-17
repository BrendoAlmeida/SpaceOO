package org.example.controller;

import javax.swing.*;
import java.awt.*;

public class Tiro {
    private int vel;
    private int dano;
    private int[] pos;
    private int[] tamanho;
    private Rectangle hitbox = new Rectangle();
    private JPanel sprite;

    public Tiro(int[] pos, int[] tamanho) {

        this.pos = pos;
        this.tamanho = tamanho;
        this.hitbox.setBounds(pos[0],pos[1],tamanho[0],tamanho[1]);

        this.sprite= new JPanel();
        this.sprite.setBounds(pos[0], pos[1], tamanho[0], tamanho[1]);
        this.sprite.setBackground(Color.BLUE);


    }

    public int getVel() {
        return vel;
    }

    public int getDano() {
        return dano;
    }

    public int[] getPos() {
        return pos;
    }

    public int[] getTamanho() {
        return tamanho;
    }


    public Rectangle getHitbox() {
        return hitbox;
    }

    public JPanel getSprite() {
        return sprite;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

}
