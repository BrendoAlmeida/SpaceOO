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

// Inimigo: Dimension tamanho, Tiro tiro, int vida, int velocidade, int delayTiro
public class modelInimigo {
    private static final Connection con = connection.con;

    public static List<Inimigo> getInimigos(){
        String sql = "SELECT Inimigos.TamanhoX as inimigoTamanhoX, Inimigos.TamanhoY as inimigoTamanhoY, Inimigos.Vida as vida, Inimigos.Velocidade as velocidade, Inimigos.DelayTiro as delayTiro, Tiros.TamanhoX as tiroTamanhoX, Tiros.TamanhoY as tiroTamanhoY, Tiros.Velocidade as tiroVelocidade, Tiros.Dano as tiroDano, Tiros.DirecaoX as tiroDirecaoX, Tiros.DirecaoY as tiroDirecaoY, Tiros.isPersegue as isPersegue, Tiros.tiroInimigo as tiroInimigo FROM Inimigos INNER JOIN Tiros ON Inimigos.idTiro = Tiros.Id";
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
                Inimigo inimigo = new Inimigo(new Dimension(rs.getInt("inimigoTamanhoX"), rs.getInt("inimigoTamanhoY")), tiro, rs.getInt("vida"), rs.getInt("velocidade"), rs.getInt("delayTiro"));
                inimigos.add(inimigo);
            }
            return inimigos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
