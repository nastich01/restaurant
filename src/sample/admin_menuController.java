package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class admin_menuController {
    @FXML
    private Tab dishtab;
    @FXML
    private Tab zakaztab;
    @FXML
    private Tab tabletab;
    @FXML
    private Tab tabletab1;
    @FXML
    private TabPane tabPane;


    @FXML
    void initialize() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("dishTab.fxml"));
        dishtab.setContent(loader.load());

    }
    @FXML
    void initializeBron() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("bronTab.fxml"));
        tabletab.setContent(loader.load());

    }

    @FXML
    void initializeZakaz() throws IOException {
        FXMLLoader loader2=new FXMLLoader();
        loader2.setLocation(getClass().getResource("zakazTab.fxml"));
        zakaztab.setContent(loader2.load());

    }

    @FXML
    void initializeTable() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("tableTab.fxml"));
        tabletab1.setContent(loader.load());

    }


}
