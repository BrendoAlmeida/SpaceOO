package org.example.view;
import org.example.controller.Usuario;
import org.example.model.modelLogin;

import javax.swing.*;
import java.awt.*;

public class TelaLoginPn extends JPanel
{

    final int LARGURA = 1000;
    final int ALTURA = 800;
    //"<html>Welcome, Admin <br/>to Car Tooner"
    CustomFontLoader fntLoader = new CustomFontLoader();
    Font font = fntLoader.loadCustomFont();
    JLabel txtInfo = new JLabel("<html>Insira seus dados de login:<br/>");
    JLabel txtLog = new JLabel("TESTE");


    Usuario usuario = new Usuario("admin", "admin");

    TelaLoginPn()
    {
        this.add(txtInfo);
        usuario = modelLogin.login(usuario);
        if(usuario != null) {
            txtLog.setText("Usuário logado com sucesso!");
        } else {
            txtLog.setText("Usuário ou senha inválidos!");
        }
        this.setPreferredSize(new Dimension(LARGURA,ALTURA));
        this.setBackground(Color.black);
        txtLog.setVisible(true);
        txtLog.setFont(font);

        this.add(txtLog);
        this.setVisible(true);
    }
    public void paint(Graphics  g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}

