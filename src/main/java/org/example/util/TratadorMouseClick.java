package org.example.util;

import org.example.controller.Usuario;
import org.example.view.FramePrincipal;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class TratadorMouseClick implements MouseListener {
    Clip Click;
    Clip Hov;
    JLabel txtLog;
    JTextField NmUsr;
    JTextField psswrd;
    String tela;
    boolean vef;
    public TratadorMouseClick(Clip clc, Clip hv, JLabel tL, JTextField nms, JTextField pswd,boolean v,String tla)
    {
        Click = clc;
        Hov = hv;
        txtLog = tL;
        NmUsr = nms;
        psswrd = pswd;
        tela = tla;
        vef = v;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        if(Click != null)
        {
            Click.setFramePosition(0);
            Click.start();
        }
        if(vef && (NmUsr != null && psswrd !=null) )
        {
            Usuario usuario;
            usuario =  new Usuario(NmUsr.getText(), psswrd.getText());
            if(usuario.login())
                FramePrincipal.CarregarPag(tela);
            else
                txtLog.setText("Usuário ou senha inválidos!");
        }
        else
            FramePrincipal.CarregarPag(tela);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(Hov != null)
        {
            Hov.setFramePosition(0);
            Hov.start();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(Hov != null)
            Hov.stop();

    }
}

