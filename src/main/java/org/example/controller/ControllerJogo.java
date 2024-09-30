package org.example.controller;

import org.example.model.modelUsuario;
import org.example.util.*;
import org.example.view.Fase1;
import org.example.view.FramePrincipal;
import org.example.view.TelaLog;
import org.example.view.TelaMorteVitoria;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class ControllerJogo {
    private Dimension dimencao;
    private int fatorDimecao;

    private Inimigo inimigoType;
    private Personagem jogadorType;

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
    private final int faseAt;

    private Clip somMorte = CarregadorAudio.CarregarAudio("audio/SomDeMorte.wav");
    private Clip SfxTiro = CarregadorAudio.CarregarAudio("audio/Tiro1.wav");
    private Clip SfxTiroPersegue = CarregadorAudio.CarregarAudio("audio/Tiro2.wav");
    private Clip SfxParedeQuebra = CarregadorAudio.CarregarAudio("audio/ParedeQuebrando.wav");
    private Clip DanoSfx = CarregadorAudio.CarregarAudio("audio/Dano.wav");
    private Clip SfxTiroJog = CarregadorAudio.CarregarAudio("audio/Tiro3.wav");

    private Timer delaySomTiro = new Timer(250, e -> {
        if(SfxTiro.isActive())
        {
            SfxTiro.stop();
            SfxTiro.setFramePosition(0);
            SfxTiro.start();
        }
        else
            SfxTiro.start();
    });

    private void PararAudios()
    {
        somMorte.stop();
        SfxTiro.stop();
        SfxTiroPersegue.stop();
        SfxParedeQuebra.stop();
        DanoSfx.stop();
        SfxTiroJog.stop();

        somMorte.close();
        SfxTiro.close();
        SfxTiroPersegue.close();
        SfxParedeQuebra.close();
        DanoSfx.close();
        SfxTiroJog.close();
    }

    private Timer delaySomTiroPersegue = new Timer(200, e->{
        if(SfxTiroPersegue.isActive())
        {
            SfxTiroPersegue.stop();
            SfxTiroPersegue.setFramePosition(0);
            SfxTiroPersegue.start();
        }
        else
            SfxTiroPersegue.start();
    });
    private final int VIDA_MAXIMA_PAREDE = 30;

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

    public void initInimigos(Inimigo inimigo, int qtdFileiras, int maxInimigos) {
        int tamanhoX = this.getDimencao().width;
        int fatorDim = this.getFatorDimecao();
        int qtdInimigos = (tamanhoX - fatorDim*2);

        if (maxInimigos == -1) maxInimigos = qtdInimigos;

        for (int i = fatorDim; i < qtdInimigos; i += fatorDim) {
            if (inimigos.size() >= maxInimigos) break;
            for (int j = 0; j < qtdFileiras; j++) {
                if (inimigos.size() >= maxInimigos) break;
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

    public void initKeyBindings(JPanel panel)
    {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"moveLeft");
        panel.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLeft = true;
            }
        });

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"moveRight");
        panel.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRight = true;
            }
        });

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"),"atirar");
        panel.getActionMap().put("atirar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atirar = true;
            }
        });

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"),"stopLeft");
        panel.getActionMap().put("stopLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLeft = false;
            }
        });

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"),"stopRight");
        panel.getActionMap().put("stopRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRight = false;
            }
        });

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SPACE"),"stopAtirar");
        panel.getActionMap().put("stopAtirar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atirar = false;
            }
        });
    }

    public ControllerJogo(JPanel mainPanel, Dimension dimencao, int fatorDimecao, Inimigo inimigo, Personagem jogador, Parede parede, int qtdFileiras, int fase, int maxInimigos, int scr) {
        this.dimencao = dimencao;
        this.score = scr;
        this.fatorDimecao = fatorDimecao;
        this.mainPanel = mainPanel;
        //modifica o construtor para verificar qual é a fase
        //para fazer as funções de ganha e perde serem universais
        this.faseAt = fase;

        this.PanelVida.setBackground(Color.black);

        initInimigos(inimigo, qtdFileiras, maxInimigos);
        inimigoType = inimigo;
        initPersonagem(jogador);
        jogadorType = jogador;
        initParede(parede);
        initScore();
        initVida();
        this.jogador = jogador;
        mainPanel.repaint();

        initKeyBindings(mainPanel);

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

        if(inimigos.isEmpty()) ganha(faseAt);
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
            coracao.setIcon(CarregadorImagem.CarregaIcone("img/coracao.png",25,25));
            coracao.setBackground(Color.BLACK);
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

                if(tiro instanceof TiroPersegue)
                    delaySomTiroPersegue.start();
                else
                    delaySomTiro.start();

                boolean tiroColide = false;
                for(int i = 0; i < inimigos.size(); i++){
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
            if(SfxTiroJog.isActive())
            {
                SfxTiroJog.stop();
                SfxTiroJog.setFramePosition(0);
                SfxTiroJog.start();
            }
            else
            {
                SfxTiroJog.setFramePosition(0);
                SfxTiroJog.start();
            }
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

                if(somMorte.isActive())
                {
                    somMorte.stop();
                    somMorte.setFramePosition(0);
                    somMorte.start();
                }
                else
                {
                    somMorte.setFramePosition(0);
                    somMorte.start();
                }
                inimigos.remove(inimigo);
                mainPanel.remove(inimigo);

                score += multScore * inimigoType.getVida();

                return true;
            }
        }
        return false;
    }

    public boolean colideJogador(Tiro tiro){
        Rectangle hitboxTiro = tiro.getBounds();
        Rectangle hitboxJogador = jogador.getBounds();
        if (!hitboxTiro.intersects(hitboxJogador)) return false;

        if(DanoSfx.isActive())
        {
            DanoSfx.stop();
            DanoSfx.setFramePosition(0);
            DanoSfx.start();
        }
        else
        {
            DanoSfx.setFramePosition(0);
            DanoSfx.start();
        }

        jogador.tomarDano(tiro.getDano());
        if(jogador.getVida() == 0) perde(faseAt);

        return true;
    }

    public boolean colideParede(Tiro tiro){
        Rectangle hitboxTiro = tiro.getBounds();
        for (Parede parede : paredes) {
            Rectangle hitboxParede = parede.getBounds();
            if (hitboxTiro.intersects(hitboxParede)) {
                parede.tomarDano(tiro.getDano());
                if (parede.getVida() > 0)
                {
                    if(parede.getVida() < VIDA_MAXIMA_PAREDE*0.75 && parede.getVida() > VIDA_MAXIMA_PAREDE*0.35)
                        parede.setSprite("img/Parede2.png");
                    else if(parede.getVida() < VIDA_MAXIMA_PAREDE*0.3)
                        parede.setSprite("img/Parede3.png");

                    return true;
                }

                if(SfxParedeQuebra.isActive())
                {
                    SfxParedeQuebra.stop();
                    SfxParedeQuebra.setFramePosition(0);
                    SfxParedeQuebra.start();
                }
                else
                    SfxParedeQuebra.start();

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

    public void perde(int faseAt)
    {
        updateTimer.stop();
        renderTimer.stop();

        UsuarioJogando.getUserJog().setScore(score);
        modelUsuario.updateScore(UsuarioJogando.getUserJog());

        if(faseAt == 1)
            FramePrincipal.RemoverPag("Fase1");
        else if(faseAt == 2)
            FramePrincipal.RemoverPag("Fase2");
        else if(faseAt == 3)
            FramePrincipal.RemoverPag("Fase3");

        FramePrincipal.AddPag(new TelaMorteVitoria(FramePrincipal.Click,FramePrincipal.Hov,false, UsuarioJogando.getUserJog(), score),"TelaMorteVitoria");
        FramePrincipal.CarregarPag("TelaMorteVitoria");

        PararAudios();
        //Parar a execução da fase em segundo plano
        throw new FimFaseException();
    }

    public void ganha(int fase)
    {//chamada quando inimigos.isEmpty()

        TelaLog.AttLista();
        UsuarioJogando.getUserJog().setScore(score);
        modelUsuario.updateScore(UsuarioJogando.getUserJog());

        if(faseAt == 1)
            FramePrincipal.RemoverPag("Fase1");
        else if(faseAt == 2)
            FramePrincipal.RemoverPag("Fase2");
        else if(faseAt == 3)
            FramePrincipal.RemoverPag("Fase3");


        if(fase < 3)
            FramePrincipal.IniciaFase(fase+1,jogador.getId(),this.score);
        else
        {
            FramePrincipal.AddPag(new TelaMorteVitoria(FramePrincipal.Click,FramePrincipal.Hov,true, UsuarioJogando.getUserJog(), score),"TelaMorteVitoria");
            FramePrincipal.CarregarPag("TelaMorteVitoria");
        }

        PararAudios();
        //Parar a execução da fase em segundo plano
        throw new FimFaseException();
    }
}