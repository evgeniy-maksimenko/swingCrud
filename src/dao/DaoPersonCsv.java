package dao;

import model.Person;
import util.CsvFileReader;
import util.CsvFileWriter;

import java.io.File;
import java.util.ArrayList;


public class DaoPersonCsv implements Dao {

    ArrayList<Person> person = new ArrayList<Person>();
    private final String CSVFile = "db.csv";

    @Override
    public ArrayList<Person> select() {
        CsvFileReader csvFileReader = new CsvFileReader(person);
        person = csvFileReader.readCsvFile(CSVFile);
        return person;
    }

    @Override
    public void insert(Person model) {
        person.add(model);
        writeToFile(person);
    }

    @Override
    public void update(Person model) {

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
        CsvFileWriter csvFileWriter = new CsvFileWriter(person);
        csvFileWriter.writeCsvFile(CSVFile);
    }
}
