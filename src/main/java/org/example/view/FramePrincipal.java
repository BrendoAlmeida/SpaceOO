package org.example.view;
import org.example.controller.Personagem;
import org.example.util.CarregadorAudio;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class FramePrincipal extends JFrame
{
    public static Clip Hov = CarregadorAudio.CarregarAudio("audio/Hover.wav");
    public static Clip Click = CarregadorAudio.CarregarAudio("audio/Click.wav");
    public static Clip clip = CarregadorAudio.CarregarAudio("audio/menuTheme.wav");

    private final static CardLayout CdLt = new CardLayout();
    protected final static JPanel pnPrincipal = new JPanel(CdLt);

    private final static TelaSelLoginCad tlLC = new TelaSelLoginCad(Click,Hov,clip);
    private final static TelaLog tlLog = new TelaLog(Click,Hov);
    private final static TelaCad tlCad = new TelaCad(Click,Hov);
    private final static SelPerso SelP = new SelPerso(Click,Hov);

    //private final static Fase1 F1 = new Fase1(clip, clip);

    public static void PararMusicaMenu()
    {
        clip.stop();
    }

    public static void CarregarPag(String pag)
    {
        CdLt.show(pnPrincipal,pag);
    }

    public static void RemoverPag(String nomePainel)
    {
        Component[] componentes = pnPrincipal.getComponents();
        for (Component comp : componentes) {
            if (comp.getName() != null && comp.getName().equals(nomePainel)) {
                pnPrincipal.remove(comp);
                break;
            }
        }
        pnPrincipal.revalidate();
        pnPrincipal.repaint();
    }

    public static void AddPag(JPanel pag, String StrPag)
    {
        pnPrincipal.add(pag,StrPag);
    }

    public static void SetCompName(Component comp,String str)
    {
        comp.setName(str);
    }

    public static void IniciaFase(int fase, int p, int scrAt)
    {
        if(fase == 1)
        {
            Fase1 f = new Fase1(p,scrAt);
            f.setName("Fase1");
            AddPag(f,"Fase1");
            Fase1.MudarMusica();
            CarregarPag("Fase1");
        }
        if(fase == 2)
        {
            Fase2 f = new Fase2(p,scrAt);
            f.setName("Fase2");
            AddPag(f,"Fase2");
            CarregarPag("Fase2");
        }
        if(fase == 3)
        {
            Fase3 f = new Fase3(p,scrAt);
            f.setName("Fase3");
            AddPag(f,"Fase3");
            CarregarPag("Fase3");
        }

    }
    public static void IniciaFase(int fase, Personagem p, int scrAt)
    {
        if(fase == 1)
        {
            Fase1 f = new Fase1(p,scrAt);
            f.setName("Fase1");
            AddPag(f,"Fase1");
            Fase1.MudarMusica();
            CarregarPag("Fase1");
        }
        if(fase == 2)
        {
            Fase1 f = new Fase1(p,scrAt);
            f.setName("Fase2");
            AddPag(f,"Fase2");
            CarregarPag("Fase2");
        }
        if(fase == 3)
        {
            Fase1 f = new Fase1(p,scrAt);
            f.setName("Fase3");
            AddPag(f,"Fase3");
            CarregarPag("Fase3");
        }
    }

    public FramePrincipal()
    {
        SetCompName(tlLC,"TelaSelLoginCad");
        SetCompName(tlLog,"TelaLog");
        SetCompName(tlCad,"TelaCad");
        SetCompName(SelP,"SelPerso");

        this.setTitle("Space SwingInvaders");
        pnPrincipal.add(tlLC,"TelaSelLoginCad");
        pnPrincipal.add(tlLog,"TelaLog");
        pnPrincipal.add(tlCad,"TelaCad");
        pnPrincipal.add(SelP,"SelPerso");

        //pnPrincipal.add(F1,"Fase1");

        CdLt.show(pnPrincipal,"TelaSelLoginCad");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnPrincipal);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
