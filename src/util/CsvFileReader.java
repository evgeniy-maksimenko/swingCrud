package util;


import model.Person;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvFileReader {

    ArrayList<Person> conf;
    public CsvFileReader(ArrayList<Person> conf) {
        this.conf = conf;
    }

    private static final String DELIMITER = ";";

    private final int ID    = 0;
    private final int FNAME = 1;
    private final int LNAME = 2;
    private final int AGE   = 3;


    public ArrayList<Person> readCsvFile(String fileName) {

        BufferedReader fileReader = null;

        try {
            String line;
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(DELIMITER);
                if (tokens.length > 0) {
                    conf.add(new Person(
                            Integer.parseInt(tokens[ID]),
                            String.valueOf(tokens[FNAME]),
                            String.valueOf(tokens[LNAME]),
                            Integer.parseInt(tokens[AGE])
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader");
                e.printStackTrace();
            }
        }
        return conf;
    }
}