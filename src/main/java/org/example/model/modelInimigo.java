package org.example.model;

import org.example.config.db.connection;
import org.example.controller.Inimigo;
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
// Inimigo: Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro
public class modelInimigo {
    private static final Connection con = connection.con;

    public static List<Inimigo> getInimigos(){
        String sql = "SELECT Inimigos.sprite AS sprite, Inimigos.id AS id, Inimigos.TamanhoX as inimigoTamanhoX, Inimigos.TamanhoY as inimigoTamanhoY, Inimigos.Vida as vida, Inimigos.Velocidade as velocidade, Inimigos.DelayTiro as delayTiro, Tiros.TamanhoX as tiroTamanhoX, Tiros.TamanhoY as tiroTamanhoY, Tiros.Velocidade as tiroVelocidade, Tiros.Dano as tiroDano, Tiros.DirecaoX as tiroDirecaoX, Tiros.DirecaoY as tiroDirecaoY, Tiros.isPersegue as isPersegue, Tiros.tiroInimigo as tiroInimigo FROM Inimigos INNER JOIN Tiros ON Inimigos.idTiro = Tiros.Id";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Inimigo> inimigos = new ArrayList<>();
            while (rs.next()) {
                Tiro tiro;
                if (rs.getBoolean("isPersegue")) {
                    tiro = new TiroPersegue(new Dimension(rs.getInt("tiroTamanhoX"), rs.getInt("tiroTamanhoY")), rs.getInt("tiroVelocidade"), rs.getInt("tiroDano"), new int[]{rs.getInt("tiroDirecaoX"), rs.getInt("tiroDirecaoY")}, rs.getBoolean("tiroInimigo"));
                }else{
                    tiro = new Tiro(new Dimension(rs.getInt("tiroTamanhoX"), rs.getInt("tiroTamanhoY")), rs.getInt("tiroVelocidade"), rs.getInt("tiroDano"), new int[]{rs.getInt("tiroDirecaoX"), rs.getInt("tiroDirecaoY")}, rs.getBoolean("tiroInimigo"));
                }
                Inimigo inimigo = new Inimigo(new Dimension(rs.getInt("inimigoTamanhoX"), rs.getInt("inimigoTamanhoY")), tiro, rs.getInt("vida"), rs.getInt("velocidade"), rs.getInt("delayTiro"), rs.getString("sprite"));
                inimigo.setId(rs.getInt("id"));
                inimigos.add(inimigo);
            }
            return inimigos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Inimigo getInimigo(int id){
        String sql = "SELECT Inimigos.sprite AS sprite, Inimigos.id AS id, Inimigos.TamanhoX as inimigoTamanhoX, Inimigos.TamanhoY as inimigoTamanhoY, Inimigos.Vida as vida, Inimigos.Velocidade as velocidade, Inimigos.DelayTiro as delayTiro, Tiros.TamanhoX as tiroTamanhoX, Tiros.TamanhoY as tiroTamanhoY, Tiros.Velocidade as tiroVelocidade, Tiros.Dano as tiroDano, Tiros.DirecaoX as tiroDirecaoX, Tiros.DirecaoY as tiroDirecaoY, Tiros.isPersegue as isPersegue, Tiros.tiroInimigo as tiroInimigo FROM Inimigos INNER JOIN Tiros ON Inimigos.idTiro = Tiros.Id WHERE Inimigos.id = ?";
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
                Inimigo inimigo = new Inimigo(new Dimension(rs.getInt("inimigoTamanhoX"), rs.getInt("inimigoTamanhoY")), tiro, rs.getInt("vida"), rs.getInt("velocidade"), rs.getInt("delayTiro"), rs.getString("sprite"));
                inimigo.setId(rs.getInt("id"));
                return inimigo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(Inimigo inimigo){
        String sql = "UPDATE Inimigos SET TamanhoX = ?, TamanhoY = ?, Vida = ?, Velocidade = ?, DelayTiro = ?, idTiro = ? WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, inimigo.getTamanho().width);
            stmt.setInt(2, inimigo.getTamanho().height);
            stmt.setInt(3, inimigo.getVida());
            stmt.setInt(4, inimigo.getVelocidade());
            stmt.setInt(5, inimigo.getDelayTiro());
            stmt.setInt(6, inimigo.getTiro().getId());
            stmt.setInt(7, inimigo.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(Inimigo inimigo){
        String sql = "DELETE FROM Inimigos WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, inimigo.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(Inimigo inimigo){
        String sql = "INSERT INTO Inimigos (TamanhoX, TamanhoY, Vida, Velocidade, DelayTiro, idTiro) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, inimigo.getTamanho().width);
            stmt.setInt(2, inimigo.getTamanho().height);
            stmt.setInt(3, inimigo.getVida());
            stmt.setInt(4, inimigo.getVelocidade());
            stmt.setInt(5, inimigo.getDelayTiro());
            stmt.setInt(6, inimigo.getTiro().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
