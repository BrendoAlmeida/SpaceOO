package org.example.model;

import org.example.config.db.connection;
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
public class modelTiro {
    private static final Connection con = connection.con;

    public static boolean deletarTiro(int id) {
        String sql = "DELETE FROM tiro WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Tiro> getTiro(){
        String sql = "SELECT * FROM Tiros";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Tiro> tiros = new ArrayList<>();
            while (rs.next()) {
                if (rs.getBoolean("isPersegue")) {
                    TiroPersegue tiroPersegue = new TiroPersegue(new Dimension(rs.getInt("tamanhoX"), rs.getInt("tamanhoY")), rs.getInt("velocidade"), rs.getInt("dano"), new int[]{rs.getInt("direcaoX"), rs.getInt("direcaoY")}, rs.getBoolean("tiroInimigo"));
                    tiros.add(tiroPersegue);
                    continue;
                }
                Tiro tiro = new Tiro(new Dimension(rs.getInt("tamanhoX"), rs.getInt("tamanhoY")), rs.getInt("velocidade"), rs.getInt("dano"), new int[]{rs.getInt("direcaoX"), rs.getInt("direcaoY")}, rs.getBoolean("tiroInimigo"));
                tiros.add(tiro);
            }
            return tiros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Tiro getTiro(int id){
        String sql = "SELECT * FROM Tiros WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("isPersegue")) {
                    return new TiroPersegue(new Dimension(rs.getInt("tamanhoX"), rs.getInt("tamanhoY")), rs.getInt("velocidade"), rs.getInt("dano"), new int[]{rs.getInt("direcaoX"), rs.getInt("direcaoY")}, rs.getBoolean("tiroInimigo"));
                }
                return new Tiro(new Dimension(rs.getInt("tamanhoX"), rs.getInt("tamanhoY")), rs.getInt("velocidade"), rs.getInt("dano"), new int[]{rs.getInt("direcaoX"), rs.getInt("direcaoY")}, rs.getBoolean("tiroInimigo"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
