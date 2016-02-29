package dao;

import model.Person;
import org.ho.yaml.YamlDecoder;
import org.ho.yaml.YamlEncoder;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class DaoPersonYaml implements Dao {

    ArrayList<Person> person = new ArrayList<Person>();
    private final String YAMLFile = "db.yaml";

    @Override
    public ArrayList<Person> select() {
        try {
            YamlDecoder dec = new YamlDecoder(new FileInputStream(YAMLFile));
            person = (ArrayList<Person>) dec.readObject();
            dec.close();
        } catch (EOFException e1) {
            e1.printStackTrace();
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
        try {
            YamlEncoder enc = new YamlEncoder(new FileOutputStream(YAMLFile));
            enc.writeObject(person);
            enc.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
