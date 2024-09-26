package org.example.controller;

public class Admin extends Usuario{
    public Admin(String nome, String senha, int Id)
    {
        super(nome, senha, Id);
    }

    public Admin(String nome, int Score, int Id)
    {
        super(nome, Score, Id);
    }
}
