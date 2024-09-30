package org.example.util;

import org.example.controller.Usuario;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class UsuarioJogando
{
    private static Usuario UserJog;

    public static Usuario getUserJog() {
        return UserJog;
    }

    public static void setUserJog(Usuario userJog) {
        UserJog = userJog;
    }
}
