package org.example.util;

import org.example.controller.Usuario;
import org.example.model.modelUsuario;

import javax.swing.*;
import java.awt.*;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class ListRend extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        JLabel cel = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if(value instanceof Usuario)
        {
            Usuario user = (Usuario) value;

            if(modelUsuario.getIsAdm(user))
                cel.setBackground(Color.BLUE);
            else
                cel.setBackground(new Color(20, 31, 20));
        }

        if(isSelected)
            cel.setBackground(list.getSelectionBackground());

        return cel;
    }
}
