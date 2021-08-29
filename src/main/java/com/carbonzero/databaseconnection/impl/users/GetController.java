package com.carbonzero.databaseconnection.impl.users;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class GetController extends DatabaseConnectionClass {

    @CrossOrigin
    @GetMapping("/logincheck")
    public boolean loginCheck(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "password") String password
    ){
        String passwordFromDatabase = null;
        try {
            createConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT DISTINCT password FROM users WHERE id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            passwordFromDatabase = resultSet.getString(1);
        } catch (SQLException error){
                error.printStackTrace();
        } finally {
            closeConnection();
        }
        if (passwordFromDatabase.equals(password)){
            return true;
        }
        return false;
    }

}