package dao;

import com.mongodb.*;
import model.Person;

import java.util.ArrayList;

public class DaoPersonMongo implements Dao {

    public DBCollection table;

    public DaoPersonMongo() {
        MongoClient mongo = new MongoClient("127.0.0.1", 27017);
        DB db = mongo.getDB("ma");
        table = db.getCollection("person");
    }

    @Override
    public ArrayList<Person> select() {
        ArrayList<Person> person = new ArrayList<Person>();
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = table.find(searchQuery);
        DBObject object;

        while (cursor.hasNext()) {
            object = cursor.next();
            double idD = new Double(String.valueOf(object.get("id"))); int id = (int) idD;
            double ageD = new Double(String.valueOf(object.get("age"))); int age = (int) ageD;
            person.add(new Person(id, (String) object.get("fname"), (String) object.get("lname"), age));
        }
        return person;
    }

    @Override
    public void insert(Person model) {
        BasicDBObject document = new BasicDBObject();
        document.put("id", model.id);
        document.put("fname", model.fname);
        document.put("lname", model.lname);
        document.put("age", model.age);
        table.insert(document);
    }

    @Override
    public void update(Person model) {
        delete(model.getId());
        insert(model);
    }

    @Override
    public void delete(int id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", id);
        table.remove(searchQuery);
    }
}
