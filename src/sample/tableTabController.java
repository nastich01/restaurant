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

public class tableTabController {
    private final ObservableList<Table> tableData = FXCollections.observableArrayList();
    @FXML
    private TableView<Table> tableTable;

    @FXML
    private TableColumn<Table, String> number;

    @FXML
    private TableColumn<Table, String> svoboden;

    @FXML
    private Label numberLabel;

    @FXML
    private Label svobodenLabel;

    @FXML
    private Button izmen;


    @FXML
    void initialize() {
        String num;
        String sv;

        int dl;

        BufferedReader br = null;
        try {
            File file = new File("столы.txt");
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new FileReader(file));
            String stt;
            while ((stt = br.readLine()) != null) {
                //dl = stt.length();
                //int i = stt.indexOf(" ");
                num = stt.substring(0, 1);
                //stt = stt.substring(i + 1, dl);

                //dl = stt.length();
                //i = stt.indexOf(" ");
                sv = stt.substring(2, 3);


                    tableData.add(new Table(num,sv));
                    number.setCellValueFactory(new PropertyValueFactory<>("number"));
                    svoboden.setCellValueFactory(new PropertyValueFactory<>("svoboden"));
                    tableTable.setItems(tableData);


            }
        }
        catch (IOException e){
            System.out.println("Ошибка при создании или открывании файла");
        }

        showProductDetails(null);

        tableTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));

    }

    private void showProductDetails(Table table){
        table = tableTable.getSelectionModel().getSelectedItem();
        if (table!=null){
            numberLabel.setText(table.getNumber());
            svobodenLabel.setText(table.getSvoboden());
        }
        else {
            numberLabel.setText("");
            svobodenLabel.setText("");
        }
    }


    public boolean showProductEditDialog(Table table) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(tableTab2Controller.class.getResource("tableTab2.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменение записи");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            tableTab2Controller controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(table);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    private void handleEditProduct() {
        Table selectedProduct = tableTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = showProductEditDialog(selectedProduct);
            if (okClicked) {
                showProductDetails(selectedProduct);
                int selectedIndex=tableTable.getSelectionModel().getSelectedIndex();
                tableData.set(selectedIndex, selectedProduct);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Нет выбранного стола");
            alert.setContentText("Выберите продукт в таблице");

            alert.showAndWait();
        }
        ObservableList<Table> str = tableTable.getItems();
        Table.DellF(str);
    }

}
