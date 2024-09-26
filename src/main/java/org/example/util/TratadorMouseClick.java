package org.example.util;

import org.example.controller.Admin;
import org.example.controller.Usuario;
import org.example.model.modelUsuario;
import org.example.view.Fase1;
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
    boolean ehF;
    boolean vef;
    public TratadorMouseClick(Clip clc, Clip hv, JLabel tL, JTextField nms, JTextField pswd,boolean v,boolean ehFase,String tla)
    {
        Click = clc;
        Hov = hv;
        txtLog = tL;
        NmUsr = nms;
        psswrd = pswd;
        tela = tla;
        vef = v;
        ehF = ehFase;
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
            if(modelUsuario.getUsuarios().isEmpty())
            {
                System.out.println("ENTROU");
                Admin adm;
                adm = new Admin(NmUsr.getText(), psswrd.getText(),modelUsuario.numUsers);

                if(adm.cadastrar())
                {
                    modelUsuario.atualizar(adm);
                    FramePrincipal.CarregarPag(tela);
                }
                else
                    txtLog.setText("ERRO");
            }
            else
            {
                System.out.println("ENTROU 2");
                Usuario usuario;
                usuario = new Usuario(NmUsr.getText(), psswrd.getText(),modelUsuario.numUsers);
                modelUsuario.numUsers+=1;

                if(usuario.cadastrar())
                {
                    modelUsuario.atualizar(usuario);
                    FramePrincipal.CarregarPag(tela);
                }
                else
                    txtLog.setText("ERRO");
            }
        }
        else if(ehF)
        {
            Fase1.initFase1();
            FramePrincipal.CarregarPag(tela);
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

