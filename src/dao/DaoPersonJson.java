package dao;


import java.io.*;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Person;


public class DaoPersonJson implements Dao {

    private final String JSONFile = "db.json";
    ArrayList<Person> person;

    @Override
    public ArrayList<Person> select() {
        person = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(JSONFile));
            Gson gson = new Gson();
            ArrayList<Person> result = gson.fromJson(reader, new TypeToken<ArrayList<Person>>() {
            }.getType());
            for (Person model : result) {
                person.add(new Person(model.id, model.fname, model.lname, model.age));
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return person;
    }

    @Override
    public void insert(Person model) {
        person = select();
        person.add(model);
        writeToFile(person);

    }

    @Override
    public void update(Person model) {
        delete(model.getId());
        insert(model);
    }

    @Override
    public void delete(int id) {
        person = select();
        ArrayList<Person> newPerson = new ArrayList<>();
        for (Person model : select()) {
            if (model.id != id) {
                newPerson.add(model);
            }
        }
        writeToFile(newPerson);
    }


    private void writeToFile(ArrayList<Person> person) {
        String json = new Gson().toJson(person);
        File newTextFile = new File(JSONFile);
        FileWriter fw;
        try {
            fw = new FileWriter(newTextFile);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
