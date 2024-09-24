package org.example.controller;

import java.awt.*;

public class Parede extends Elemento {
    public Parede(int[] pos, Dimension tamanho, int vida) {
        super(pos, tamanho, "img/player1.png");
        startParede(vida);
    }

    public Parede(Dimension tamanho, int vida) {
        super(tamanho, "img/player1.png");
        startParede(vida);
    }

    public void startParede(int vida){
        this.vida = vida;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
    }

    public Parede clone() {
        return new Parede(tamanho, vida);
    }
}
