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

public class zakazTabController {
    private final ObservableList<Purchase> allZakazData = FXCollections.observableArrayList();

    @FXML
    private TableView<Purchase> allZakazTable;

    @FXML
    private TableColumn<Purchase, String> zakazNumber;

    @FXML
    private TableColumn<Purchase, String> client;

    @FXML
    private TableColumn<Purchase, String> oplata;

    @FXML
    private Label numberLabel;

    @FXML
    private Label clientLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label oplataLabel;

    @FXML
    private Button del;


    @FXML
    void initialize() {
        String num;
        String cl;
        String d;
        String n;
        String o;

        int dl;

        BufferedReader br = null;
        try {
            File file = new File("заказы.txt");
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new FileReader(file));
            String stt;
            while ((stt = br.readLine()) != null) {
                dl = stt.length();
                int i = stt.indexOf(" ");
                num = stt.substring(0, i);
                stt = stt.substring(i + 1, dl);

                dl = stt.length();
                i = stt.indexOf(" ");
                cl = stt.substring(0, i);
                d=stt.substring(i+1,i+11);
                stt = stt.substring(i + 12, dl);

                dl = stt.length();
                i = stt.indexOf(" ");
                n = stt.substring(0, i);
                o = stt.substring(i+1, i+2);



                allZakazData.add(new Purchase(num,cl,d,n,o));
                zakazNumber.setCellValueFactory(new PropertyValueFactory<>("zakazNumber"));
                client.setCellValueFactory(new PropertyValueFactory<>("client"));
                oplata.setCellValueFactory(new PropertyValueFactory<>("oplata"));
                allZakazTable.setItems(allZakazData);


            }
        }
        catch (IOException e){
            System.out.println("Ошибка при создании или открывании файла");
        }

        showProductDetails(null);

        allZakazTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));

        del.setOnAction(actionEvent -> {
            int selectedIndex=allZakazTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                allZakazTable.getItems().remove(selectedIndex);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(null);
                alert.setTitle("Не выделено");
                alert.setHeaderText("Не выбран элемент");
                alert.setContentText("Выберите элемент в таблице");

                alert.showAndWait();
            }
            ObservableList<Purchase> str = allZakazTable.getItems();
            Purchase.DelF(str);
        });
    }

    private void showProductDetails(Purchase purchase){
        purchase = allZakazTable.getSelectionModel().getSelectedItem();
        if (purchase!=null){
            numberLabel.setText(purchase.getZakazNumber());
            clientLabel.setText(purchase.getClient());
            dateLabel.setText(purchase.getDate());
            nameLabel.setText(purchase.getName());
            oplataLabel.setText(purchase.getOplata());
        }
        else {
            numberLabel.setText("");
            clientLabel.setText("");
            dateLabel.setText("");
            nameLabel.setText("");
            oplataLabel.setText("");
        }
    }

    /*@FXML
    private void Delete(){

    }*/



}
