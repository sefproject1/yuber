package com.example.yuber.controllers;

import com.example.yuber.Main;
import com.example.yuber.models.UserModel;
import com.example.yuber.models.UserSession;
import com.example.yuber.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        // check if the user exists in the file
        UserModel user = UserService.checkInfo(username.getText(), password.getText());
        if (user != null) {
            // save the user to the UserSession singleton
            UserSession.getInstance().setUser(user);

            try {
                String view = "";
                if(UserSession.getUser().getRole() == "customer")
                    view = "customer-view.fxml";
                else view = "driver-view.fxml";

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(view));
                Parent root = (Parent) fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Order a ride - Yuber");

                Scene scene = new Scene(root, 1100, 800);
                scene.getStylesheets().add(Main.class.getResource("css/styles.css").toExternalForm());
                stage.setScene(scene);

                stage.show();

                // close current window
                Stage currStage = (Stage)error.getScene().getWindow();
                currStage.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // if not, display an error
        else error.setText("Invalid username and/or password.");
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) {
        // go to register page
    }
}