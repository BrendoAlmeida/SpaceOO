package org.example.model;

import org.example.config.db.connection;
import org.example.controller.Usuario;

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
public class modelUsuario  {
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
            stmt.executeUpdate();

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
            int lA = stmt.executeUpdate();


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, score = ?, isAdmin = ? WHERE id = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getScore());
            stmt.setBoolean(4, usuario.getIsAdmin());
            stmt.setInt(5, usuario.getId());
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletarPorNome(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE nome = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            int lA = stmt.executeUpdate();


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateScore(Usuario usuario)
    {
        String sql = "UPDATE usuario SET score = ? WHERE nome = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuario.getScore());
            stmt.setString(2, usuario.getNome());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getScrBD(Usuario usuario)
    {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        int res;
        try(PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setString(1,usuario.getNome());
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
                res = rs.getInt("score");
            else
            {
                res = -2;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            res= -1;
        }
        return res;
    }
    public static void updateIsAdm(Usuario usuario)
    {
        String sql = "UPDATE usuario SET isAdmin = ? WHERE nome = ?";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, usuario.getIsAdmin());
            stmt.setString(2, usuario.getNome());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getIsAdm(Usuario usuario)
    {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        boolean res;
        try(PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setString(1,usuario.getNome());
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
                res = rs.getBoolean("isAdmin");
            else
            {
                res = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            res= false;
        }

        return res;
    }

    public static List<Usuario> getUsuarios()
    {
        String sql = "SELECT * FROM usuario";
        PreparedStatement stmt;
        List<Usuario> Usuarios = new ArrayList<>();
        try{
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Usuario user = new Usuario(rs.getString("nome"),rs.getInt("score"));
                Usuarios.add(user);
            }
        }
        catch(Exception e)
        {
            System.out.println("ERRO");
            e.printStackTrace();
            Usuarios = null;
        }
        return Usuarios;
    }

    public static boolean deletarTodos()
    {
        String sql = "DELETE FROM usuario";
        PreparedStatement stmt;
        try{
            stmt = con.prepareStatement(sql);
            int linhasAf = stmt.executeUpdate();

            return linhasAf > 0;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean JaExiste(Usuario usuario)
    {//Não permite que exista mais de um usuário com o mesmo nome cadastrado no sistema
        String sql = "SELECT * FROM usuario where nome = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setString(1,usuario.getNome());
            ResultSet rs = stmt.executeQuery();
            return rs.next();//retorna se existe pelo menos um usuario com o mesmo nome
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}