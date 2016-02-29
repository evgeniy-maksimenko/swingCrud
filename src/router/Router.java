package router;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import dao.DaoPersonMysql;
import dao.Dao;
import dao.DaoPersonCassandra;
//import dao.socket.DaoPersonMysqlNetClient;
import model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Router extends AbstractTableModel {
    public ArrayList<Person> grid = null;
    public Dao model;


    public Router() {
        // model = new DaoPersonMysql();
        // model = new DaoPersonMongo();
        // model = new DaoPersonMysqlHib();
        // model = new DaoPersonMysqlNetClient(this);
        // model = new DaoPersonRedis();
        model = new DaoPersonCassandra();
        grid = model.select();
    }

    public String getColumnName(int col) {
        String[] str = {"id", "fname", "lname", "age"};
        return str[col];
    }

    @Override
    public int getRowCount() {

        return grid.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Object  result = null;
        Person p = grid.get(row);
        switch (col)
        {
            case 0: result = p.id; break;
            case 1: result = p.fname; break;
            case 2: result = p.lname; break;
            case 3: result = p.age; break;
        }
        return result;
    }
}
