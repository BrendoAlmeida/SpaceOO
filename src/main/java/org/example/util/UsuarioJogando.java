package org.example.util;

import org.example.controller.Usuario;

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
