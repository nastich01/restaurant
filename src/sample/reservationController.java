package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class reservationController {
    private final ObservableList<Table> tableData = FXCollections.observableArrayList();
    @FXML
    private TextField numberOfTable;
    @FXML
    private Button zabron;
    @FXML
    void initialize() {
        zabron.setOnAction(actionEvent -> {
            zabron.getScene().getWindow().hide();

            String l = numberOfTable.getText().trim();
            String n = "";
            String e = "";
            String c="";
            Integer dl;
            boolean check = false;

            BufferedReader br = null;
            try {
                File file = new File("столы.txt");
                if (!file.exists())
                    file.createNewFile();
                br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null) {

                    int i = st.indexOf(" ");
                    n = st.substring(0, i);
                    e=st.substring(i+1,i+2);

                    if ((l.equals(n)) && (e.equals("+"))) {
                        System.out.println("+");
                        check = true;
                        tableData.add(new Table(n,"-"));
                        try(FileWriter writer = new FileWriter("бронь.txt", true))
                        {
                            String str = l+" "+ EntranceController.login+" "+LocalDate.now().toString()+System.lineSeparator();
                            writer.write(str);
                            writer.close();
                        }
                        catch(IOException ex){
                            ex.printStackTrace();
                        }
                    }else{

                        tableData.add(new Table(n,e));
                    }
                }
                Table.DellF(tableData);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (!check) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Сообщение об ошибке");
                alert.setContentText("Выбранный стол невозможно забронировать.");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Уведомление");
                alert.setHeaderText("Сообщение о брони");
                alert.setContentText("Бронь стола "+ l+" прошла успешно.");
                alert.showAndWait();
            }
        });
    }



}
