package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class EntranceController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button regis;

    @FXML
    private PasswordField TextField2;

    @FXML
    private Button vxod;


    @FXML
    private TextField textField1;

    public static String login;
    public static Integer number;
    final static Random random = new Random();

    @FXML
    void initialize() {
        vxod.setOnAction(actionEvent -> {

            vxod.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();


            String l = textField1.getText().trim();
            String p = TextField2.getText().trim();
            login=l;

            String log = "";
            String pas = "";
            boolean ch = false;
            boolean admin=false;

            BufferedReader br = null;
            try {
                File file = new File("данные.txt");
                if (!file.exists())
                    file.createNewFile();
                br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null) {
                    int i = st.indexOf(" ");
                    log = st.substring(0, i);
                    pas = st.replace(log, "").trim();
                    if ((l.equals(log)) && (p.equals(pas))) {
                        ch = true;
                    }
                    if ((l.equals("admin")) && (p.equals("111"))) {
                        admin = true;
                    }
                    if (admin){
                        loader.setLocation(getClass().getResource("admin_menu.fxml"));

                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        number= random.nextInt(100);
                        loader.setLocation(getClass().getResource("main_scan.fxml"));

                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании или открытии файла");
            }

            if (ch) {

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root,800,460));
                stage.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Сообщение об ошибке");
                alert.setContentText("Проверьте введенные логин и пароль");
                alert.showAndWait();
            }
        });



    }
    @FXML
    private void regis(){
        openScene("registration.fxml");
    }

    public void openScene(String window) {
       //regis.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root,800,460));
        stage1.showAndWait();
    }

}
