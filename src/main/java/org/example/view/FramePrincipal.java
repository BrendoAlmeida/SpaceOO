package org.example.view;
import javax.swing.*;
import java.awt.*;

public class FramePrincipal extends JFrame
{
    private final static CardLayout CdLt = new CardLayout();
    private final static JPanel pnPrincipal = new JPanel(CdLt);

    private final static TelaSelLoginCad tlLC = new TelaSelLoginCad();
    private final static TelaLog tlLog = new TelaLog();
    private final static TelaCad tlCad = new TelaCad();
    private final static SelPerso SelP = new SelPerso();

    protected static void CarregarPag(String pag)
    {
        CdLt.show(pnPrincipal,pag);
    }

    public FramePrincipal()
    {
        pnPrincipal.add(tlLC,"TelaSelLoginCad");
        pnPrincipal.add(tlLog,"TelaLog");
        pnPrincipal.add(tlCad,"TelaCad");
        pnPrincipal.add(SelP,"SelPerso");

        CdLt.show(pnPrincipal,"TelaSelLoginCad");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pnPrincipal);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
