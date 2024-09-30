package org.example.controller;

import java.awt.*;
import java.util.Random;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class Inimigo extends Elemento{
    private Tiro tiro;

    public Inimigo(int[] pos, Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro, String sprite) {
        super(pos, tamanho, sprite);
        startInimigo(tiro, vida, velocidade, delayTiro);
    }

    public Inimigo(Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro, String sprite) {
        super(tamanho, sprite);
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
        return new Inimigo(this.tamanho, this.tiro, this.vida, this.velocidade, this.delayAtirar, this.spritePath);
    }

    public Tiro atirar(){
        if(delayAtirar > 0) return null;
        int pos[] = new int[]{this.pos[0] + tamanho.width/2 - tiro.getTamanho().width/2, this.pos[1] + tiro.getTamanho().height + tamanho.height};
        Tiro tiro = this.tiro.clone();
        tiro.atirar(pos);
        delayAtirar = delayTiro;
        return tiro;
    }

    public TiroPersegue atirarPersegue(int[] direcao){
        if(delayAtirar > 0) return null;
        int pos[] = new int[]{this.pos[0] + tamanho.width/2 - tiro.getTamanho().width/2, this.pos[1] + tiro.getTamanho().height + tamanho.height};
        TiroPersegue tiro = new TiroPersegue(pos, this.tiro.getTamanho(), this.tiro.getVel(), this.tiro.getDano(), direcao, true);
        tiro.atirar(pos, direcao);
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

    public Tiro getTiro() {
        return tiro;
    }
}
