package org.example.view;
import org.example.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

//.setForeground() muda a cor do texto
//.setBorder() pra configurar a borda
//Classe Clip
//
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class TelaSelLoginCad extends JPanel {

    final int LARGURA = 1000;
    final int ALTURA = 800;

    private final Image imgInmg1 = CarregadorImagem.CarregaImagem("img/inimigo1.png");
    private final Image imgInmg2 = CarregadorImagem.CarregaImagem("img/inimigo2.png");

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf", 30f);
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/FE.ttf", 30f);

    private final JButton btnCAD = new JButton("CADASTRO");
    private final JButton btnLogin = new JButton("LOGIN");

    private final JPanel Cad = new JPanel();
    private final JPanel Login = new JPanel();

    private final JPanel selec = new JPanel();
    private final JPanel contTxt = new JPanel();

    private final JLabel title = new JLabel("SPACE SWINGVADERS");
    private final JLabel txtLog = new JLabel("Selecione o que deseja fazer");
    private final JLabel musicCredits = new JLabel("Music by Jake feddermanb from Pixabay");

    public TelaSelLoginCad(Clip Click,Clip Hov, Clip clip)
    {
        ConfigurarThis();
        ConfigurarJpanels();
        ConfigurarJButtons();
        ConfigurarListeners(Click,Hov);
        ConfigurarJLabels();

        if(clip != null)
        {
            clip.setFramePosition(0);
            clip.start();
            clip.loop((int)clip.getMicrosecondLength());
        }

        this.add(contTxt);
        this.add(txtLog);
        this.add(selec);
        this.add(musicCredits);
        this.setVisible(true);
    }

    private void ConfigurarThis()
    {
        this.setPreferredSize(new Dimension(LARGURA, ALTURA));
        //organiza o painel em um layout de caixas (BoxLayout.Y_AXIS para eixo y e BoxLayout.X_AXIS para eixo x)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.black);
    }
    private void ConfigurarJpanels()
    {
        contTxt.setLayout(new BoxLayout(contTxt, BoxLayout.Y_AXIS));
        contTxt.setBorder(new EmptyBorder(100, 0, 0, 150));
        contTxt.setPreferredSize(new Dimension(500, 400));
        contTxt.setBackground(Color.BLACK);
        contTxt.setVisible(true);
        contTxt.add(title);

        Cad.setPreferredSize(new Dimension(400, 400));
        Cad.setBackground(Color.BLACK);
        Cad.add(btnCAD);
        Cad.setVisible(true);

        Login.setPreferredSize(new Dimension(400, 400));
        Login.setBackground(Color.BLACK);
        Login.add(btnLogin);
        Login.setVisible(true);

        selec.setPreferredSize(new Dimension(ALTURA, LARGURA));
        selec.setBackground(Color.BLACK);
        selec.setLayout(new BoxLayout(selec, BoxLayout.X_AXIS));
        selec.add(Cad);
        selec.add(Login);
        selec.setVisible(true);
    }
    private void ConfigurarJLabels()
    {
        title.setFont(fnt2.deriveFont(50f));
        title.setForeground(Color.white);
        txtLog.setFont(fnt);
        txtLog.setForeground(Color.white);
    }
    private void ConfigurarJButtons()
    {
        btnLogin.setFont(fnt);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.black);
        btnCAD.setForeground(Color.WHITE);
        btnCAD.setBackground(Color.black);

        btnLogin.setVisible(true);
        btnCAD.setFont(fnt);
        btnCAD.setVisible(true);
    }
    private void ConfigurarListeners(Clip Click, Clip Hov)
    {
        btnCAD.addMouseListener(new TratadorMouseClick(Click, Hov, null, null, null, false, false, "TelaCad"));
        btnLogin.addMouseListener(new TratadorMouseClick(Click, Hov, null, null, null, false, false, "TelaLog"));
    }

    public void paint(Graphics  g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(imgInmg1,btnCAD.getLocation().x + 75,375,null);
        g2d.drawImage(imgInmg2,710,365,null);
    }

}

