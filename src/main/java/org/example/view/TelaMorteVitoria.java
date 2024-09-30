package org.example.view;

import org.example.controller.Personagem;
import org.example.controller.Usuario;
import org.example.util.CarregadorFonte;
import org.example.util.TratadorMouseClick;
import org.example.util.TratadorMouseHover;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class TelaMorteVitoria extends JPanel
{
    private final JLabel Res = new JLabel();
    private final JLabel Scr = new JLabel();

    private final Font fnt= CarregadorFonte.CarregaFonte("fonts/FE.ttf",50f);
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf", 30f);

    private final JButton jogarDnv = new JButton("Jogar Novamente");
    private final JButton voltar = new JButton("Voltar para o inicio");

    private final JPanel pnP = new JPanel();
    private final JPanel contB = new JPanel();

    public TelaMorteVitoria(Clip Click, Clip Hov,boolean ganhou,Usuario user, int score)
    {
        ConfigurarJPanel();
        ConfigurarJLabels(ganhou,user, score);
        ConfigurarJButtons();
        ConfigurarListenersHover(Click,Hov);
        ConfigurarListenerAc(Click,Hov);
        ConfigurarThis();
    }

    private void ConfigurarJPanel()
    {
        contB.setLayout(new BoxLayout(contB,BoxLayout.X_AXIS));
        contB.add(jogarDnv);
        contB.add(voltar);
        contB.setBackground(Color.black);
        contB.setVisible(true);

        pnP.setLayout(new BoxLayout(pnP,BoxLayout.Y_AXIS));
        pnP.add(Res);
        pnP.add(Scr);
        pnP.add(contB);
        pnP.setBackground(Color.black);
        pnP.setVisible(true);
    }
    private void ConfigurarThis()
    {
        this.setPreferredSize(new Dimension(1000, 800));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        this.add(pnP);
    }
    private void ConfigurarJLabels(boolean ganhou, Usuario user, int score)
    {

        Res.setSize(new Dimension(200, 200));
        Res.setFont(fnt);
        Res.setVisible(true);
        Res.setAlignmentX(Component.CENTER_ALIGNMENT);

        Scr.setForeground(Color.white);
        Scr.setSize(new Dimension(200, 200));
        Scr.setFont(fnt2);
        Scr.setVisible(true);
        Scr.setAlignmentX(Component.CENTER_ALIGNMENT);
        Scr.setText("SCORE: " + score);

        if(ganhou)
            Res.setText("Vitória");
        else
            Res.setText("Derrota");
    }
    private void ConfigurarJButtons()
    {
        jogarDnv.setFont(fnt2);
        jogarDnv.setForeground(Color.white);
        jogarDnv.setPreferredSize(new Dimension(150, 100));
        jogarDnv.setBackground(Color.BLACK);

        voltar.setFont(fnt2);
        voltar.setForeground(Color.white);
        voltar.setPreferredSize(new Dimension(150, 100));
        voltar.setBackground(Color.BLACK);
    }
    private void ConfigurarListenersHover(Clip Click, Clip Hov)
    {
        voltar.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        jogarDnv.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
    }
    private void ConfigurarListenerAc(Clip Click, Clip Hov)
    {
        voltar.addMouseListener(new TratadorMouseClick(Click,Hov,null,null,null,false,false,"TelaSelLoginCad"));
        jogarDnv.addMouseListener(new TratadorMouseClick(Click,Hov,null,null,null,false,true,"Fase1"));
    }
}
