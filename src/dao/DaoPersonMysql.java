package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import model.Person;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoPersonMysql implements Dao {
    public Statement st;

    public DaoPersonMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ma", "root", "");
            this.st = (Statement) conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Person> select() {

        ArrayList<Person> Person = new ArrayList<Person>();
        try {
            ResultSet rs = this.st.executeQuery("SELECT * FROM person");

            while (rs.next()) {
                Person p = new Person(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getInt("age"));
                Person.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Person;

    }

    @Override
    public void insert(Person model) {
        try {
            this.st.executeUpdate("INSERT INTO person (id, fname, lname, age) VALUES (NULL,'" + model.fname + "','" + model.lname + "','" + model.age + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person model) {
        try {
            this.st.executeUpdate("UPDATE person SET fname = '" + model.fname + "', lname = '" + model.lname + "', age = '" + model.age + "' WHERE id = " + model.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            this.st.executeUpdate("DELETE FROM person WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
