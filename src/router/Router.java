package router;

import dao.DaoPersonMysql;
import model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Router extends AbstractTableModel {
    public ArrayList<Person> grid = null;
    public DaoPersonMysql model = new DaoPersonMysql();

    public Router() {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        Person model = grid.get(rowIndex);
        switch (columnIndex) {
            case 0: obj = model.id; break;
            case 1: obj = model.fname; break;
            case 2: obj = model.lname; break;
            case 3: obj = model.age; break;
        }
        return obj;
    }
}
