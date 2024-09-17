package org.example.controller;

import org.example.util.CarregadorImagem

import javax.swing.*;
import java.awt.*;

public abstract class Elemento extends JPanel {
    protected int vida;
    protected int[] pos;
    protected int tamanho;
    protected Image sprite;
    protected Rectangle hitbox = new Rectangle();
    protected Tiro tiro;

    public Elemento(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;
        hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho, tamanho).getImage();

        this.setBounds(pos[0], pos[1], tamanho, tamanho);
        this.setBackground(Color.BLACK);

        this.tiro = tiro;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, pos[0], pos[1], null);
    }

    public Rectangle getHitbox() {return hitbox;}

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

}

