package org.example.util;

import org.example.controller.Personagem;

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
