package com.example.javafxsql;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.Objects;


public class Registration {
    @FXML
    RadioButton maleBtn, femaleBtn;
    @FXML
    TextField cityTF, emailTF, passwordTF, loginTF, surnameTF, nameTF, genderTF, dateofbirthTF;
    @FXML
    Button regBtn;


    @FXML
    public void signUpClick (ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Stage Login  = (Stage) regBtn.getScene() .getWindow();
        Login.setResizable(false);
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Login.setScene(new Scene(parent, 600, 400));
        signUpUser();
    }

    public void signUpUser() throws SQLException, ClassNotFoundException {

        DatabaseHandler handler = new DatabaseHandler();

        String name = nameTF.getText();
        String surname = surnameTF.getText();
        String username = loginTF.getText();
        String password = passwordTF.getText();
        String email = emailTF.getText();
        String city = cityTF.getText();
        String dateofbirth = dateofbirthTF.getText();
        String gender = genderTF.getText();

        User user = new User(name, surname, username, password, email, city, dateofbirth, gender);

        handler.signUpUser(user);

    }


}
