package sample;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;

public class Purchase {
    String zakazNumber;
    String client;
    String date;
    ObservableList <Dish> zakazData;
    String name;
    String oplata;


    public Purchase (String zakazNumber, String client, String date, ObservableList <Dish> zakazData, String oplata)
    {
        this.zakazNumber=zakazNumber;
        this.client = client;
        this.date = date;
        this.zakazData = zakazData;
        this.oplata=oplata;
    }


    public Purchase(String zakazNumber, String client, String date, String name, String oplata) {
        this.zakazNumber=zakazNumber;
        this.client = client;
        this.date = date;
        this.name=name;
        this.oplata=oplata;
    }



    public void setZakazNumber(String zakazNumber){this.zakazNumber=zakazNumber;}
    public String getZakazNumber(){return zakazNumber;}

    public String getClient ()
    {
        return client;
    }

    public void setClient (String client)
    {
        this.client = client;
    }

    public ObservableList<Dish> getZakazData() {
        return zakazData;
    }

    public void setZakazData(ObservableList<Dish> zakazData) {
        this.zakazData = zakazData;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date= date;
    }
    public String getOplata() {
        return oplata;
    }

    public void setOplata(String oplata) {
        this.oplata = oplata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void DelF(ObservableList<Purchase> allZakaz) {
        try(FileWriter writer = new FileWriter("заказы.txt", false))
        {
            Purchase purchase;
            int i = 0;
            int size = allZakaz.size();
            while (i<size){
                purchase = allZakaz.get(i);
                String str = purchase.zakazNumber + " " + purchase.client + " " + purchase.date+" "+purchase.name+" "+purchase.oplata ;
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

    public static void WrPurchases(Purchase purchase) {
        try(FileWriter writer = new FileWriter("заказы.txt", true))
        {

            String n;
            int i = 0;
            int size = purchase.zakazData.size();
            while (i<size){
                n = purchase.zakazData.get(i).getName();
                String str = purchase.zakazNumber + " " + purchase.client + " " + purchase.date + " " + n+" "+purchase.oplata +System.lineSeparator();
                writer.write(str);
                ++i;
            }


        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /*public static void DellF(ObservableList<Purchase> purchases) {
        try(FileWriter writer = new FileWriter("заказы.txt", false))
        {
            Purchase purchase;
            int i = 0;
            int size = purchases.size();
            while (i<size){
                purchase = purchases.get(i);
                String str = purchase.zakazNumber + " " + purchase.client + " " + purchase.date + " " + purchase.zakazData.toString();
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
    public static void WrNewZakaz (Dish dish) {
        try(FileWriter writer = new FileWriter("статистика.txt", true))
        {
            String str = dish.articul+System.lineSeparator();
            writer.write(str);
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }*/
}
