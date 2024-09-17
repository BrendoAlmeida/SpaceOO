package org.example.view;
import org.example.util.CarregadorFonte;
import org.example.util.CarregadorImagem;
import org.example.util.TratadorMouseClick;
import org.example.util.TratadorMouseHover;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelPerso extends JPanel
{
    private final JLabel txtSel = new JLabel("Selecione seu personagem");
    private final JLabel estP1 = new JLabel("<html>Vida : <br/> Dano: <br/> Velocidade: <br/></html>");
    private final JLabel estP2 = new JLabel("<html>Vida : <br/> Dano: <br/> Velocidade: <br/></html>");

    private final ImageIcon imgP1 = CarregadorImagem.CarregaIcone("img/player1.png",250,250);
    private final ImageIcon imgP2 = CarregadorImagem.CarregaIcone("img/player2.png",250,250);

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/FE.ttf");
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf");

    private final JPanel contPs = new JPanel();
    private final JPanel contP1 = new JPanel();
    private final JPanel contP2 = new JPanel();

    private final JButton SelP1 = new JButton(imgP1);
    private final JButton SelP2 = new JButton(imgP2);
    private final JButton conf = new JButton("Selecionar");

    public SelPerso(Clip Click, Clip Hov)
    {
        this.setSize(1000,800);
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        txtSel.setFont(fnt.deriveFont(50f));
        txtSel.setForeground(Color.white);

        estP1.setForeground(Color.white);
        estP1.setBackground(Color.BLACK);
        estP1.setFont(fnt2);
        estP1.setVisible(false);

        estP2.setForeground(Color.white);
        estP2.setBackground(Color.BLACK);
        estP2.setFont(fnt2);
        estP2.setVisible(false);

        SelP1.setBackground(Color.BLACK);
        SelP1.setSize(new Dimension(250,250));

        SelP1.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        SelP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(estP2.isVisible())
                    estP2.setVisible(false);
                estP1.setVisible(true);
            }});


        SelP2.setBackground(Color.BLACK);
        SelP2.setSize(new Dimension(250,250));
        SelP2.addMouseListener(new TratadorMouseHover(Click,Hov,null,null,null));
        SelP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(estP1.isVisible())
                    estP1.setVisible(false);

                estP2.setVisible(true);
            }
        });

        SelP1.setVisible(true);
        SelP2.setVisible(true);

        conf.setForeground(Color.white);
        conf.setBackground(Color.BLACK);
        conf.setFont(fnt2);
        conf.addMouseListener(new TratadorMouseClick(Click,Hov,null,null,null,false,"fase1"));


        contP1.add(SelP1);
        contP1.add(estP1);
        contP1.setLayout(new BoxLayout(contP1,BoxLayout.Y_AXIS));
        contP1.setBackground(Color.black);

        contP2.add(SelP2);
        contP2.add(estP2);
        contP2.setLayout(new BoxLayout(contP2,BoxLayout.Y_AXIS));
        contP2.setBackground(Color.black);

        contPs.add(contP1);
        contPs.add(contP2);
        contPs.setBackground(Color.black);
        contPs.setLayout(new BoxLayout(contPs,BoxLayout.X_AXIS));

        this.add(txtSel);
        this.add(contPs);
        this.add(conf);
        this.setVisible(true);
    }
}