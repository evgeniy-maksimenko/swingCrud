package dao.socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.Dao;
import model.Person;
import router.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class DaoPersonMysqlNetClient implements Dao {
    ArrayList<Person> person;
    DataOutputStream out;
    DataInputStream in;
    Router router;

    public DaoPersonMysqlNetClient(Router router) {
        this.router = router;
        try {
            Socket socket = new Socket("localhost", 7777);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            Timer tm = new Timer(50, new actionRead());
            tm.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ArrayList<Person> select() {
        ArrayList<Person> person = new ArrayList<Person>();

        try {
            out.writeUTF("select:");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return person;
    }


    @Override
    public void insert(Person model) {
        try {
            String msg = new Gson().toJson(model);
            out.writeUTF("insert:" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person model) {
        delete(model.getId());
        insert(model);
    }

    @Override
    public void delete(int id) {
        try {
            out.writeUTF("delete:" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class actionRead implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (in.available() > 0) {
                    String str = in.readUTF();

                    String cmd = str.substring(0, str.indexOf(":"));
                    String msg = str.substring(str.indexOf(":") + 1);

                    Gson gson = new Gson();

                    switch (cmd) {
                        case "select":
                            person = gson.fromJson(msg, new TypeToken<ArrayList<Person>>() {}.getType());
                            for (Person model : person) {
                                router.grid.add(model);
                                router.fireTableDataChanged();
                            }
                            break;
                        case "insert":
                            break;
                        case "delete":
                            break;
                    }

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
