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

// Personagem: Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro
public class modelPersonagem {
    private static final Connection con = connection.con;

    public static List<Personagem> getPersonagens(){
        String sql = "SELECT Jogador.tamanhoX AS jogadorTamanhoX, Jogador.tamanhoY AS jogadorTamanhoY, Jogador.vida, Jogador.velocidade, Jogador.delayTiro, Tiros.tamanhoX AS tiroTamanhoX, Tiros.tamanhoY AS tiroTamanhoY, Tiros.velocidade AS tiroVelocidade, Tiros.dano AS tiroDano, Tiros.direcaoX AS tiroDirecaoX, Tiros.direcaoY AS tiroDirecaoY, Tiros.tiroInimigo AS tiroInimigo, Tiros.isPersegue FROM Jogador INNER JOIN Tiros ON Jogador.idTiro = Tiros.id";
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
                Personagem personagem = new Personagem(new Dimension(rs.getInt("jogadorTamanhoX"), rs.getInt("jogadorTamanhoY")), tiro, rs.getInt("vida"), rs.getInt("velocidade"), rs.getInt("delayTiro"));
                personagens.add(personagem);
            }
            return personagens;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}