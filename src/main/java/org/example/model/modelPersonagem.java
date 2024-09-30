package org.example.model;


import org.example.config.db.connection;
import org.example.controller.Personagem;
import org.example.controller.Tiro;
import org.example.controller.TiroPersegue;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
// Personagem: Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro
public class modelPersonagem {
    private static final Connection con = connection.con;

    public static List<Personagem> getPersonagens(){
        String sql = "SELECT Jogador.sprite AS sprite, Jogador.id AS id, Jogador.tamanhoX AS jogadorTamanhoX, Jogador.tamanhoY AS jogadorTamanhoY, Jogador.vida, Jogador.velocidade, Jogador.delayTiro, Tiros.tamanhoX AS tiroTamanhoX, Tiros.tamanhoY AS tiroTamanhoY, Tiros.velocidade AS tiroVelocidade, Tiros.dano AS tiroDano, Tiros.direcaoX AS tiroDirecaoX, Tiros.direcaoY AS tiroDirecaoY, Tiros.tiroInimigo AS tiroInimigo, Tiros.isPersegue FROM Jogador INNER JOIN Tiros ON Jogador.idTiro = Tiros.id";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Personagem> personagens = new ArrayList<>();
            while (rs.next()) {
                Tiro tiro;
                if (rs.getBoolean("isPersegue")) {
                    tiro = new TiroPersegue(new Dimension(rs.getInt("tiroTamanhoX"), rs.getInt("tiroTamanhoY")), rs.getInt("tiroVelocidade"), rs.getInt("tiroDano"), new int[]{rs.getInt("tiroDirecaoX"), rs.getInt("tiroDirecaoY")}, rs.getBoolean("tiroInimigo"));
                }else{
                    tiro = new Tiro(new Dimension(rs.getInt("tiroTamanhoX"), rs.getInt("tiroTamanhoY")), rs.getInt("tiroVelocidade"), rs.getInt("tiroDano"), new int[]{rs.getInt("tiroDirecaoX"), rs.getInt("tiroDirecaoY")}, rs.getBoolean("tiroInimigo"));
                }
                Personagem personagem = new Personagem(new Dimension(rs.getInt("jogadorTamanhoX"), rs.getInt("jogadorTamanhoY")), tiro, rs.getInt("vida"), rs.getInt("velocidade"), rs.getInt("delayTiro"), rs.getString("sprite"));
                personagem.setId(rs.getInt("id"));
                personagens.add(personagem);
            }
            return personagens;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Personagem getPersonagem(int id){
        String sql = "SELECT Jogador.sprite AS sprite, Jogador.id AS id, Jogador.tamanhoX AS jogadorTamanhoX, Jogador.tamanhoY AS jogadorTamanhoY, Jogador.vida, Jogador.velocidade, Jogador.delayTiro, Tiros.tamanhoX AS tiroTamanhoX, Tiros.tamanhoY AS tiroTamanhoY, Tiros.velocidade AS tiroVelocidade, Tiros.dano AS tiroDano, Tiros.direcaoX AS tiroDirecaoX, Tiros.direcaoY AS tiroDirecaoY, Tiros.tiroInimigo AS tiroInimigo, Tiros.isPersegue FROM Jogador INNER JOIN Tiros ON Jogador.idTiro = Tiros.id WHERE Jogador.id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tiro tiro;
                if (rs.getBoolean("isPersegue")) {
                    tiro = new TiroPersegue(new Dimension(rs.getInt("tiroTamanhoX"), rs.getInt("tiroTamanhoY")), rs.getInt("tiroVelocidade"), rs.getInt("tiroDano"), new int[]{rs.getInt("tiroDirecaoX"), rs.getInt("tiroDirecaoY")}, rs.getBoolean("tiroInimigo"));
                }else{
                    tiro = new Tiro(new Dimension(rs.getInt("tiroTamanhoX"), rs.getInt("tiroTamanhoY")), rs.getInt("tiroVelocidade"), rs.getInt("tiroDano"), new int[]{rs.getInt("tiroDirecaoX"), rs.getInt("tiroDirecaoY")}, rs.getBoolean("tiroInimigo"));
                }
                Personagem personagem = new Personagem(new Dimension(rs.getInt("jogadorTamanhoX"), rs.getInt("jogadorTamanhoY")), tiro, rs.getInt("vida"), rs.getInt("velocidade"), rs.getInt("delayTiro"), rs.getString("sprite"));
                personagem.setId(rs.getInt("id"));
                return personagem;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void update(Personagem personagem){
        String sql = "UPDATE Jogador SET tamanhoX = ?, tamanhoY = ?, vida = ?, velocidade = ?, delayTiro = ?, idTiro = ? WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, personagem.getTamanho().width);
            stmt.setInt(2, personagem.getTamanho().height);
            stmt.setInt(3, personagem.getVida());
            stmt.setInt(4, personagem.getVelocidade());
            stmt.setInt(5, personagem.getDelayTiro());
            stmt.setInt(6, personagem.getTiro().getId());
            stmt.setInt(7, personagem.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(Personagem personagem){
        String sql = "DELETE FROM Jogador WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, personagem.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(Personagem personagem){
        String sql = "INSERT INTO Jogador (tamanhoX, tamanhoY, vida, velocidade, delayTiro, idTiro) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, personagem.getTamanho().width);
            stmt.setInt(2, personagem.getTamanho().height);
            stmt.setInt(3, personagem.getVida());
            stmt.setInt(4, personagem.getVelocidade());
            stmt.setInt(5, personagem.getDelayTiro());
            stmt.setInt(6, personagem.getTiro().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}