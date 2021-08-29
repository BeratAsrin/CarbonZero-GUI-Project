package com.carbonzero.databaseconnection.impl.users;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController

public class PostController extends DatabaseConnectionClass{

    @CrossOrigin
    @PostMapping("/postuser")

    public void postUser(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "company") String company,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email
    ){
        createConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users(id,name,surname,company,password,email) VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1,Integer.parseInt(id));
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,surname);
            preparedStatement.setString(4,company);
            preparedStatement.setString(5,password);
            preparedStatement.setString(6,email);
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException error){
            error.printStackTrace();
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
