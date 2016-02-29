package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.Dao;
import model.Person;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;


public class DaoPersonRedis implements Dao {
    Jedis jedis;
    private final String DATABASE   = "model.person";
    private final String URL        = "localhost";

    public DaoPersonRedis() {
        jedis = new Jedis(URL);
        System.out.println("Connection to server sucessfully");
    }

    @Override
    public ArrayList<Person> select() {
        ArrayList<Person> person = new ArrayList<Person>();
        List<String> list = jedis.lrange(DATABASE, 0, jedis.llen(DATABASE));
        for (int i = 0; i < list.size(); i++) {
            Gson gson = new Gson();
            ArrayList<Person> persons = gson.fromJson(list.get(i), new TypeToken<ArrayList<Person>>() {
            }.getType());
            for (Person model : persons) {
                person.add(new Person(model.id, model.fname, model.lname, model.age));
            }
        }
        return person;
    }

    @Override
    public void insert(Person model) {
        ArrayList<Person> person = new ArrayList<Person>();
        person.add(model);
        String json = new Gson().toJson(person);
        jedis.rpush(DATABASE, json);
    }

    @Override
    public void update(Person model) {
        delete(model.id);
        insert(model);
    }

    @Override
    public void delete(int id) {
        ArrayList<Person> person = new ArrayList<Person>();
        for (Person model : select()) {
            if (model.id == id) {
                person.add(model);
                String json = new Gson().toJson(person);
                jedis.lrem(DATABASE, 0, json);
            }
        }
    }
}
