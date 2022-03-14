package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class bronTabController {
    private final ObservableList<Bron> bronData = FXCollections.observableArrayList();

    @FXML
    private TableView<Bron> bronTable;

    @FXML
    private TableColumn<Bron, String> number;

    @FXML
    private TableColumn<Bron, String> client;

    @FXML
    private Label numberLabel;

    @FXML
    private Label clientLabel;

    @FXML
    private Label dateLabel;



    @FXML
    private Button del;


    @FXML
    void initialize() {
        String ar;
        String n;
        String p;

        int dl;

        BufferedReader br = null;
        try {
            File file = new File("бронь.txt");
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new FileReader(file));
            String stt;
            while ((stt = br.readLine()) != null) {
                dl = stt.length();
                int i = stt.indexOf(" ");
                ar = stt.substring(0, i);
                stt = stt.substring(i + 1, dl);

                dl = stt.length();
                i = stt.indexOf(" ");
                n = stt.substring(0, i);
                p=stt.substring(i+1,i+11);

                    bronData.add(new Bron(ar,n,p));
                    number.setCellValueFactory(new PropertyValueFactory<>("number"));
                    client.setCellValueFactory(new PropertyValueFactory<>("client"));
                    bronTable.setItems(bronData);


            }
        }
        catch (IOException e){
            System.out.println("Ошибка при создании или открывании файла");
        }

        showProductDetails(null);

        bronTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));

    }

    private void showProductDetails(Bron bron){
        bron = bronTable.getSelectionModel().getSelectedItem();
        if (bron!=null){
            numberLabel.setText(bron.getNumber());
            clientLabel.setText(bron.getClient());
            dateLabel.setText(bron.getDate());
        }
        else {
            numberLabel.setText("");
            clientLabel.setText("");
            dateLabel.setText("");
        }
    }
    @FXML
    private void handleDeleteProduct(){
        int selectedIndex=bronTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bronTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран элемент");
            alert.setContentText("Выберите элемент в таблице");

            alert.showAndWait();
        }
        ObservableList<Bron> str = bronTable.getItems();
        Bron.DelF(str);
    }



}