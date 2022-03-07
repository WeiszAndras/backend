package production;

import entities.Nyak;
import filehandler.NyakDataHandler;
import java.util.ArrayList;

/**
 * author: Andras Weisz
 * Production class find the main method of the project
 */
public class Production {
    public static void main(String[] args) {

        Nyak nyak = new Nyak();
        // Task 5 -> Read from file the objects
        NyakDataHandler handler=new NyakDataHandler();
        ArrayList<Nyak> data = handler.NyakReadFromFile("puffer.txt");
        for (int i = 0; i < data.size(); i++) {
            System.out.println(i+" "+data.get(i).getId()
                    +" "+ data.get(i).getId()
                    +" "+data.get(i).getQuantity()
                    +" "+data.get(i).getStartDate()
                    +"|| "+data.get(i).getEndDate());
        }

        // Task 5 -> Save to Mysql the objects
        nyak.saveRandomNyakok();

    }
}