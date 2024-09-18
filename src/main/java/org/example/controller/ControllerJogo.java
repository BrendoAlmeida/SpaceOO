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
    private int direcaoInimigo = 1;
    private List<Tiro> tiros = new java.util.ArrayList<>();
    private List<Parede> paredes = new java.util.ArrayList<>();

    private Personagem jogador;
    private JPanel mainPanel;
    private Timer updateTimer;
    private Timer renderTimer;

    // botões
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean atirar = false;

    public void initInimigos(Inimigo inimigo, int qtdFileiras) {
        int tamanhoX = this.getDimencao()[0];
        int fatorDim = this.getFatorDimecao();
        int qtdInimigos = (tamanhoX - fatorDim*3);

        for (int i = fatorDim; i < qtdInimigos; i += fatorDim) {
            for (int j = 0; j < qtdFileiras; j++) {
                Inimigo novoInimigo = inimigo.clone();
                novoInimigo.setPos(new int[]{i, fatorDim + (j * fatorDim)});
                inimigos.add(novoInimigo);
                mainPanel.add(novoInimigo);
            }
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

    public void initParede(Parede parede) {
        int tamanhoX = this.getDimencao()[0];
        int tamanhoY = this.getDimencao()[1];
        int fatorDim = this.getFatorDimecao();
        int qtdParedes = (tamanhoX - fatorDim*2);

        for (int i = parede.getTamanho(); i < qtdParedes; i += parede.getTamanho()*2) {
            Parede novaParede = parede.clone();
            novaParede.setPos(new int[]{i, tamanhoY - parede.getTamanho()*2});
            paredes.add(novaParede);
            mainPanel.add(novaParede);
        }
        mainPanel.repaint();
    }

    public ControllerJogo(JPanel mainPanel, int[] pos, int fatorDimecao, Inimigo inimigo, Personagem jogador, Parede parede, int qtdFileiras) {
        dimencao = pos;
        this.fatorDimecao = fatorDimecao;
        this.mainPanel = mainPanel;

        initInimigos(inimigo, qtdFileiras);
        initPersonagem(jogador);
        initParede(parede);
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
        moveInimigo();
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
            }
            if (colideParede(tiro)) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
            }
        }
    }

    public void moveInimigo(){
        int i = 0;
        if (direcaoInimigo == 1) i = inimigos.size() - 1;

        for (; i < inimigos.size() && i >= 0; i += direcaoInimigo*-1) {
            Inimigo inimigo = inimigos.get(i);
            if (!inimigo.mover(direcaoInimigo, dimencao[0])) direcaoInimigo *= -1;
        }
    }

    public void inimigoAtira(){
        for (Inimigo inimigo : inimigos) {
            Tiro tiro = inimigo.atirar();
            if (tiro != null) {
                boolean tiroColide = false;
                for (int i = 0; i < inimigos.size(); i++){
                    if (tiro.getBounds().intersects(inimigos.get(i).getBounds())) {
                        tiroColide = true;
                        break;
                    }
                }
                if (tiroColide) continue;
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
            Rectangle hitboxInimigo = inimigo.getBounds();
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
        jogador.tomarDano(tiro.getDano());
        return hitboxTiro.intersects(hitboxJogador);
    }

    public boolean colideParede(Tiro tiro){
        Rectangle hitboxTiro = tiro.getBounds();
        for (Parede parede : paredes) {
            Rectangle hitboxParede = parede.getBounds();
            if (hitboxTiro.intersects(hitboxParede)) {
                parede.tomarDano(tiro.getDano());
                if (parede.getVida() > 0) return true;

                paredes.remove(parede);
                mainPanel.remove(parede);
                return true;
            }
        }
        return false;
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