package org.example.controller;

import java.awt.*;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class TiroPersegue extends Tiro{
    public TiroPersegue(int[] pos, Dimension tamanho, int velocidade, int dano, int[] direcao, boolean tiroInimigo) {
        super(pos, tamanho, velocidade, dano, direcao, tiroInimigo);
    }

    public TiroPersegue(Dimension tamanho, int velocidade, int dano, int[] direcao, boolean tiroInimigo) {
        super(tamanho, velocidade, dano, direcao, tiroInimigo);
    }

    public void atirar(int[] pos, int[] direcao){
        super.atirar(pos);
        this.direcao = new int[]{direcao[0], direcao[1]};
        }

    public void mover() {
        if (!movendo) return;

        int[] posicao = getPos();
        int[] direcao = new int[]{this.direcao[0] - posicao[0], this.direcao[1] - posicao[1]};
        double norma = Math.sqrt(direcao[0] * direcao[0] + direcao[1] * direcao[1]);

        if (posicao[1] >= this.direcao[1]) {
            this.pos[1] += velocidade;
            setPos(pos);
            return;
        }

        direcao[0] = (int) (direcao[0] * getVel() / norma);
        direcao[1] = (int) (direcao[1] * getVel() / norma);
        setPos(new int[]{posicao[0] + direcao[0], posicao[1] + direcao[1]});

    }

    public TiroPersegue clone(){
        return new TiroPersegue(tamanho, velocidade, dano, direcao, tiroInimigo);
    }
}
