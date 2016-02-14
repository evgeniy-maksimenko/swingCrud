package dao.socket;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DaoPersonMysql;
import model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class DaoPersonMysqlNetServer {
    ArrayList<NetClient> clients = new ArrayList<NetClient>();
    public DaoPersonMysql model = new DaoPersonMysql();

    public DaoPersonMysqlNetServer() {

        try {
            ServerSocket ss = new ServerSocket(7777);
            Timer tm = new Timer(50, new ActionReader());
            tm.start();
            System.out.println("Server started");

            while (true) {
                Socket socket = ss.accept();
                NetClient nc = new NetClient(socket);
                clients.add(nc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class NetClient {
        Socket socket;
        DataOutputStream out;
        DataInputStream in;

        public NetClient(Socket socket) throws IOException {
            this.socket = socket;
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        }
    }

    private class ActionReader implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                for (NetClient nc : clients) {
                    if (nc.in.available() > 0) {
                        String str = nc.in.readUTF();
                        String cmd = str.substring(0, str.indexOf(":"));
                        String inf = str.substring(str.indexOf(":") + 1);
                        String msg = "";

                        switch (cmd) {
                            case "select":
                                msg = "select:" + new Gson().toJson( model.select());
                                break;
                            case "insert":
                                Gson gson = new Gson();
                                Person person = gson.fromJson(inf, new TypeToken<Person>() {}.getType());
                                model.insert(person);
                                msg = "insert:" + inf;
                                break;
                            case "delete":
                                model.delete(Integer.parseInt(inf));
                                msg = "delete:" + inf;
                                break;
                        }

                        for (NetClient nn : clients) {
                            //if (nc != nn) {
                                nn.out.writeUTF(msg);
                            //}
                        }
                    }

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
