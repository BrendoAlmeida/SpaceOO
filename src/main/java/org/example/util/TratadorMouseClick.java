package org.example.util;

import org.example.controller.Admin;
import org.example.controller.Usuario;
import org.example.model.modelUsuario;
import org.example.view.Fase1;
import org.example.view.FramePrincipal;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
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

        boolean nomeEhValido;
        boolean senhaEhValida;

        if(Click != null)
        {
            Click.setFramePosition(0);
            Click.start();
        }
        if(vef && (NmUsr != null && psswrd !=null) )
        {
            String res = AvaliadorSenha.AvaliarSenha(psswrd.getText());
            if(NmUsr.getText().isEmpty() || NmUsr.getText().matches(".*[\\s].*"))
            {
                NmUsr.setBorder(new LineBorder(Color.red,2));
                nomeEhValido = false;
            }
            else
            {
                NmUsr.setBorder(new LineBorder(Color.green,2));
                txtLog.setText(txtLog.getText().replace("<p style ='color:red'>Nome não pode ficar vazio</p>",""));
                nomeEhValido = true;
            }

            if(!res.equals("Senha adequada"))
            {
                if(nomeEhValido)
                    txtLog.setText("<html>"+AvaliadorSenha.AvaliarSenha(psswrd.getText())+"</html>");
                else
                    txtLog.setText("<html><p style ='color:red'>Nome não pode ficar vazio, ou conter espaços em branco</p>"+AvaliadorSenha.AvaliarSenha(psswrd.getText())+"</html>");

                psswrd.setBorder(new LineBorder(Color.red,2));
                senhaEhValida = false;
            }
            else
            {
                if(!nomeEhValido)
                    txtLog.setText("<html><p style ='color:red'>Nome não pode ficar vazio, ou conter espaços em branco</p></html>");

                psswrd.setBorder(new LineBorder(Color.green,2));
                txtLog.setText(txtLog.getText().replace(res,AvaliadorSenha.AvaliarSenha(psswrd.getText())));
                senhaEhValida = true;
            }
            if(senhaEhValida && nomeEhValido)
            {

                Usuario usuario;
                usuario = new Usuario(NmUsr.getText(),psswrd.getText());
                usuario.setId(modelUsuario.numUsers);
                modelUsuario.numUsers+=1;

                if(!modelUsuario.JaExiste(usuario))
                    if(usuario.cadastrar())
                    {
                        UsuarioJogando.setUserJog(usuario);
                        FramePrincipal.CarregarPag(tela);
                    }
                    else
                        txtLog.setText("ERRO");
                else
                {
                    NmUsr.setBorder(new LineBorder(Color.red,2));
                    txtLog.setText("Já existe um Jogador cadastrado com esse nome");
                }

            }
        }
        else if(ehF)
        {
            Fase1.MudarMusica();
            FramePrincipal.AddPag(new Fase1(),tela);
            FramePrincipal.CarregarPag(tela);
            Fase1.MudarMusica();
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

