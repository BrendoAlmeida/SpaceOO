package org.example.config.db;
import java.sql.Connection;
import java.sql.DriverManager;

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
