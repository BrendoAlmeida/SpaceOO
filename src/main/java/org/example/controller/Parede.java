package org.example.controller;

import java.awt.*;

public class Parede extends Elemento {
    public Parede(int[] pos, Dimension tamanho) {
        super(pos, tamanho, "img/player1.png");
        startParede();
    }

    public Parede(Dimension tamanho) {
        super(tamanho, "img/player1.png");
        startParede();
    }

    public void startParede(){
        this.vida = 30;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
    }

    public Parede clone() {
        return new Parede(tamanho);
    }
}
