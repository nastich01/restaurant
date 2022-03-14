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

public class dishTabController {
    private final ObservableList<Dish> dessertData = FXCollections.observableArrayList();
    private final ObservableList <Purchase> purchaseData = FXCollections.observableArrayList();
    public static ObservableList <Dish> zakazData = FXCollections.observableArrayList();
    @FXML
    private TableView<Dish> dessertTable;

    @FXML
    private TableColumn<Dish, String> name;

    @FXML
    private TableColumn<Dish, String> price;

    @FXML
    private Label IdLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label vLabel;

    @FXML
    private TextArea componentLabel;

    @FXML
    private Label ExLabel;

    @FXML
    private Button dobav;

    @FXML
    private Button izmen;

    @FXML
    private Button del;


    @FXML
    void initialize() {
        String ar;
        String n;
        String p;
        String c;
        String v;
        Boolean ex;
        int dl;

        BufferedReader br = null;
        try {
            File file = new File("блюда.txt");
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
                stt = stt.substring(i + 1, dl);

                dl = stt.length();
                i = stt.indexOf(" ");
                p = stt.substring(0, i);
                stt = stt.substring(i + 1, dl);

                dl = stt.length();
                i = stt.indexOf(".");
                c = stt.substring(0, i);
                stt = stt.substring(i + 1, dl);

                i = stt.indexOf(" ");
                v = stt.substring(0, i);
                ex = Boolean.parseBoolean(stt.replace(stt.substring(0, i), "").trim());
                if (ex){
                    dessertData.add(new Dish(ar,n,p,c,v,ex));
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    dessertTable.setItems(dessertData);
                }

            }
        }
        catch (IOException e){
            System.out.println("Ошибка при создании или открывании файла");
        }

        showProductDetails(null);

        dessertTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));

    }

    private void showProductDetails(Dish dish){
        dish = dessertTable.getSelectionModel().getSelectedItem();
        if (dish!=null){
            IdLabel.setText(dish.getArticul());
            nameLabel.setText(dish.getName());
            priceLabel.setText(dish.getPrice());
            vLabel.setText(dish.getV());
            componentLabel.setText(dish.getComponent());
            ExLabel.setText(dish.getExist().toString());
        }
        else {
            IdLabel.setText("");
            nameLabel.setText("");
            priceLabel.setText("");
            vLabel.setText("");
            componentLabel.setText("");
            ExLabel.setText("");
        }
    }
    @FXML
    private void handleDeleteProduct(){
        int selectedIndex=dessertTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            dessertTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран товар");
            alert.setContentText("Выберите товар в таблице");

            alert.showAndWait();
        }
        ObservableList<Dish> str = dessertTable.getItems();
        Dish.DelF(str);
    }

    public boolean showProductEditDialog(Dish dish) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(dishTab2Controller.class.getResource("dishTab2.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменение записи");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dishTab2Controller controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(dish);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewProduct() {
        Dish tempProduct = new Dish("","","","","",false);
        boolean okClicked = this.showProductEditDialog(tempProduct);
        if (okClicked) {
            dessertData.add(tempProduct);
        }
        ObservableList<Dish> str = dessertTable.getItems();
        Dish.DelF(str);
    }

    @FXML
    private void handleEditProduct() {
        Dish selectedProduct = dessertTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = showProductEditDialog(selectedProduct);
            if (okClicked) {
                showProductDetails(selectedProduct);
                int selectedIndex=dessertTable.getSelectionModel().getSelectedIndex();
                dessertData.set(selectedIndex, selectedProduct);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Нет выбранного продукта");
            alert.setContentText("Выберите продукт в таблице");

            alert.showAndWait();
        }
        ObservableList<Dish> str = dessertTable.getItems();
        Dish.DelF(str);
    }

}

