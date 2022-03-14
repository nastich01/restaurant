package sample;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;

public class Dish {
    String articul;
    String name;
    String price;
    String v;
    String component;
    Boolean exist;



    public Dish(String articul, String name, String price,  String component, String v,Boolean exist) {
        this.articul = articul;
        this.name = name;
        this.price = price;
        this.v = v;
        this.component=component;
        this.exist = exist;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getExist() {
        return exist;
    }



    public void setExist(Boolean exist) {
        this.exist = exist;
    }
    public static void DelF(ObservableList<Dish> dishes) {
        try(FileWriter writer = new FileWriter("блюда.txt", false))
        {
            Dish dish;
            int i = 0;
            int size = dishes.size();
            while (i<size){
                dish = dishes.get(i);
                String str = dish.articul + " " + dish.name + " " + dish.price + " " + dish.component+"."+dish.v+" "+dish.exist.toString();
                writer.append(str);
                writer.append("\n");
                ++i;
            }
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void WrStat(Dish dish){
        try(FileWriter writer = new FileWriter("статистика.txt", true))
        {

                String str = dish.articul;
                writer.append(str);
                writer.append("\n");


            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
