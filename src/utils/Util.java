
package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class to help format and handle datetime.
 */
public class Util {
    
    private static DateTimeFormatter formatter=
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime dateFromText(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }
    
    public static String TextFromDate(LocalDateTime date){
        return date.format(formatter);
    }

}
