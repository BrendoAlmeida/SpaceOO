package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;

public class Personagem extends JPanel {
    private int vida = 3;
    private int[] pos;
    private Dimension tamanho;
    private Image sprite;
    private Tiro tiro;
    private int velocidade = 5;
    private int delayAtirar = 0;
    private int delayTiro = 50;

    public Personagem(int[] pos, Dimension tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho.width, tamanho.height).getImage();

        this.setBounds(pos[0], pos[1], tamanho.width, tamanho.height);

        this.tiro = tiro;
    }

    public Personagem(Dimension tamanho, Tiro tiro) {
        this.tamanho = tamanho;
        this.setSize(tamanho.width, tamanho.height);

        sprite = CarregadorImagem.CarregaIcone("img/player1.png", tamanho.width, tamanho.height).getImage();

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

    public Dimension getTamanho() {
        return tamanho;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setTamanho(Dimension tamanho) {
        this.tamanho = tamanho;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho.width, tamanho.height);
    }

    public void tomarDano(int dano) {
        this.vida -= dano; // TODO imunidade apos tomar dano?
    }

    public boolean mover(int direcao, int maxPos) {
        if (pos[0] + direcao * velocidade < 0 || pos[0] + direcao * velocidade > maxPos - tamanho.width) return false;

        pos[0] += direcao * velocidade;
        setPos(pos);

        return true;
    }

    public Tiro atirar(){
        if(delayAtirar > 0) return null;
        Tiro tiro = this.tiro.clone();
        int pos[] = new int[]{this.pos[0] + tamanho.width/2 - tiro.getTamanho().width/2, this.pos[1] - tiro.getTamanho().height};
        tiro.atirar(pos);
        delayAtirar = delayTiro;
        return tiro;
    }

    public void delayTiro(){
        if(delayAtirar > 0){
            delayAtirar -= 1;
        }
    }

    public int getDelayAtirar() {
        return delayAtirar;
    }
}
