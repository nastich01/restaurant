package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;

public class Main_scanController {
    @FXML
    private Button menu;
    @FXML
    private Button bron;
    @FXML
    private Button zakaz;
    
    @FXML
    void initialize() {
    menu.setOnAction(actionEvent -> {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("new_menu.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root,800,460));
        stage.showAndWait();
    });

        bron.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("reservation.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root,800,460));
            stage.showAndWait();
        });
        zakaz.setOnAction(actionEvent -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("OformZakaz.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root,800,460));
            stage.showAndWait();
        });
    }
}
