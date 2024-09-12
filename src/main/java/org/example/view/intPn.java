package org.example.view;

import org.example.controller.Usuario;
import org.example.util.CarregadorFonte;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class intPn extends JPanel
{
    private final JLabel txtInfo = new JLabel("Insira seus dados de login:");
    private final JLabel txtNome = new JLabel("Nome:");
    private final JLabel txtSenha = new JLabel("Senha:");
    private final JLabel txtLog = new JLabel("");
    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf");
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/MachineStd-Medium.otf");
    private final JButton conf = new JButton("Confirmar");
    private final JTextField NmUsr = new JTextField();
    private final JTextField psswrd= new JTextField();

    Usuario usuario;
    private final intPn PAINEL = this;
    public intPn()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000,300));
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

        conf.setFont(fnt);
        conf.setForeground(Color.white);
        conf.setPreferredSize(new Dimension(150,100));
        conf.setBackground(Color.BLACK);

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario =  new Usuario(NmUsr.getText(), psswrd.getText());
                if(usuario.login())
                {
                    txtLog.setEnabled(true);
                    txtLog.setText("Usuário logado com sucesso!");
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

        this.add(txtInfo);
        this.add(txtNome);
        this.add(NmUsr);
        this.add(txtSenha);
        this.add(psswrd);
        this.add(conf);
        this.setVisible(true);
    }
}
