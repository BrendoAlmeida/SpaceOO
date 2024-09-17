package org.example.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ControllerJogo {
    private int[] dimencao;
    private int fatorDimecao;
    private List<Inimigo> inimigos = new java.util.ArrayList<>();
    private Personagem jogador;
    private JPanel mainPanel;
    private Timer updateTimer;
    private Timer renderTimer;
    private boolean moveLeft = false;
    private boolean moveRight = false;

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
        int[] pos = new int[]{(dimencao[0] / 2) - jogador.getTamanho(), (dimencao[1] - jogador.getTamanho() - 20)};
        jogador.setPos(pos);
        mainPanel.add(jogador);
        jogador.setFocusable(true);
        jogador.requestFocusInWindow();
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

        jogador.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveLeft = true;
                    moveRight = false;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight = true;
                    moveLeft = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveLeft = false;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight = false;
                }
            }
        });

        startGameLoop();
        startRenderLoop();
    }

    private void startGameLoop() {
        updateTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
            }
        });
        updateTimer.start();
    }

    private void startRenderLoop() {
        renderTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.repaint();
            }
        });
        renderTimer.start();
    }

    private void updateGame() {
        if (moveLeft) {
            jogador.mover(-1, dimencao[0]);
        } else if (moveRight) {
            jogador.mover(1, dimencao[0]);
        }
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