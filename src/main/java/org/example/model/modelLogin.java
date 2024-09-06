package org.example.model;

import org.example.config.db.connection;
import org.example.controller.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class modelLogin {
    private static final Connection con = connection.con;

    public static Usuario login(Usuario usuario) {
        String sql = "SELECT * FROM usuario where nome = ? and senha = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                usuario = null;
            }
        } catch (Exception e) {
            usuario = null;
        }
        return usuario;
    }
}