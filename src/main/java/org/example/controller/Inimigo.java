package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Inimigo extends JPanel{
    private int vida = 1;
    private int[] pos;
    private int tamanho;
    private Image sprite;
    private Tiro tiro;
    private int delayAtirar = 0;
    private int delayTiro;
    private int velocidade = 5;

    public Inimigo(int[] pos, int tamanho, Tiro tiro) {
        this.pos = pos;
        this.tamanho = tamanho;

        sprite = CarregadorImagem.CarregaIcone("img/inimigo1.png", tamanho, tamanho).getImage();

        this.setBounds(pos[0], pos[1], tamanho, tamanho);

        this.tiro = tiro;

        Random random = new Random();
        delayTiro = 50 + random.nextInt(50);
        delayAtirar = delayTiro;
    }

    public Inimigo(int tamanho, Tiro tiro) {
        this.tamanho = tamanho;
        this.setSize(tamanho,tamanho);

        sprite = CarregadorImagem.CarregaIcone("img/inimigo1.png", tamanho, tamanho).getImage();

        this.tiro = tiro;

        Random random = new Random();
        delayTiro = 50 + random.nextInt(50);
        delayAtirar = delayTiro;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
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

    public void tomarDano(int dano) {
        this.vida -= dano;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho, tamanho);
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

    public Tiro atirar(){
        if(delayAtirar > 0) return null;
        int pos[] = new int[]{this.pos[0] + tamanho/2 - tiro.getTamanho()[0]/2, this.pos[1] + tiro.getTamanho()[1] + tamanho};
        Tiro tiro = this.tiro.clone();
        tiro.atirar(pos);
        delayAtirar = delayTiro;
        return tiro;
    }

    public boolean mover(int direcao, int maxPos) {
        if (pos[0] + direcao * velocidade < 0 || pos[0] + direcao * velocidade > maxPos - tamanho) return false;

        pos[0] += direcao * velocidade;
        setPos(pos);

        return true;
    }

    public void delayTiro(){
        if(delayAtirar > 0){
            delayAtirar -= 1;
        }
    }
}
