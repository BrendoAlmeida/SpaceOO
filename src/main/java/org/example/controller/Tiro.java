package org.example.controller;

import java.awt.*;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class Tiro extends Elemento{
    protected int dano;
    protected int[] direcao;
    protected boolean tiroInimigo = false;
    protected boolean movendo = false;
    public Tiro(int[] pos, Dimension tamanho, int velocidade, int dano, int[] direcao, boolean tiroInimigo){
        super(pos, tamanho);
        startTiro(direcao, tiroInimigo, velocidade, dano);
    }

    public Tiro(Dimension tamanho, int velocidade, int dano, int[] direcao, boolean tiroInimigo){
        super(tamanho);
        startTiro(direcao, tiroInimigo, velocidade, dano);
    }

    public void startTiro(int[] direcao, boolean tiroInimigo, int velocidade, int dano){
        this.setBackground(Color.BLUE);

        this.direcao = direcao;
        this.tiroInimigo = tiroInimigo;
        this.velocidade = velocidade;
        this.dano = dano;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
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
        if (!movendo) return;

        pos[1] += velocidade * direcao[0];
        setPos(pos);
    }

    public boolean colisao(Rectangle hitbox){
        return this.getBounds().intersects(hitbox);
    }

    public boolean isTiroInimigo() {
        return tiroInimigo;
    }
}
