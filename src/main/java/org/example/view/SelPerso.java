package org.example.view;
import org.example.controller.Personagem;
import org.example.model.modelPersonagem;
import org.example.util.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class SelPerso extends JPanel
{

    private final JLabel txtSel = new JLabel("Selecione seu personagem");

    private final List<JLabel> estatisticasPS = new ArrayList<>();

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/FE.ttf", 30f);
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf", 30f);

    private final JPanel contPs = new JPanel();

    private final List<JPanel> contPersonagens = new ArrayList<>();

    private final List<JButton> btnPersonagens = new ArrayList<>();

    private final JButton conf = new JButton("Selecionar");
    private final JButton voltar = new JButton("Voltar");

    public void initPersonagens(Clip Click, Clip Hov){
        List<Personagem> personagens = modelPersonagem.getPersonagens();

        for (int i = 0; i < personagens.size(); i++) {
            Personagem personagem = personagens.get(i);
            JButton button = new JButton(new ImageIcon(personagem.getSprite().getScaledInstance(250, 250, Image.SCALE_DEFAULT)));

            JLabel estatisticas = new JLabel("<html> Vida: " + personagem.getVida() + "<br> Velocidade: " + personagem.getVelocidade() + "<br> Delay Tiro: " + personagem.getDelayTiro() + "</html>");

            estatisticas.setForeground(Color.WHITE);
            estatisticas.setBackground(Color.BLACK);
            estatisticas.setFont(fnt2);
            estatisticas.setVisible(false);

            button.addMouseListener(new TratadorMouseHover(Click, Hov, null, null, null));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int k = 0; k < estatisticasPS.size(); k++) {
                        estatisticasPS.get(k).setVisible(false);
                    }

                    estatisticas.setVisible(true);
                    PersonagemSel.setPsel(personagem.getId());
                }
            });
            button.setBackground(Color.BLACK);
            button.setSize(new Dimension(250, 250));
            button.setVisible(true);

            JPanel panel = new JPanel();
            panel.add(button);
            panel.add(estatisticas);
            panel.setBackground(Color.BLACK);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            btnPersonagens.add(button);
            estatisticasPS.add(estatisticas);
            contPersonagens.add(panel);
        }
    }

    public SelPerso(Clip Click, Clip Hov) {
        ConfigurarJpanels();
        ConfigurarJButtons();
        ConfigurarJLabels();
        ConfigurarListenersAc(Click,Hov);
        initPersonagens(Click, Hov);
        ConfigurarThis();
    }

    private void ConfigurarJpanels()
    {
        contPs.setBackground(Color.black);
        contPs.setLayout(new BoxLayout(contPs, BoxLayout.LINE_AXIS));
        contPs.setSize(new Dimension(700, 600));
    }
    private void ConfigurarJLabels()
    {
        txtSel.setFont(fnt.deriveFont(50f));
        txtSel.setForeground(Color.white);
    }
    private void ConfigurarJButtons()
    {
        conf.setForeground(Color.white);
        conf.setBackground(Color.BLACK);
        conf.setFont(fnt2);

        voltar.setForeground(Color.white);
        voltar.setBackground(Color.BLACK);
        voltar.setFont(fnt2);
    }

    private void ConfigurarListenersAc(Clip Click, Clip Hov)
    {
        conf.addMouseListener(new TratadorMouseClick(Click, Hov, false, true, "Fase1"));
        voltar.addMouseListener(new TratadorMouseClick(Click, Hov,false, false, "TelaCad"));
    }

    private void ConfigurarThis()
    {
        this.setSize(1000, 800);
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(voltar);
        this.add(txtSel);
        this.add(contPs);
        for (JPanel contPersonagen : contPersonagens) {
            this.add(contPersonagen);
        }
        this.add(conf);
        this.setVisible(true);
    }
}
