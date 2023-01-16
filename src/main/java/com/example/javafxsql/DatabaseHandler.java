package com.example.javafxsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Config {
    Connection Connection;



    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + name;
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection = DriverManager.getConnection(connectionString, user, password);

        return Connection;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Constants.USER_TABLE +  "(" + Constants.NAME + "," + Constants.SURNAME + ","
                + Constants.USERNAME + "," + Constants.PASSWORD + "," + Constants.EMAIL + "," + Constants.CITY + ","
                + Constants.DATEOFBIRTH + "," + Constants.GENDER + ")" + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);

            prSt.setString(1, user.getName());
            prSt.setString(2, user.getSurname());
            prSt.setString(3, user.getUsername());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getEmail());
            prSt.setString(6, user.getCity());
            prSt.setString(7, user.getDateofbirth());
            prSt.setString(8, user.getGender());

            prSt.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public ResultSet getUser (User user){
        ResultSet rs = null;

        String select = "SELECT * FROM " + Constants.USER_TABLE + " WHERE " +
                Constants.USERNAME + "=? AND " + Constants.PASSWORD + "=?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(select);

            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());


            rs = prSt.executeQuery();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return rs;
    }
}
