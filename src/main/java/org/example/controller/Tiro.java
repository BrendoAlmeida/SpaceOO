package org.example.controller;

import javax.swing.*;
import java.awt.*;

public class Tiro extends JPanel{
    private int vel;
    private int dano;
    private int direcao;
    private int[] pos;
    private int[] tamanho;
    private boolean movendo = false;
    private boolean tiroInimigo = false;

    public Tiro(int[] pos, int[] tamanho, int vel, int dano, int direcao, boolean tiroInimigo){
        this.pos = new int[]{pos[0], pos[1]};
        this.tamanho = tamanho;
        this.setBounds(this.pos[0],this.pos[1],tamanho[0],tamanho[1]);

        this.setBackground(Color.BLUE);

        this.vel = vel;
        this.dano = dano;
        this.direcao = direcao;
        this.tiroInimigo = tiroInimigo;
    }

    public Tiro(int[] tamanho, int vel, int dano, int direcao, boolean tiroInimigo){
        this.tamanho = tamanho;
        this.setSize(tamanho[0],tamanho[1]);

        this.setBackground(Color.BLUE);

        this.vel = vel;
        this.dano = dano;
        this.direcao = direcao;
        this.tiroInimigo = tiroInimigo;
    }

    public int getVel() {
        return vel;
    }

    public int getDano() {
        return dano;
    }

    public int[] getPos() {
        return pos;
    }

    public int[] getTamanho() {
        return tamanho;
    }

    public void setPos(int[] pos) {
        this.pos = new int[]{pos[0], pos[1]};
        this.setBounds(this.pos[0], this.pos[1], tamanho[0], tamanho[1]);
    }

    public Tiro clone(){
        return new Tiro(pos, tamanho, vel, dano, direcao, tiroInimigo);
    }

    public void atirar(int[] pos){
        setPos(pos);
        movendo = true;
    }

    public void mover() {
        if (movendo == false) return;

        pos[1] += vel * direcao;
        setPos(pos);
    }

    public boolean colisao(Rectangle hitbox){
        return this.getBounds().intersects(hitbox);
    }

    public boolean isTiroInimigo() {
        return tiroInimigo;
    }
}
