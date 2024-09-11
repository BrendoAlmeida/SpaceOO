package org.example.view;

import javax.swing.*;

public class FramePrincipal extends JFrame
{
    TelaLoginPn pn = new TelaLoginPn();

    public FramePrincipal()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pn);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
