package org.example.view;

import org.example.controller.Usuario;
import org.example.util.CarregadorFonte;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaLog extends JPanel
{
    private final JList<Usuario> listUsers = new JList<>();
    private final JButton selUsr = new JButton("Entrar");
    private final JButton adm = new JButton("Entrar como Admin");
    private final JButton del = new JButton("Excluir");
    private final JButton edit = new JButton("Editar");
    private final JPanel btnCont = new JPanel();
    private final JLabel txtInfo = new JLabel("Jogadores Cadastrados:");
    private final Font fnt = CarregadorFonte.CarregaFonte("fonts/FT14.ttf");
    private final Font fnt2= CarregadorFonte.CarregaFonte("fonts/FT16.ttf");

    public TelaLog()
    {
        this.setPreferredSize(new Dimension(1000,800));
        //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

        btnCont.setSize(new Dimension(1000,800));
        btnCont.setLayout(new BoxLayout(btnCont,BoxLayout.X_AXIS));
        btnCont.setBackground(Color.BLACK);
        btnCont.setBorder(new EmptyBorder(0,0,0,0));
        btnCont.add(selUsr);
        btnCont.add(edit);
        btnCont.add(del);
        btnCont.add(adm);
        btnCont.setVisible(true);

        selUsr.setBackground(Color.BLACK);
        selUsr.setSize(new Dimension(250, 200));
        selUsr.setForeground(Color.white);
        selUsr.setFont(fnt);
        selUsr.setVisible(true);

        edit.setBackground(Color.BLACK);
        edit.setSize(new Dimension(250, 200));
        edit.setForeground(Color.white);
        edit.setFont(fnt);
        edit.setVisible(true);

        del.setBackground(Color.BLACK);
        del.setSize(new Dimension(250, 200));
        del.setForeground(Color.white);
        del.setFont(fnt);
        del.setVisible(true);

        adm.setBackground(Color.BLACK);
        adm.setSize(new Dimension(250, 200));
        adm.setForeground(Color.white);
        adm.setFont(fnt);
        adm.setVisible(true);

        txtInfo.setForeground(Color.white);
        txtInfo.setSize(new Dimension(200,200));
        txtInfo.setFont(fnt2);
        txtInfo.setVisible(true);

        listUsers.setVisible(true);

        this.add(btnCont);
        this.add(txtInfo);
        this.add(listUsers);
    }
}
