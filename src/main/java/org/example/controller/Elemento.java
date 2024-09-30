package org.example.controller;

import org.example.util.CarregadorImagem;

import javax.swing.*;
import java.awt.*;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public abstract class Elemento extends JPanel {
    protected int id;
    protected int vida;
    protected int[] pos;
    protected Dimension tamanho;
    protected Image sprite;
    protected int velocidade;
    protected int delayAtirar;
    protected int delayTiro;
    protected String spritePath;

    public Elemento(int[] pos, Dimension tamanho, String spritePath) {
        setTamanho(tamanho);
        setPos(pos);
        setSprite(spritePath);
    }

    public Elemento(Dimension tamanho, String spritePath) {
        setTamanho(tamanho);
        setSprite(spritePath);
    }

    public Elemento(int[] pos, Dimension tamanho) {
        setTamanho(tamanho);
        setPos(pos);
    }

    public Elemento(Dimension tamanho) {
        setTamanho(tamanho);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sprite, 0, 0, null);
    }

    public int getVida() { return vida; }

    public void tomarDano(int dano) {
        this.vida -= dano; //TODO imunidade apos tomar dano
    }

    public Image getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSprite(String spritePath) {
        sprite = CarregadorImagem.CarregaIcone(spritePath, tamanho.width, tamanho.height).getImage();
        this.spritePath = spritePath;
    }

    public int[] getPos() {
        return pos;
    }

    public void setVida(int vida) { this.vida = vida; }

    public void setPos(int[] pos) {
        this.pos = pos;
        this.setBounds(pos[0], pos[1], tamanho.width, tamanho.height);
    }

    public Dimension getTamanho() {
        return tamanho;
    }

    public void setTamanho(Dimension tamanho) {
        this.tamanho = tamanho;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setDelayAtirar(int delayAtirar) {
        this.delayAtirar = delayAtirar;
    }

    public int getDelayAtirar() {
        return delayAtirar;
    }

    public int getDelayTiro(){
        return delayTiro;
    }
}

