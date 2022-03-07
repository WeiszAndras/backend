package entities;

import persistence.NyakQuery;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * author: Andras Weisz
 * Entity class Nyak
 */
public class Nyak {

    private static Random random;

    static {
        random = new Random();
    }

    // Task 3 -> init id, pcb_id, quantity
    private int id;
    private String pcb_id;
    private short quantity = (short)(random.nextInt(1000)+1);

    // Task 3 -> init startDate
    private int startRandomInt = random.nextInt(11)+10;
    private LocalDateTime startDate = LocalDateTime.now().plus(Duration.of(startRandomInt, ChronoUnit.MINUTES));

    // Task 3 -> init endDate
    private int endRandomInt = (random.nextInt(15)+1)+startRandomInt;
    private LocalDateTime endDate = LocalDateTime.now().plus(Duration.of(endRandomInt, ChronoUnit.MINUTES));

    private static final NyakQuery nyakQuery = new NyakQuery();

    // Task 3 -> Object stored in ArrayList
    private static final ArrayList<?> allNyak = new ArrayList<>(nyakQuery.getAllEntity());

    // getter-setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPcb_id() {
        return pcb_id;
    }

    public void setPcb_id(String pcb_id) {
        this.pcb_id = pcb_id;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // Task 3 -> Object stored in ArrayList
    public static ArrayList<?> getAllNyak(){
        return allNyak;
    }

    // Task 3 -> filter from the data
    public static ArrayList<Nyak> getRandomNyak(){

        ArrayList<Nyak> result=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int randomNyakIndex = random.nextInt(allNyak.size());
            Nyak randomNyak = (Nyak) allNyak.get(randomNyakIndex);
            if(!result.contains(randomNyak)){
                result.add(randomNyak);
            }else{
                i--;
            }
        }
        return result;
    }

    public void saveRandomNyakok(){
        nyakQuery.getInsertCommand();
    }

}
