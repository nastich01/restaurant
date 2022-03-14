package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class registrationController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LOg;

    @FXML
    private PasswordField PAs;

    @FXML
    private Button Regi;

    @FXML
    private Button vxod;



    @FXML
    void initialize() {
        Regi.setOnAction(actionEvent -> {
            String l = LOg.getText().trim();
            String p = PAs.getText().trim();

            String log = "";
            boolean ch = false;

            BufferedReader br = null;
            try{
                File file = new File("данные.txt");
                if(!file.exists())
                    file.createNewFile();
                br = new BufferedReader(new FileReader(file));
                String st;
                while((st = br.readLine()) != null) {
                    int i = st.indexOf(" ");
                    log = st.substring(0, i);
                    if (l.equals(log)){
                        ch = true;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании или открывании файла");
            }

            if (ch){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Сообщение об ошибке");
                alert.setContentText("Данный логин уже существует");
                alert.showAndWait();


            }
            else{
                try(FileWriter writer = new FileWriter("данные.txt",true)){
                    String lp = l+" "+p + System.lineSeparator();
                    writer.write(lp);
                    EntranceController.login=l;
                }
                catch (IOException e){
                    System.out.println("Ошибка при создании или открывании файла");
                }
            }
        });

        /*vxod.setOnAction(actionEvent -> {
            EntranceController.number= EntranceController.random.nextInt(100);
            FXMLLoader loader = new FXMLLoader();
            //vxod.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("entrance.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });*/}
    @FXML
    private void vxod(){
        EntranceController.number= EntranceController.random.nextInt(100);
        FXMLLoader loader = new FXMLLoader();
        //vxod.getScene().getWindow().hide();
        loader.setLocation(getClass().getResource("main_scan.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
        /*public void openScene(String window) {
            vxod.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(window));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }*/





}
