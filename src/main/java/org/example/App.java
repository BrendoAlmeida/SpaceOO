package org.example;

import org.example.controller.Usuario;
import org.example.model.modelLogin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Usuario usuario = new Usuario("admin", "admin");
        modelLogin model = new modelLogin();
        usuario = model.login(usuario);
        if (usuario != null) {
            System.out.println("Usuário logado com sucesso!");
        } else {
            System.out.println("Usuário ou senha inválidos!");
        }
    }
}
