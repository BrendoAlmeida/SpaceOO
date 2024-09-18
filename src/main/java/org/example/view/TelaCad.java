package org.example.view;

import org.example.controller.Usuario;
import org.example.util.CarregadorAudio;
import org.example.util.CarregadorFonte;
import org.example.util.TratadorMouseClick;
import org.example.util.TratadorMouseHover;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCad extends JPanel
{
    private final JLabel txtInfo = new JLabel("Insira seus dados de login:");
    private final JLabel txtNome = new JLabel("Nome:");
    private final JLabel txtSenha = new JLabel("Senha:");
    protected final JLabel txtLog = new JLabel("");

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf", 30f);
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/FT16.ttf", 30f);

    private final JButton conf = new JButton("Confirmar");
    private final JButton voltar = new JButton("Voltar");

    protected final JTextField NmUsr = new JTextField();
    protected final JTextField psswrd= new JTextField();

    protected Usuario usuario;

    public TelaCad(Clip Click, Clip Hov)
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000,300));
        this.setBackground(Color.black);

        txtNome.setForeground(Color.WHITE);
        txtSenha.setForeground(Color.WHITE);
        txtInfo.setForeground(Color.white);
        txtLog.setForeground(Color.white);

        txtInfo.setFont(fnt);
        txtLog.setFont(fnt2);
        txtNome.setFont(fnt);
        txtSenha.setFont(fnt);

        txtInfo.setVisible(true);
        txtLog.setVisible(true);
        txtNome.setVisible(true);
        txtSenha.setVisible(true);

        NmUsr.setMaximumSize(new Dimension(2000,100));
        psswrd.setMaximumSize(new Dimension(2000,100));
        NmUsr.addMouseListener(new TratadorMouseHover(Click,Hov,txtLog,NmUsr,psswrd));
        psswrd.addMouseListener(new TratadorMouseHover(Click,Hov,txtLog,NmUsr,psswrd));

        NmUsr.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(Hov != null)
                {
                    if(Hov.isRunning())
                        Hov.stop();
                    Hov.setFramePosition(0);
                    Hov.start();
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(Hov != null)
                    Hov.stop();
            }
        });
        psswrd.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (Hov != null)
                {
                    if(Hov.isRunning())
                        Hov.stop();

                    Hov.setFramePosition(0);
                    Hov.start();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(Hov != null)
                    Hov.stop();
            }
        });

        conf.setFont(fnt);
        conf.setForeground(Color.white);
        conf.setPreferredSize(new Dimension(150,100));
        conf.setBackground(Color.BLACK);
        conf.addMouseListener(new TratadorMouseClick(Click,Hov,txtLog,NmUsr,psswrd,true,"SelPerso"));

        voltar.setFont(fnt);
        voltar.setForeground(Color.white);
        voltar.setSize(new Dimension(150,100));
        conf.setBackground(Color.black);
        conf.addMouseListener(new TratadorMouseHover(Click, Hov,txtLog,NmUsr,psswrd));

        NmUsr.setPreferredSize(new Dimension(600,100));
        NmUsr.setBackground(Color.BLACK);
        NmUsr.setFont(fnt2.deriveFont(30f));
        NmUsr.setForeground(Color.getColor("#d5ebdb"));
        NmUsr.setVisible(true);

        psswrd.setPreferredSize(new Dimension(600,100));
        psswrd.setFont(fnt2.deriveFont(30f));
        psswrd.setBackground(Color.BLACK);
        psswrd.setForeground(Color.getColor("#d5ebdb"));
        psswrd.setVisible(true);

        this.add(txtInfo);
        this.add(txtNome);
        this.add(NmUsr);
        this.add(txtSenha);
        this.add(psswrd);
        this.add(conf);
        this.add(txtLog);
        this.setVisible(true);
    }
}
