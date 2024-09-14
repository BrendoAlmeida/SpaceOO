package org.example.view;

import org.example.controller.Usuario;
import org.example.util.CarregadorFonte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCad extends JPanel
{
    private final JLabel txtInfo = new JLabel("Insira seus dados de login:");
    private final JLabel txtNome = new JLabel("Nome:");
    private final JLabel txtSenha = new JLabel("Senha:");
    private final JLabel txtLog = new JLabel("");
    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/space_invaders.ttf");
    private final Font fnt2 = CarregadorFonte.CarregaFonte("fonts/FT16.ttf");
    private final JButton conf = new JButton("Confirmar");
    private final JTextField NmUsr = new JTextField();
    private final JTextField psswrd= new JTextField();
    Usuario usuario;
    public TelaCad()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000,300));
        this.setBackground(Color.black);

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

        NmUsr.setMaximumSize(new Dimension(2000,100));
        psswrd.setMaximumSize(new Dimension(2000,100));
        NmUsr.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                NmUsr.setBackground(Color.white);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                NmUsr.setBackground(Color.black);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                NmUsr.setBackground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                NmUsr.setBackground(Color.black);
            }
        });
        psswrd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                psswrd.setBackground(Color.white);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                psswrd.setBackground(Color.black);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                psswrd.setBackground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                psswrd.setBackground(Color.black);
            }
        });

        conf.setFont(fnt);
        conf.setForeground(Color.white);
        conf.setPreferredSize(new Dimension(150,100));
        conf.setBackground(Color.BLACK);

        conf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario =  new Usuario(NmUsr.getText(), psswrd.getText());
                if(usuario.login())
                    FramePrincipal.CarregarPag("SelPerso");
                else
                    txtLog.setText("Usuário ou senha inválidos!");
            }
        });

        NmUsr.setPreferredSize(new Dimension(600,100));
        NmUsr.setBackground(Color.BLACK);
        NmUsr.setFont(fnt2.deriveFont(30f));
        NmUsr.setForeground(Color.getColor("#d5ebdb"));
        NmUsr.setVisible(true);

        psswrd.setPreferredSize(new Dimension(600,100));
        psswrd.setFont(fnt2.deriveFont(30f));
        psswrd.setBackground(Color.BLACK);
        psswrd.setForeground(Color.getColor("#d5ebdb"));
        psswrd.setVisible(true);

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
