package org.example.view;
import org.example.controller.Usuario;
import org.example.util.CarregadorFonte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//.setForeground() muda a cor do texto
//.setBorder() pra configurar a borda


public class TelaLoginPn extends JPanel
{

    final int LARGURA = 1000;
    final int ALTURA = 800;

    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf");
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/MachineStd-Medium.otf");

    private final JLabel txtInfo = new JLabel("Insira seus dados de login:");
    private final JLabel txtNome = new JLabel("Nome:");
    private final JLabel txtSenha = new JLabel("Senha:");
    private final JLabel txtLog = new JLabel("");

    private final JButton btnCAD = new JButton("CADASTRO");
    private final JButton btnLogin = new JButton("LOGIN");
    private final JButton conf = new JButton();

    private final JPanel Cad = new JPanel();
    private final JPanel Login = new JPanel();
    private final JPanel intPn = new JPanel();
    private final JPanel selec = new JPanel();

    private final JTextField NmUsr = new JTextField();
    private final JTextField psswrd= new JTextField();

    Usuario usuario;
    TelaLoginPn PAINEL = this;

    public TelaLoginPn()
    {
        intPn.setEnabled(false);
        intPn.setVisible(false);

        this.setPreferredSize(new Dimension(ALTURA,LARGURA));
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

        JButton conf = new JButton("Confirmar");
        //organiza o painel em um layout de caixas (BoxLayout.Y_AXIS para eixo y e BoxLayout.X_AXIS para eixo x)
        intPn.setLayout(new BoxLayout(intPn,BoxLayout.Y_AXIS));
        intPn.setPreferredSize(new Dimension(1000,250));
        intPn.setBackground(Color.black);
        //intPn.setVisible(true);

        this.setPreferredSize(new Dimension(LARGURA,ALTURA));
        this.setBackground(Color.black);

        txtNome.setForeground(Color.WHITE);
        txtSenha.setForeground(Color.WHITE);
        txtInfo.setForeground(Color.white);
        txtLog.setForeground(Color.white);

        txtInfo.setFont(fnt);
        txtLog.setFont(fnt);
        txtNome.setFont(fnt);
        txtSenha.setFont(fnt);

        txtInfo.setVisible(true);
        txtLog.setVisible(true);
        txtNome.setVisible(true);
        txtSenha.setVisible(true);

        btnLogin.setFont(fnt);
        btnLogin.setForeground(Color.WHITE);
        btnCAD.setForeground(Color.WHITE);
        btnLogin.setVisible(true);
        btnCAD.setFont(fnt);
        btnCAD.setVisible(true);
        conf.setFont(fnt);
        conf.setForeground(Color.white);
        conf.setPreferredSize(new Dimension(150,100));
        conf.setBackground(Color.BLACK);
        conf.setBorder(null);

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario =  new Usuario(NmUsr.getText(), psswrd.getText());
                if(usuario.login())
                {
                    txtLog.setEnabled(true);
                    txtLog.setText("Usuário logado com sucesso!");
                    PAINEL.setEnabled(false);
                    PAINEL.setVisible(false);
                }
                else
                    txtLog.setText("Usuário ou senha inválidos!");
            }
        });

        btnCAD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                intPn.setVisible(true);
                intPn.setVisible(true);
            }
        });

        NmUsr.setPreferredSize(new Dimension(600,100));
        psswrd.setPreferredSize(new Dimension(600,100));
        NmUsr.setFont(NmUsr.getFont().deriveFont(30f));
        psswrd.setFont(psswrd.getFont().deriveFont(30f));
        NmUsr.setBackground(Color.BLACK);
        psswrd.setBackground(Color.BLACK);
        NmUsr.setForeground(Color.WHITE);
        psswrd.setForeground(Color.WHITE);
        NmUsr.setVisible(true);
        psswrd.setVisible(true);

        intPn.add(txtInfo);
        intPn.add(txtNome);
        intPn.add(NmUsr);
        intPn.add(txtSenha);
        intPn.add(psswrd);
        intPn.add(conf);

        this.add(intPn);
        this.add(txtLog);
        this.setVisible(true);
    }
    public void paint(Graphics  g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
    }

}

