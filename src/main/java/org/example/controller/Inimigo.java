package org.example.controller;

import java.awt.*;
import java.util.Random;

public class Inimigo extends Elemento{
    private Tiro tiro;

    public Inimigo(int[] pos, Dimension tamanho, Tiro tiro) {
        super(pos, tamanho, "img/inimigo1.png");
        startInimigo(tiro);
    }

    public Inimigo(Dimension tamanho, Tiro tiro) {
        super(tamanho, "img/inimigo1.png");
        startInimigo(tiro);
    }

    public void startInimigo(Tiro tiro){
        this.velocidade = 2;
        this.delayAtirar = 0;
        this.delayTiro = 50;
        this.vida = 1;
        this.tiro = tiro;

        Random random = new Random();
        delayTiro = 50 + random.nextInt(50);
        delayAtirar = delayTiro;
    }

    public Inimigo clone() {
        return new Inimigo(this.tamanho, this.tiro);
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
