package org.example.model;
import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:dbSpaceOO.db");
        }catch (Exception e){
            con = null;
        }
    }
}
