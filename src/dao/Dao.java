package dao;


import model.Person;

import java.util.ArrayList;

public interface Dao {
    public ArrayList<Person> select();
    public void insert(Person model);
    public void update(Person model);
    public void delete(int id);
}
