/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class_Files;

import java.sql.*;

/**
 *
 * @author Ishan Darshana
 */
public class Database_Config {

    public Statement statement = null;
    public Connection connection = null;
    
    public Database_Config(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/investment", "root", "Srilanka123!@#");
            statement = (Statement) connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

    public Statement getStatement() {
        return statement;
    }
    public Connection getConnection() {
        return connection;
    }

       


}
