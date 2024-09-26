package org.example.controller;

import org.example.util.CarregadorFonte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ControllerJogo {
    private Dimension dimencao;
    private int fatorDimecao;

    private List<Inimigo> inimigos = new java.util.ArrayList<>();
    private int direcaoInimigo = 1;
    private List<Tiro> tiros = new java.util.ArrayList<>();
    private List<Parede> paredes = new java.util.ArrayList<>();

    private Personagem jogador;
    private JPanel mainPanel;
    private Timer updateTimer;
    private Timer renderTimer;
    private Font font1 = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf", 20f);

    // botões
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean atirar = false;

    private JLabel LblScore = new JLabel();
    private int score = 0;
    private double multScore = 100;

    private JPanel PanelVida = new JPanel();

    public void initScore(){
        LblScore.setText("Score: " + score);
        int tamanho = fatorDimecao*4;
        LblScore.setBounds(dimencao.width - tamanho, 20, tamanho, 20);
        LblScore.setFont(font1);

        mainPanel.add(LblScore);
    }

    public void initVida(){
        PanelVida.setBounds(20, 20, 60, 20);
        mainPanel.add(PanelVida);
    }

    public void initInimigos(Inimigo inimigo, int qtdFileiras) {
        int tamanhoX = this.getDimencao().width;
        int fatorDim = this.getFatorDimecao();
        int qtdInimigos = (tamanhoX - fatorDim*2);

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
        int[] pos = new int[]{(dimencao.width / 2) - jogador.getTamanho().width, (dimencao.height - jogador.getTamanho().height - 20)};
        jogador.setPos(pos);
        mainPanel.add(jogador);
        jogador.setFocusable(true);
        jogador.requestFocusInWindow();
        mainPanel.repaint();
    }

    public void initParede(Parede parede) {
        int tamanhoX = this.getDimencao().width;
        int tamanhoY = this.getDimencao().height;
        int fatorDim = this.getFatorDimecao();
        int qtdParedes = (tamanhoX - fatorDim*2);

        for (int i = parede.getTamanho().width; i < qtdParedes; i += parede.getTamanho().width*2) {
            Parede novaParede = parede.clone();
            novaParede.setPos(new int[]{i, tamanhoY - parede.getTamanho().height*2});
            paredes.add(novaParede);
            mainPanel.add(novaParede);
        }
        mainPanel.repaint();
    }

    public ControllerJogo(JPanel mainPanel, Dimension dimencao, int fatorDimecao, Inimigo inimigo, Personagem jogador, Parede parede, int qtdFileiras) {
        this.dimencao = dimencao;
        this.fatorDimecao = fatorDimecao;
        this.mainPanel = mainPanel;

        initInimigos(inimigo, qtdFileiras);
        initPersonagem(jogador);
        initParede(parede);
        initScore();
        initVida();
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
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
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
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
                    atirar = false;
                }
            }
        });

        startGameLoop();
        startRenderLoop();
    }

    private void startGameLoop() {
        updateTimer = new Timer(32, new ActionListener() {
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
            jogador.mover(-1, dimencao.width);
        }
        if (moveRight) {
            jogador.mover(1, dimencao.width);
        }
        if(atirar) {
            jogadorAtira();
        }
        inimigoAtira();
        moveTiro();
        moveInimigo();
        updateScore();
        updateVida();
        jogador.delayTiro();
        for (Inimigo inimigo : inimigos) {
            inimigo.delayTiro();
        }
    }

    public void updateScore(){
        multScore *= 0.9999;
        LblScore.setText("Score: " + score);
    }

    public void updateVida(){
        int vida = jogador.getVida();

        PanelVida.removeAll();

        for (int i = 0; i < vida; i++) {
            JLabel coracao = new JLabel();
            coracao.setBounds((PanelVida.getComponentCount())*20, 0, 20, 20);
//            coracao.setIcon(new ImageIcon("img/coracao.png"));
            coracao.setBackground(Color.RED);
            coracao.setOpaque(true);
            PanelVida.add(coracao);
        }
    }

    public void moveTiro(){
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            tiro.mover();

            if (tiro.getPos()[1] < 0  || tiro.getPos()[1] > dimencao.height || tiro.getPos()[0] < 0 || tiro.getPos()[0] > dimencao.width) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                continue;
            }
            if (colideInimigo(tiro)) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                continue;
            }
            if (colideJogador(tiro)) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                continue;
            }
            if (colideParede(tiro)) {
                tiros.remove(tiro);
                mainPanel.remove(tiro);
                continue;
            }
        }
    }

    public void moveInimigo(){
        int i = 0;
        if (direcaoInimigo == 1) i = inimigos.size() - 1;

        for (; i < inimigos.size() && i >= 0; i += direcaoInimigo*-1) {
            Inimigo inimigo = inimigos.get(i);
            if (!inimigo.mover(direcaoInimigo, dimencao.width)) direcaoInimigo *= -1;
        }
    }

    public void inimigoAtira(){
        for (Inimigo inimigo : inimigos) {
            Tiro tiro;
            if (inimigo.getTiro().getClass().getName().equals("org.example.controller.TiroPersegue")) {
                tiro = inimigo.atirarPersegue(jogador.getPos());
            }else{
                tiro = inimigo.atirar();
            }

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
                if (tiro.isTiroInimigo()) return false;
                inimigo.tomarDano(tiro.getDano());
                if (inimigo.getVida() > 0) return true;

                inimigos.remove(inimigo);
                mainPanel.remove(inimigo);

                score += multScore;

                return true;
            }
        }
        return false;
    }

    public boolean colideJogador(Tiro tiro){
        Rectangle hitboxTiro = tiro.getBounds();
        Rectangle hitboxJogador = jogador.getBounds();
        if (!hitboxTiro.intersects(hitboxJogador)) return false;

        jogador.tomarDano(tiro.getDano());
        if(jogador.getVida() == 0) perde();

        return true;
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

    public Dimension getDimencao() {
        return dimencao;
    }

    public void setDimencao(Dimension dimencao) {
        this.dimencao = dimencao;
    }

    public java.util.List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void perde(){
        System.out.println("Perdeu");
        updateTimer.stop();
    }
}