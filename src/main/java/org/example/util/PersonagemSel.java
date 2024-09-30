package org.example.util;

import org.example.controller.Personagem;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class PersonagemSel
{
    private static int Psel;
    private static Personagem OBPsel;

    public static  int getPsel() {
        return Psel;
    }

    public static void setPsel(int psel) {
        Psel = psel;
    }

    public static Personagem getOBPsel() {
        return OBPsel;
    }

    public static void setOBPsel(Personagem OBPsel) {
        PersonagemSel.OBPsel = OBPsel;
    }
}
