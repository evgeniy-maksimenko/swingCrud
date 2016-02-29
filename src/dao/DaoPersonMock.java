package dao;

import model.Person;

import java.util.ArrayList;

public class DaoPersonMock implements Dao {
    ArrayList<Person> persons = new ArrayList<>();

    @Override
    public ArrayList<Person> select() {
        return persons;
    }

    @Override
    public void insert(Person model) {

    }

    @Override
    public void update(Person model) {

    }

    @Override
    public void delete(int id) {

    }
}
