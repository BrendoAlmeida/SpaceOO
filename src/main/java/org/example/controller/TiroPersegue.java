package org.example.controller;

import java.awt.*;

public class TiroPersegue extends Tiro{
    public TiroPersegue(int[] pos, Dimension tamanho, int velocidade, int dano, int direcao, boolean tiroInimigo) {
        super(pos, tamanho, velocidade, dano, direcao, tiroInimigo);
    }

    public TiroPersegue(Dimension tamanho, int velocidade, int dano, int direcao, boolean tiroInimigo) {
        super(tamanho, velocidade, dano, direcao, tiroInimigo);
    }

    public void mover(int[] pos){
        if (!movendo) return;

        int[] posicao = getPos();
        int[] direcao = new int[]{pos[0] - posicao[0], pos[1] - posicao[1]};
        double norma = Math.sqrt(direcao[0]*direcao[0] + direcao[1]*direcao[1]);
        direcao[0] = (int) (direcao[0] * getVel()/norma);
        direcao[1] = (int) (direcao[1] * getVel()/norma);
        setPos(new int[]{posicao[0] + direcao[0], posicao[1] + direcao[1]});
    }

    public TiroPersegue clone(){
        return new TiroPersegue(tamanho, velocidade, dano, direcao, tiroInimigo);
    }
}
