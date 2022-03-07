package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 * author: Andras Weisz
 * AbstractDbProcess class responsible to process sql requests
 */
public abstract class AbstractDbProcess {

    private Connection connection;
    private Statement statement;

    public  AbstractDbProcess() {
        connection = DbConnection.connect();
    }

    public final List<?> getAllEntity() {
        List<?> result = new ArrayList<>();
        try {
            String select = getSelectCommand();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(select)) {
                result = getList(resultSet);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AbstractDbProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    protected void sqlCommand(String sql) {

        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDbProcess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDbProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // sql to insertion of DB
    protected abstract void getInsertCommand();
    // sql to selection from DB
    protected abstract String getSelectCommand();
    // get List from selection
    protected abstract List<?> getList(ResultSet set);
}
