
package filehandler;

import entities.Nyak;
import utils.Util;
import java.util.ArrayList;

/**
 * author: Andras Weisz
 * NyakDataHandler class responsible to manage Nyak Entity to file handling
 */
public class NyakDataHandler {

    // Task 4 -> pipe character as separator in the file
    private final static char SEPARATOR='|';

    public void NyakWriteToFile(ArrayList<Nyak> nyakok, String url){
        ArrayList<String> dataList=new ArrayList<>();
        for (Nyak item : nyakok) {
            dataList.add(NyakToText(item));
        }
        FileHandler.writeFile(dataList, url);
    }

    private String NyakToText(Nyak nyak) {
       StringBuilder builder=new StringBuilder();
       builder.append(nyak.getId());
       builder.append(SEPARATOR);
       builder.append(nyak.getQuantity());
       builder.append(SEPARATOR);
       builder.append(Util.TextFromDate(nyak.getStartDate()));
       builder.append(SEPARATOR);
       builder.append(Util.TextFromDate(nyak.getEndDate()));
       return builder.toString();
               
    }
    
    public ArrayList<Nyak> NyakReadFromFile(String url){
        ArrayList<Nyak> nyakList=new ArrayList<>();
        ArrayList<String> dataList= FileHandler.readFile(url);
        for (String raw : dataList) {
            nyakList.add(NyakFromText(raw));
         
        }
        return nyakList;
    }

    private Nyak NyakFromText(String raw) {
        String[] data= raw.split("["+SEPARATOR+"]");
          Nyak nyak=new Nyak();
        nyak.setId( Integer.parseInt(data[0]));
        nyak.setQuantity((short) Integer.parseInt(data[1]));
        nyak.setStartDate(Util.dateFromText(data[2]));
        nyak.setEndDate(Util.dateFromText(data[3]));
          return nyak;
    }

    // task 5 -> delete the 4th data and delete the 4th id of pcb
    public ArrayList<Nyak> deleteData(ArrayList<Nyak> data){
        data.remove(4);
        for (int i = 0; i < data.size(); i++) {
            if ( data.get(i).getId() == 4 ) {
                data.remove(i);
            }
        }
        return data;
    }

}
