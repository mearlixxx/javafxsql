package com.example.javafxsql;

import Animation.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class HelloController {
    @FXML
    public Label welcomeText;
    @FXML
    public Button logInBtn, regBtn;
    @FXML
    public TextField logInTF;
    @FXML
    public PasswordField passwField;

    @FXML
    public void regBtnClick (ActionEvent event) throws IOException {
        Stage Registration  = (Stage) regBtn.getScene() .getWindow();
        Registration.setResizable(false);
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration.fxml")));
        Registration.setScene(new Scene(parent, 600, 400));
    }

    @FXML
    public void logInBtnClick (ActionEvent event) throws IOException, SQLException {
        String loginText = logInTF.getText().trim();
        String loginPassword = passwField.getText().trim();

        if (!loginText.equals("") && !loginPassword.equals("")) {
            loginUser(loginText, loginPassword);
        }
        else {
            System.out.println("error 404");
        }
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException, IOException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        dbHandler.getUser(user);
        ResultSet resultSet = dbHandler.getUser(user);
        int counter = 0;
        try {
            while (resultSet.next()) {
                counter++;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        if (counter >=1){
            loadSuccess();
        }
        else {
            Shake loginAnimation = new Shake(logInTF);
            Shake passwordAnimation = new Shake(passwField);
            loginAnimation.Play();
            passwordAnimation.Play();
            logInTF.setText(null);
            passwField.setText(null);
        }
    }
    public void loadSuccess () throws IOException {
        Stage Success  = (Stage) logInBtn.getScene() .getWindow();
        Success.setResizable(false);
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("success.fxml")));
        Success.setScene(new Scene(parent, 600, 400));
    }
}