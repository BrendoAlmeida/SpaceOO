package org.example;

import org.example.controller.Usuario;
import org.example.model.modelLogin;
import org.example.view.FramePrincipal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Usuario usuario = new Usuario("admin", "admin");
        usuario = modelLogin.login(usuario);
        if (usuario != null) {
            System.out.println("Usuário logado com sucesso!");
        } else {
            System.out.println("Usuário ou senha inválidos!");
        }


        FramePrincipal frame = new FramePrincipal();


    }
}
