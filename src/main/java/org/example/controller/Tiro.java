package org.example.controller;

import java.awt.*;

public class Tiro extends Elemento{
    private int dano;
    private int direcao;
    private boolean tiroInimigo = false;
    private boolean movendo = false;

    public Tiro(int[] pos, Dimension tamanho, int velocidade, int dano, int direcao, boolean tiroInimigo){
        super(pos, tamanho);
        startTiro(direcao, tiroInimigo);
    }

    public Tiro(Dimension tamanho, int velocidade, int dano, int direcao, boolean tiroInimigo){
        super(tamanho);
        startTiro(direcao, tiroInimigo);
    }

    public void startTiro(int direcao, boolean tiroInimigo){
        this.setBackground(Color.BLUE);

        this.velocidade = 10;
        this.dano = 1;
        this.direcao = direcao;
        this.tiroInimigo = tiroInimigo;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, tamanho.width, tamanho.height);
    }

    public int getVel() {
        return velocidade;
    }

    public int getDano() {
        return dano;
    }

    public int[] getPos() {
        return pos;
    }

    public Dimension getTamanho() {
        return tamanho;
    }

    public void setPos(int[] pos) {
        this.pos = new int[]{pos[0], pos[1]};
        this.setBounds(this.pos[0], this.pos[1], tamanho.width, tamanho.height);
    }

    public Tiro clone(){
        return new Tiro(tamanho, velocidade, dano, direcao, tiroInimigo);
    }

    public void atirar(int[] pos){
        setPos(pos);
        movendo = true;
    }

    public void mover() {
        if (movendo == false) return;

        pos[1] += velocidade * direcao;
        setPos(pos);
    }

    public boolean colisao(Rectangle hitbox){
        return this.getBounds().intersects(hitbox);
    }

    public boolean isTiroInimigo() {
        return tiroInimigo;
    }
}
