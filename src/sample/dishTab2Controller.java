package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class dishTab2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dishContentField;

    @FXML
    private TextField dishIdField;

    @FXML
    private TextField dishNameField;

    @FXML
    private TextField dishSumField;

    @FXML
    private TextField dishVField;

    @FXML
    private TextField dishExField;


    private Stage dialogStage;
    private Dish dish;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Dish dish) {
        this.dish = dish;

        dishIdField.setText(dish.getArticul());
        dishNameField.setText(dish.getName());
        dishSumField.setText(dish.getPrice());
        dishVField.setText(dish.getV());
        dishExField.setText(dish.getExist().toString());
        dishContentField.setText(dish.getComponent());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (dishIdField.getText() == null || dishIdField.getText().length() == 0) {
            errorMessage += "Нет доступного артикула\n";
        }
        if (dishNameField.getText() == null || dishNameField.getText().length() == 0) {
            errorMessage += "Нет доступного наименования товара\n";
        }
        if (dishSumField.getText() == null || dishSumField.getText().length() == 0) {
            errorMessage += "Нет доступной суммы\n";
        }
        if (dishContentField.getText() == null || dishContentField.getText().length() == 0) {
            errorMessage += "Нет доступного количества\n";
        }
        if (dishVField.getText() == null || dishVField.getText().length() == 0) {
            errorMessage += "Нет доступного количества\n";
        }
        if (dishExField.getText() == null || dishExField.getText().length() == 0) {
            errorMessage += "Нет доступного количества\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            dish.setArticul(dishIdField.getText());
            dish.setName(dishNameField.getText());
            dish.setPrice(dishSumField.getText());
            dish.setV(dishVField.getText());
            dish.setExist(Boolean.parseBoolean(dishExField.getText()));
            dish.setComponent(dishContentField.getText());

            //Product.addProduct(product);
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}

