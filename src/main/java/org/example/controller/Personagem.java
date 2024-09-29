package org.example.controller;

import java.awt.*;

public class Personagem extends Elemento {
    private Tiro tiro;

    public Personagem(int[] pos, Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro) {
        super(pos, tamanho, "img/player1.png");

        if(this.getId() == 2)
            this.setSprite("img/player2.png");

        startPersonagem(tiro, vida, velocidade, delayTiro);
    }

    public Personagem(Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro) {
        super(tamanho, "img/player1.png");

        if(this.getId() == 2)
            this.setSprite("img/player2.png");

        startPersonagem(tiro, vida, velocidade, delayTiro);
    }

    public void startPersonagem(Tiro tiro, int vida, int velocidade, int delayTiro) {
        this.tiro = tiro;
        this.vida = vida;
        this.velocidade = velocidade;
        this.delayTiro = delayTiro;
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

    public Tiro getTiro() {
        return tiro;
    }
}
