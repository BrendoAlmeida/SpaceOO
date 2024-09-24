package org.example.controller;

import org.example.model.modelUsuario;

public class Usuario {
    private int id = -1;
    private String nome;
    private String senha;
    private int Score;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario()
    {

    }

    public boolean login(){
        Usuario usuario = modelUsuario.login(this);

        if (usuario == null) return false;

        this.id = usuario.getId();
        this.Score = usuario.getScore();

        return true;
    }

    public boolean cadastrar(){
        return modelUsuario.cadastrar(this);
    }

    public String getNome(){
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
        modelUsuario.atualizar(this);
    }

    public void setSenha(String senha) {
        this.senha = senha;
        modelUsuario.atualizar(this);
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
        modelUsuario.atualizar(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Nome: "+this.getNome()+"                              Score:"+ this.getScore();
    }
}
