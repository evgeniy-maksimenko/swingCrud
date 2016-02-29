package util;

import model.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileWriter {
    ArrayList<Person> conf;

    public CsvFileWriter(ArrayList<Person> conf) {
        this.conf = conf;
    }


    private static final String DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "id,fname,lname,age";

    public void writeCsvFile(String fileName) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(FILE_HEADER.toString());
            fileWriter.write(NEW_LINE_SEPARATOR);

            for (Person aFrame : conf) {
                fileWriter.write(String.valueOf(aFrame.id));
                fileWriter.write(DELIMITER);
                fileWriter.write(String.valueOf(aFrame.fname));
                fileWriter.write(DELIMITER);
                fileWriter.write(String.valueOf(aFrame.lname));
                fileWriter.write(DELIMITER);
                fileWriter.write(String.valueOf(aFrame.age));
                fileWriter.write(NEW_LINE_SEPARATOR);
            }


            System.out.println("CSV file was created successfully");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
            }

        }
    }
}