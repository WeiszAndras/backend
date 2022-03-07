package persistence;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * author: Andras Weisz
 * DbConnection class responsible to connect DB
 */
 class DbConnection {

    public static Connection connect(){
        String connectionString="jdbc:mysql://localhost:3306/cs_beugro";

        Connection connection= null;

        try {
            connection = DriverManager.getConnection(connectionString, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;

    }

}
