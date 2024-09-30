package org.example.controller;

import org.example.model.modelUsuario;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class Usuario {
    private int id = -1;
    private String nome;
    private String senha;
    private int Score;
    private boolean isAdmin;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String nome, int Score)
    {
        this.nome = nome;
        this.Score = Score;
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

    //
    public void setIsAdmin()
    {
        this.isAdmin = true;
        modelUsuario.updateIsAdm(this);
    }
    public boolean getIsAdmin()
    {
        return this.isAdmin;
    }
    public void setSenha(String senha) {
        this.senha = senha;
        modelUsuario.atualizar(this);
    }
    //

    public void setSenhaLocal(String senha) {
        this.senha = senha;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        if (this.getScore() > score) return;

        Score = score;
        modelUsuario.atualizar(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        modelUsuario.atualizar(this);
    }

    @Override
    public String toString()
    {
        return "Nome: "+this.getNome()+format()+"Score:"+ modelUsuario.getScrBD(this);
    }
     private String format()
     {
         int lNome = this.getNome().length() + 6;
         int sSize = (this.getScore()+"").length()+6;
         String res = "";

         for(int i=0; i<75- lNome - sSize;i++)
             res= res+" ";

         return res;
     }
}
