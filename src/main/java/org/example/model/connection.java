package org.example.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connection {
    public Statement connection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            return connection.createStatement();
        }catch (Exception e){
            return null;
        }
    }
}
