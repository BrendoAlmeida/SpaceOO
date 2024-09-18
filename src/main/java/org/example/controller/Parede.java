package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;

public class Parede extends JPanel {
    private int vida = 30;
    private int[] pos;
    private int tamanho;
    private Image sprite;
    private Tiro tiro;
    private int velocidade;
    private int delayAtirar;
    private int delayTiro;

    public Parede(int[] pos, int tamanho) {
        this.pos = pos;
        this.tamanho = tamanho;

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho, tamanho).getImage();

        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho, tamanho);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
    }

    public int getTamanho() {
        return tamanho;
    }

    public Parede clone() {
        return new Parede(pos, tamanho);
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho, tamanho);
    }

    public void tomarDano(int dano) {
        vida -= dano;
    }

    public int getVida() {
        return vida;
    }
}
