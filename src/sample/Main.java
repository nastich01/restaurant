package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("entrance.fxml"));
        primaryStage.setTitle("Вход в систему");
        primaryStage.setScene(new Scene(root, 800,460 ));
        primaryStage.show();
        Stage stage = (Stage) primaryStage.getScene().getWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
