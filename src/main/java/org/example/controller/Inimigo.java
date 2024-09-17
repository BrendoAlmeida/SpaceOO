package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;

public class Inimigo extends JPanel{
    private int vida;
    private int[] pos;
    private int tamanho;
    private Image sprite;
    private Rectangle hitbox = new Rectangle();
    private Tiro tiro;

    public Inimigo(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;
        hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho, tamanho).getImage();

        this.setBounds(pos[0], pos[1], tamanho, tamanho);
        this.setBackground(Color.BLACK);

        this.tiro = tiro;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getVida() { return vida; }

    public Image getSprite() {
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

    public void setVida(int vida) { this.vida = vida; }

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
