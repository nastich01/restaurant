package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class new_menuController {
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
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label vLabel;

    @FXML
    private TextArea componentLabel;

    @FXML
    private Button zakazat;

    @FXML
    private Button oform;

    @FXML
    private Button bron;

    @FXML
    TextField namestat;

    @FXML
    TextField pricestat;

    public static Integer nmax;
    public static Integer nmax2;
    public static String stat1;
    public static String stat2;

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
            nameLabel.setText(dish.getName());
            priceLabel.setText(dish.getPrice());
            vLabel.setText(dish.getV());
            componentLabel.setText(dish.getComponent());
        }
        else {
            nameLabel.setText("");
            priceLabel.setText("");
            vLabel.setText("");
            componentLabel.setText("");
        }
    }

    @FXML
    private void zakazat() {
       Dish selectedProduct = dessertTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            showProductDetails(selectedProduct);
            Dish.WrStat(selectedProduct);
            //Purchase.WrNewZakaz(selectedProduct);
            zakazData.add(selectedProduct);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Нет выбранного продукта");
            alert.setContentText("Выберите продукт в таблице");

            alert.showAndWait();
        }

    }
    @FXML
    private void oform(){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OformZakaz.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root,730,459));
        stage.showAndWait();
    }
    @FXML
    private void bron(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("reservation.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root,730,459));
        stage.showAndWait();
    }

    @FXML
    private void poiskName() {
        FilteredList<Dish> filteredData = new FilteredList<>(dessertData, b -> true);
        namestat.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dish -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (dish.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Dish> sortedData = new SortedList<Dish>(filteredData);
        sortedData.comparatorProperty().bind(dessertTable.comparatorProperty());
        dessertTable.setItems(sortedData);

    }
    @FXML
    private void poiskPrice() {
        FilteredList<Dish> filteredData = new FilteredList<>(dessertData, b -> true);
        //Устанавливаем предикат фильтра при каждом его изменении
        pricestat.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dish -> {
                //Если текст фильтра пуст, то показываем весь список элементов
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //Сравниваем значения списка с текстом фильтра
                String lowerCaseFilter = newValue.toLowerCase();

                if (dish.getPrice().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Фильтр соответствует значению
                }
                return false;
            });
        });
        //Помещаем фильтрованный список в сортировочный
        SortedList<Dish> sortedData = new SortedList<>(filteredData);
        //Связываем компараторы сортировочного списка и таблицы
        //Иначе сортировка таблицы не будет иметь никакого эффекта
        sortedData.comparatorProperty().bind(dessertTable.comparatorProperty());
        //Сортируем и фильтруем данные в таблице
        dessertTable.setItems(sortedData);

    }

    @FXML
    private void stat() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ensureCapacity: numbers.ensureCapacity(25);
        for(int i=0; i<25 ;i++) {
            numbers.add(0);
        }
        Integer x;
        BufferedReader br = null;
        try {
            File file = new File("статистика.txt");
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new FileReader(file));
            String stt;
            while ((stt = br.readLine()) != null) {
                x = Integer.parseInt(stt.substring(0, 1));
                Integer sum=numbers.get(x) +1;
                numbers.set(x, sum);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(numbers);
        Integer max=numbers.get(0);
        Integer max2=numbers.get(0);
        for(Integer y : numbers) {
            if(y>=max){
                max2=max;
                max=y;
                nmax2=nmax;
                nmax=numbers.indexOf(y);
                System.out.println(max+" "+max2);
            } else if(y>=max2){
                max2=y;
                nmax2=numbers.indexOf(y);
                System.out.println(max+" "+max2);
            }
        }
        System.out.println("---------");
        System.out.println(nmax+" "+nmax2);
        Dish dish;
        Integer i = 0;
        int size = dessertData.size();
        while (i<size){
            System.out.println(i+" "+dessertData.get(i).name);
            if (i==(nmax-1)){
                dish = dessertData.get(i);
                stat1=dish.name;
            }else if (i==(nmax2-1)){
                dish=dessertData.get(i);
                stat2=dish.name;
            }
            ++i;
        }
        if (stat1!="" && stat2!=""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(null);
            alert.setTitle("Статистика");
            alert.setHeaderText("Блюда, которые заказывают часто:");
            alert.setContentText("1: "+stat1+System.lineSeparator()+"2: "+stat2);

            alert.showAndWait();
        }
       else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Статистика");
            alert.setHeaderText("Статистика не сформирована");
            alert.setContentText("Вы первый покупатель!");

            alert.showAndWait();
        }

    }}