package com.carbonzero.databaseconnection.impl.users;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DatabaseConnectionClass {

    public Connection con = null;
    public Statement stat = null;

    public void createConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/konsorsiyum","root","");
            stat = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
