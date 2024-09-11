package org.example.view;
import org.example.controller.Usuario;
import org.example.model.modelLogin;
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
    private final Font fnt = CarregaRecursos.CarregaFonte("fonts/space_invaders.ttf");
    private final Font fnt2 = CarregaRecursos.CarregaFonte("fonts/MachineStd-Medium.otf");
    private final JLabel txtInfo = new JLabel("Insira seus dados de login:");
    private final JLabel txtLog = new JLabel("");
    Usuario usuario;
    private final JTextField NmUsr = new JTextField();
    private final JTextField psswrd= new JTextField();
    TelaLoginPn PAINEL = this;


    public TelaLoginPn()
    {
        this.setPreferredSize(new Dimension(ALTURA,LARGURA));
        JPanel intPn = new JPanel();
        JButton conf = new JButton("Confirmar");
        //organiza o painel em um layout de caixas (BoxLayout.Y_AXIS para eixo y e BoxLayout.X_AXIS para eixo x)
        intPn.setLayout(new BoxLayout(intPn,BoxLayout.Y_AXIS));
        intPn.setPreferredSize(new Dimension(1000,250));
        intPn.setBackground(Color.black);
        intPn.setVisible(true);

        this.setPreferredSize(new Dimension(LARGURA,ALTURA));
        this.setBackground(Color.black);

        txtInfo.setForeground(Color.white);
        txtInfo.setVisible(true);
        txtInfo.setFont(fnt);
        txtLog.setVisible(true);
        txtLog.setForeground(Color.white);
        txtLog.setFont(fnt);

        conf.setFont(fnt);
        conf.setForeground(Color.white);
        conf.setPreferredSize(new Dimension(150,100));
        conf.setBackground(Color.BLACK);
        conf.setBorder(null);

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario =  new Usuario(NmUsr.getText(), psswrd.getText());
                usuario = modelLogin.login(usuario);
                if(usuario != null)
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
        intPn.add(NmUsr);
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

