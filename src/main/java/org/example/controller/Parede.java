package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;

public class Parede extends JPanel {
    private int vida = 30;
    private int[] pos;
    private Dimension tamanho;
    private Image sprite;
    private Tiro tiro;
    private int velocidade;
    private int delayAtirar;
    private int delayTiro;

    public Parede(int[] pos, Dimension tamanho) {
        this.pos = pos;
        this.tamanho = tamanho;

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho.width, tamanho.height).getImage();

        this.setBounds(pos[0], pos[1], tamanho.width, tamanho.height);
    }

    public Parede(Dimension tamanho) {
        this.tamanho = tamanho;

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho.width, tamanho.height).getImage();

        this.setSize(tamanho.width, tamanho.height);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
    }

    public Dimension getTamanho() {
        return tamanho;
    }

    public Parede clone() {
        return new Parede(tamanho);
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho.width, tamanho.height);
    }

    public void tomarDano(int dano) {
        vida -= dano;
    }

    public int getVida() {
        return vida;
    }
}
