package org.example.controller;

public class Admin extends Usuario{
    public static final String SENHA = "adm";

    public Admin(String nome, String senha)
    {
        super(nome, senha);
    }
    public Admin(String nome, int Score)
    {
        super(nome, Score);
    }

    public Admin(Usuario user)
    {
        super(user.getNome(), user.getSenha());
    }
    public static void EditarPerso(Personagem perso)
    {

    }
}
