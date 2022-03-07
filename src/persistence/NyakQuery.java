package persistence;

import entities.Nyak;
import filehandler.NyakDataHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * author: Andras Weisz
 * NyakQuery class responsible to create sql
 */
public class NyakQuery extends AbstractDbProcess {

    private static String INSERT_INTO="insert into cs_beugro.production (`pcb_id`, `quantity`, `startDate`, `endDate`) values('%d', '%d', '%s', '%s');";

    @Override
    public void getInsertCommand() {

        NyakDataHandler handler=new NyakDataHandler();
        handler.NyakWriteToFile(Nyak.getRandomNyak(), "puffer.txt");
        ArrayList<Nyak> dataResult = handler.deleteData(handler.NyakReadFromFile("puffer.txt"));

        for (int i = 0; i < dataResult.size(); i++) {
            String insertText=String.format(INSERT_INTO,
                    dataResult.get(i).getId(),
                    dataResult.get(i).getQuantity(),
                    dataResult.get(i).getStartDate(),
                    dataResult.get(i).getEndDate());
            sqlCommand(insertText);
        }
    }

    @Override
    protected String getSelectCommand() {
        return "select * from cs_beugro.products;";
    }

    @Override
    protected List<?> getList(ResultSet set) {
        List<Nyak> result=new ArrayList<>();
        try {
            while (set.next()) {
                result.add(dataQuery(set));
            }} catch (SQLException ex) {
            Logger.getLogger(NyakQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // set Nyak Entity after request of DB
    private Nyak dataQuery(ResultSet set) throws SQLException {
        Nyak nyak=new Nyak();
        nyak.setPcb_id(set.getString("pcb"));
        nyak.setId(set.getInt("id"));
        return nyak;
    }

}
