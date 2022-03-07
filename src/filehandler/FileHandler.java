
package filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * author: Andras Weisz
 * FileHandler class responsible to file writing and reading
 */
 class FileHandler {

     static void writeFile(ArrayList<String> data, String url){
         String txt=txtCollect(data);
         try {
             FileWriter writer=new FileWriter(url);
             writer.write(txt);
             writer.close();
         } catch (IOException ex) {
             Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

    private static String txtCollect(ArrayList<String> data) {
       StringBuilder builder=new StringBuilder();
       String[] sample={System.lineSeparator(), ""};
        for (int i = 0; i < data.size(); i++) {
            builder.append(data.get(i));
            builder.append(sample[(i+1)/ data.size()]);
        }
        return builder.toString();
    }
    
    static ArrayList<String> readFile(String url){
        ArrayList<String>  result=new ArrayList<>();
         try {
             FileReader reader=new FileReader(url);
             BufferedReader bufferedReader=new BufferedReader(reader);
             String row;
             while ((row=bufferedReader.readLine())!=null) {
                 result.add(row);
             }
             bufferedReader.close();
             reader.close();
         } catch (IOException ex) {
             Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
        return result;
    }
}
