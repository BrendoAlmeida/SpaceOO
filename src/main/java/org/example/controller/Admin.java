package org.example.controller;

public class Admin extends Usuario{
    public Admin(String nome, String senha)
    {
        super(nome, senha);
    }

    public Admin(String nome, int Score)
    {
        super(nome, Score);
    }
}
