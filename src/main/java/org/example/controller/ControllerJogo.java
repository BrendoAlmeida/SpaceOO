package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControllerJogo {
    private int[] dimencao;
    private int fatorDimecao;
    private List<Inimigo> inimigos = new java.util.ArrayList<>();
    private JPanel mainPanel;

    public void initInimigos(Inimigo inimigo) {
        int tamanhoX = this.getDimencao()[0];
        int fatorDim = this.getFatorDimecao();
        int qtdInimigos = (tamanhoX - fatorDim);

        for (int i = fatorDim; i < qtdInimigos; i += fatorDim) {
            Inimigo novoInimigo = inimigo.clone();
            novoInimigo.setPos(new int[]{i, 0});
            novoInimigo.getSprite().setBounds(i, 0, novoInimigo.getTamanho(), novoInimigo.getTamanho());
            novoInimigo.getSprite().setBackground(Color.RED);
            inimigos.add(novoInimigo);
            mainPanel.add(novoInimigo.getSprite());
        }
    }

    public ControllerJogo(JPanel mainPanel, int[] pos, int fatorDimecao, Inimigo inimigo) {
        dimencao = pos;
        this.fatorDimecao = fatorDimecao;
        this.mainPanel = mainPanel;

        initInimigos(inimigo);
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
