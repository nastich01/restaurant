package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class OformZakazController {
    ObservableList<String> oplata = FXCollections.observableArrayList("оплата при получении", "оплата онлайн");
    @FXML
    Label summa;

    @FXML
    TextField textField1;

    @FXML
    private TableView<Dish> tekZakazTable;

    @FXML
    private TableColumn<Dish, String> name;

    @FXML
    private TableColumn<Dish, String> price;

    @FXML
    ChoiceBox oplataChoiceBox;

    @FXML
    Button oform;

    @FXML
    void initialize(){
        Integer n,x=0;
        int i = 0;
        int size = new_menuController.zakazData.size();
        while (i<size){
            n = Integer.parseInt(new_menuController.zakazData.get(i).getPrice());
            x = x+n;
            ++i;}
        summa.setText(Integer.toString(x));
        oplataChoiceBox.setItems(oplata);
        oplataChoiceBox.setOnAction(event -> {
            if (oplataChoiceBox.getValue()=="оплата онлайн"){
                textField1.setDisable(false);
            }else {
                textField1.setDisable(true);
            }
        });
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tekZakazTable.setItems(new_menuController.zakazData);
    }
    @FXML
    private void oform(){
            if (oplataChoiceBox.getValue()=="оплата онлайн"){
                Purchase.WrPurchases(new Purchase(EntranceController.number.toString(),EntranceController.login, LocalDate.now().toString(),new_menuController.zakazData,"+"));

                if(textField1.getText().trim().length()==0){
                    System.out.println(textField1.getText().trim());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Сообщение об ошибке");
                    alert.setContentText("Введите номер карты");
                    alert.showAndWait();
                }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Уведомление");
                        alert.setHeaderText("Сообщение о заказе");
                        alert.setContentText("Вы оплатили заказ онлайн.Ваш заказ принят!");
                        alert.showAndWait();

                }
                //purchaseData.add(new Purchase(EntranceController.number.toString(),EntranceController.login, LocalDate.now().toString(),selectedProduct.articul,"-"));
            }else {
                Purchase.WrPurchases(new Purchase(EntranceController.number.toString(),EntranceController.login, LocalDate.now().toString(),new_menuController.zakazData,"-"));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Уведомление");
                    alert.setHeaderText("Сообщение о заказе");
                    alert.setContentText("Вы выбрали оплату при получении.Ваш заказ принят!");
                    alert.showAndWait();

            }


    }
}
