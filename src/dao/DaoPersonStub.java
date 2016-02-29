package dao;

import model.Person;

import java.util.ArrayList;

public class DaoPersonStub implements Dao {
    ArrayList<Person> persons = new ArrayList<>();

    @Override
    public ArrayList<Person> select() {
        return persons;
    }

    @Override
    public void insert(Person model) {
        persons.add(model);
    }

    @Override
    public void update(Person model) {
        delete(model.getId());
        insert(model);
    }

    @Override
    public void delete(int id) {
        persons.remove(id);
    }
}
