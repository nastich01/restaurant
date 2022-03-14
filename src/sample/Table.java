package sample;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;

public class Table {
    String number;
    String svoboden;
    String client;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSvoboden() {
        return svoboden;
    }

    public void setSvoboden(String svoboden) {
        this.svoboden = svoboden;
    }



    public Table(String number, String svoboden) {
        this.number = number;
        this.svoboden = svoboden;

    }
    public static void DellF(ObservableList<Table> tables) {
        try(FileWriter writer = new FileWriter("столы.txt", false))
        {
            Table table;
            int i = 0;
            int size = tables.size();
            while (i<size){
                table = tables.get(i);
                String str = table.number + " " + table.svoboden;
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
