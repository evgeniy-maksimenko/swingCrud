package dao;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import dao.Dao;
import model.Person;

import java.util.ArrayList;

public class DaoPersonCassandra implements Dao{

    private static Cluster cluster;
    private static Session session;
    private final String URL        = "127.0.0.1";
    private final String KEYSPACE   = "dev";

    public DaoPersonCassandra() {
        cluster = Cluster.builder().addContactPoint(URL).build();
        session = cluster.connect(KEYSPACE);
    }

    @Override
    public ArrayList<Person> select() {
        ArrayList<Person> person = new ArrayList<Person>();
        ResultSet results = session.execute("SELECT * FROM person");
        for (Row row : results) {
            person.add(new Person(row.getInt("id"), row.getString("fname"), row.getString("lname"), row.getInt("age")));
        }
        return person;
    }

    @Override
    public void insert(Person model) {
        session.execute("INSERT INTO person (id, fname, lname, age) VALUES ( "+model.id+", '"+model.fname+"', '"+model.lname+"', "+model.age+")");
    }

    @Override
    public void update(Person model) {
        session.execute("UPDATE person SET fname='"+model.fname+"', lname='"+model.lname+"', age="+model.age+" WHERE id="+model.id+" ");
    }

    @Override
    public void delete(int id) {
        session.execute("DELETE FROM person WHERE id=" + id);
    }
}
