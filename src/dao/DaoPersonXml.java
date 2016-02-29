package dao;

import model.Person;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DaoPersonXml implements Dao {

    private final String XMLFile = "db.xml";
    ArrayList<Person> person = new ArrayList<Person>();

    @Override
    public ArrayList<Person> select() {
        try {
            XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(XMLFile));
            person = (ArrayList<Person>) xmlDecoder.readObject();

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
            XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(XMLFile));
            xmlEncoder.writeObject(person);
            xmlEncoder.flush();
            xmlEncoder.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
