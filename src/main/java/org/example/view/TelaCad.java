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
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
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
    protected final JTextField psswrd= new JTextField("Senha deve ter pelo menos 8 caracteres, uma maiúscula e um Dígito");

    protected Usuario usuario;

    public TelaCad(Clip Click, Clip Hov)
    {
        ConfigurarJButtons();
        ConfigurarJLabels();
        ConfigurarListeners(Click,Hov);
        ConfigurarThis();
    }

    private void ConfigurarJButtons()
    {
        conf.setFont(fnt);
        conf.setForeground(Color.white);
        conf.setPreferredSize(new Dimension(150, 100));
        conf.setBackground(Color.BLACK);

        voltar.setFont(fnt);
        voltar.setForeground(Color.white);
        voltar.setSize(new Dimension(150, 100));
        voltar.setBackground(Color.black);

        NmUsr.setPreferredSize(new Dimension(600, 100));
        NmUsr.setBackground(Color.BLACK);
        NmUsr.setFont(fnt2.deriveFont(30f));
        NmUsr.setForeground(Color.getColor("#d5ebdb"));
        NmUsr.setVisible(true);

        psswrd.setPreferredSize(new Dimension(600, 100));
        psswrd.setFont(fnt2.deriveFont(30f));
        psswrd.setBackground(Color.BLACK);
        psswrd.setForeground(Color.getColor("#d5ebdb"));
        psswrd.setVisible(true);

        NmUsr.setMaximumSize(new Dimension(2000, 100));
        psswrd.setMaximumSize(new Dimension(2000, 100));
    }
    private void ConfigurarJLabels()
    {
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
    }
    private void ConfigurarListeners(Clip Click, Clip Hov)
    {
        NmUsr.addMouseListener(new TratadorMouseHover(Click, Hov, txtLog, NmUsr, psswrd));
        NmUsr.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (Hov != null) {
                    if (Hov.isRunning())
                        Hov.stop();
                    Hov.setFramePosition(0);
                    Hov.start();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (Hov != null)
                    Hov.stop();
            }
        });
        psswrd.addMouseListener(new TratadorMouseHover(Click, Hov, txtLog, NmUsr, psswrd));
        psswrd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                psswrd.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        psswrd.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (Hov != null) {
                    if (Hov.isRunning())
                        Hov.stop();

                    Hov.setFramePosition(0);
                    Hov.start();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (Hov != null)
                    Hov.stop();
            }
        });
        conf.addMouseListener(new TratadorMouseClick(Click, Hov, txtLog, NmUsr, psswrd, true, false, "SelPerso"));
        voltar.addMouseListener(new TratadorMouseClick(Click, Hov, false, false, "TelaSelLoginCad"));
    }
    private void ConfigurarThis()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000, 300));
        this.setBackground(Color.black);

        this.add(voltar);
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
