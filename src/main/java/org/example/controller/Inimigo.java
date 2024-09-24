package org.example.controller;

import java.awt.*;
import java.util.Random;

public class Inimigo extends Elemento{
    private Tiro tiro;

    public Inimigo(int[] pos, Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro) {
        super(pos, tamanho, "img/inimigo1.png");
        startInimigo(tiro, vida, velocidade, delayTiro);
    }

    public Inimigo(Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro) {
        super(tamanho, "img/inimigo1.png");
        startInimigo(tiro, vida, velocidade, delayTiro);
    }

    public void startInimigo(Tiro tiro, int vida, int velocidade, int delayTiro){
        this.tiro = tiro;
        this.vida = vida;
        this.velocidade = velocidade;

        Random random = new Random();
        this.delayTiro = delayTiro + random.nextInt(50);
        this.delayAtirar = this.delayTiro;
    }

    public Inimigo clone() {
        return new Inimigo(this.tamanho, this.tiro, this.vida, this.velocidade, this.delayAtirar);
    }

    public Tiro atirar(){
        if(delayAtirar > 0) return null;
        int pos[] = new int[]{this.pos[0] + tamanho.width/2 - tiro.getTamanho().width/2, this.pos[1] + tiro.getTamanho().height + tamanho.height};
        Tiro tiro = this.tiro.clone();
        tiro.atirar(pos);
        delayAtirar = delayTiro;
        return tiro;
    }

    public boolean mover(int direcao, int maxPos) {
        if (pos[0] + direcao * velocidade < 0 || pos[0] + direcao * velocidade > maxPos - tamanho.width) return false;

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
