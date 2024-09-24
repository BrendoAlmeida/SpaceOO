package org.example.util;

import org.example.controller.Usuario;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class TratadorMouseHover implements MouseListener {
    Clip Click;
    Clip Hov;
    JLabel txtLog;
    JTextField NmUsr;
    JTextField psswrd;

    public TratadorMouseHover(Clip clc, Clip hv, JLabel tL, JTextField nms, JTextField pswd)
    {
        Click = clc;
        Hov = hv;
        txtLog = tL;
        NmUsr = nms;
        psswrd = pswd;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(Click != null)
        {
            Click.setFramePosition(0);
            Click.start();
        }
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
