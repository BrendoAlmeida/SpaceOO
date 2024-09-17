package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;

public class Personagem extends JPanel {
    private int vida = 3;
    private int[] pos;
    private int tamanho;
    private Image sprite;
    private Rectangle hitbox = new Rectangle();
    private Tiro tiro;
    private int velocidade = 5;

    public Personagem(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;
        hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho, tamanho).getImage();

        this.setBounds(pos[0], pos[1], tamanho, tamanho);

        this.tiro = tiro;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
    }

    public int getVida() {
        return vida;
    }

    public Image getSprite() {
        return sprite;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho, tamanho);
        hitbox.setBounds(pos[0], pos[1], tamanho, tamanho);
    }

    public void mover(int direcao, int maxPos) {
        if (pos[0] + direcao * velocidade < 0 || pos[0] + direcao * velocidade > maxPos - tamanho) return;

        pos[0] += direcao * velocidade;
        setPos(pos);
    }
}
