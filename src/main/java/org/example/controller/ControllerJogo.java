package org.example.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ControllerJogo {
    private int[] dimencao;
    private int fatorDimecao;

    private List<Inimigo> inimigos = new java.util.ArrayList<>();
    private List<Tiro> tiros = new java.util.ArrayList<>();

    private Personagem jogador;
    private JPanel mainPanel;
    private Timer updateTimer;
    private Timer renderTimer;

    // botões
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean atirar = false;

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
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    atirar = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveLeft = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    atirar = false;
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
        }
        if (moveRight) {
            jogador.mover(1, dimencao[0]);
        }
        if(atirar) {
            jogadorAtira();
        }
        inimigoAtira();
        moveTiro();
        jogador.delayTiro();
        for (Inimigo inimigo : inimigos) {
            inimigo.delayTiro();
        }
    }

    public void moveTiro(){
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            tiro.mover();
            if (tiro.getPos()[1] < 0) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                continue;
            }
            if (colideInimigo(tiro)) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                i--;
            }
            if (colideJogador(tiro)) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                jogador.tomarDano(tiro.getDano());
            }
        }
    }

    public void inimigoAtira(){
        for (Inimigo inimigo : inimigos) {
            Tiro tiro = inimigo.atirar();
            if (tiro != null) {
                tiros.add(tiro);
                mainPanel.add(tiro);
            }
        }
    }

    public void jogadorAtira(){
        Tiro tiro = jogador.atirar();
        if (tiro != null) {
            tiros.add(tiro);
            mainPanel.add(tiro);
        }
    }

    public boolean colideInimigo(Tiro tiro){
        Rectangle hitboxTiro = tiro.getBounds();
        for (Inimigo inimigo : inimigos) {
            Rectangle hitboxInimigo = inimigo.getHitbox();
            if (hitboxTiro.intersects(hitboxInimigo)) {
                inimigo.tomarDano(tiro.getDano());
                if (inimigo.getVida() > 0) return true;

                inimigos.remove(inimigo);
                mainPanel.remove(inimigo);
                return true;
            }
        }
        return false;
    }

    public boolean colideJogador(Tiro tiro){
        Rectangle hitboxTiro = tiro.getBounds();
        Rectangle hitboxJogador = jogador.getBounds();
        return hitboxTiro.intersects(hitboxJogador);
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