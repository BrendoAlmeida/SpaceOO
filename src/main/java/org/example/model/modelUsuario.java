package org.example.model;

import org.example.config.db.connection;
import org.example.controller.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class modelUsuario {
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

    public static boolean cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, senha) VALUES (?, ?)";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletar(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, score = ? WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getScore());
            stmt.setInt(4, usuario.getId());
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuario";
        PreparedStatement stmt;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("senha"));
                usuario.setId(rs.getInt("id"));
                usuario.setScore(rs.getInt("score"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}