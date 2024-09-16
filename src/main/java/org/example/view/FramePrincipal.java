package org.example.view;
import org.example.util.CarregadorAudio;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class FramePrincipal extends JFrame
{
    protected static Clip Hov = CarregadorAudio.CarregarAudio("audio/Hover.wav");
    protected static Clip Click = CarregadorAudio.CarregarAudio("audio/Click.wav");
    protected static Clip clip = CarregadorAudio.CarregarAudio("audio/menuTheme.wav");

    private final static CardLayout CdLt = new CardLayout();
    private final static JPanel pnPrincipal = new JPanel(CdLt);

    private final static TelaSelLoginCad tlLC = new TelaSelLoginCad(Click,Hov,clip);
    private final static TelaLog tlLog = new TelaLog(Click,Hov);
    private final static TelaCad tlCad = new TelaCad(Click,Hov);
    private final static SelPerso SelP = new SelPerso(Click,Hov);
    //private final static fase1 F1 = new fase1();




    public static void CarregarPag(String pag)
    {
        CdLt.show(pnPrincipal,pag);
    }

    public FramePrincipal()
    {
        this.setTitle("Space SwingInvaders");
        pnPrincipal.add(tlLC,"TelaSelLoginCad");
        pnPrincipal.add(tlLog,"TelaLog");
        pnPrincipal.add(tlCad,"TelaCad");
        pnPrincipal.add(SelP,"SelPerso");
        //pnPrincipal.add(F1,"fase1");

        CdLt.show(pnPrincipal,"TelaSelLoginCad");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pnPrincipal);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
