package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControllerJogo {
    private int[] dimencao;
    private int fatorDimecao;
    private List<Inimigo> inimigos = new java.util.ArrayList<>();
    private Personagem jogador;
    private JPanel mainPanel;

    public void initInimigos(Inimigo inimigo) {
        int tamanhoX = this.getDimencao()[0];
        int fatorDim = this.getFatorDimecao();
        int qtdInimigos = (tamanhoX - fatorDim);

        for (int i = fatorDim; i < qtdInimigos; i += fatorDim) {
            Inimigo novoInimigo = inimigo.clone();
            novoInimigo.setPos(new int[]{i, 0});
            novoInimigo.setBounds(i, 0, novoInimigo.getTamanho(), novoInimigo.getTamanho());
            inimigos.add(novoInimigo);
            mainPanel.add(novoInimigo);
        }
        mainPanel.repaint();
    }

    public void initPersonagem(Personagem jogador) {
        int[] pos = new int[]{(dimencao[0] / 2) - jogador.getTamanho(), (dimencao[1] - jogador.getTamanho()*2)};
        jogador.setBounds(pos[0], pos[1], jogador.getTamanho(), jogador.getTamanho());
        mainPanel.add(jogador);
        mainPanel.repaint();
    }

    public ControllerJogo(JPanel mainPanel, int[] pos, int fatorDimecao, Inimigo inimigo, Personagem jogador) {
        dimencao = pos;
        this.fatorDimecao = fatorDimecao;
        this.mainPanel = mainPanel;

        initInimigos(inimigo);
        initPersonagem(jogador);
        this.jogador = jogador;
        mainPanel.repaint();
    }

    public int getFatorDimecao() {
        return fatorDimecao;
    }

    public void setFatorDimecao(int fatorDimecao) {
        this.fatorDimecao = fatorDimecao;
    }

    public int[] getDimencao() {
        return dimencao;
    }

    public void setDimencao(int[] dimencao) {
        this.dimencao = dimencao;
    }

    public java.util.List<Inimigo> getInimigos() {
        return inimigos;
    }
}
