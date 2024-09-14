package org.example.view;
import org.example.util.CarregadorFonte;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//.setForeground() muda a cor do texto
//.setBorder() pra configurar a borda


public class TelaSelLoginCad extends JPanel
{

    final int LARGURA = 1000;
    final int ALTURA = 800;

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf");
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/FE.ttf");

    private final JButton btnCAD = new JButton("CADASTRO");
    private final JButton btnLogin = new JButton("LOGIN");

    private final JPanel Cad = new JPanel();
    private final JPanel Login = new JPanel();

    private final JPanel selec = new JPanel();
    private final JPanel contTxt = new JPanel();

    private final JLabel title = new JLabel("SPACE SWINGVADERS");
    private final JLabel txtLog = new JLabel("Selecione o que deseja fazer");

    public TelaSelLoginCad()
    {
        this.setPreferredSize(new Dimension(LARGURA,ALTURA));

        contTxt.setLayout(new BoxLayout(contTxt,BoxLayout.Y_AXIS));
        contTxt.setBorder(new EmptyBorder(100,0,0,150));
        contTxt.setPreferredSize(new Dimension(500, 400));
        contTxt.setBackground(Color.BLACK);
        contTxt.setVisible(true);

        Cad.setPreferredSize(new Dimension(400,400));
        Cad.setBackground(Color.BLACK);
        Cad.add(btnCAD);
        Cad.setVisible(true);

        Login.setPreferredSize(new Dimension(400,400));
        Login.setBackground(Color.BLACK);
        Login.add(btnLogin);
        Login.setVisible(true);

        selec.setPreferredSize(new Dimension(ALTURA, LARGURA));
        selec.setBackground(Color.BLACK);
        selec.setLayout(new BoxLayout(selec,BoxLayout.X_AXIS));
        selec.add(Cad);
        selec.add(Login);
        selec.setVisible(true);

        title.setFont(fnt2.deriveFont(50f));
        title.setForeground(Color.white);
        txtLog.setFont(fnt);
        txtLog.setForeground(Color.white);

        //organiza o painel em um layout de caixas (BoxLayout.Y_AXIS para eixo y e BoxLayout.X_AXIS para eixo x)
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.black);

        btnLogin.setFont(fnt);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.black);
        btnCAD.setForeground(Color.WHITE);
        btnCAD.setBackground(Color.black);
        btnLogin.setVisible(true);
        btnCAD.setFont(fnt);
        btnCAD.setVisible(true);

        btnCAD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FramePrincipal.CarregarPag("TelaCad");
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FramePrincipal.CarregarPag("TelaLog");
            }
        });
        contTxt.add(title);
        contTxt.add(txtLog);

        this.add(contTxt);
        this.add(selec);

        this.setVisible(true);
    }
    public void paint(Graphics  g)
    {
        super.paint(g);
    }

}

