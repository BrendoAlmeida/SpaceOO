package org.example.config.db;
import java.sql.Connection;
import java.sql.DriverManager;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class connection {
    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:dbSpaceOO.db");
        }catch (Exception e){
            e.printStackTrace();
            con = null;
        }
    }
}
