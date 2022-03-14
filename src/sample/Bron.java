package sample;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;

public class Bron {
    String number;
    String client;
    String date;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bron(String number, String client, String date) {
        this.number = number;
        this.client = client;
        this.date = date;
    }
    public static void DelF(ObservableList<Bron> broni) {
        try(FileWriter writer = new FileWriter("бронь.txt", false))
        {
            Bron bron;
            int i = 0;
            int size = broni.size();
            while (i<size){
                bron = broni.get(i);
                String str = bron.number + " " + bron.client + " " + bron.date ;
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
}
