package ui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorDialog extends JDialog{

    public boolean isResultOk = false;
    JTextField input_id;
    JTextField input_fname;
    JTextField input_lname;
    JTextField input_age;

    public OperatorDialog() {
        setLayout(null);
        setBounds(200,200,400,400);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(null);
        myPanel.setBounds(0,0,400,400);
        myPanel.setBackground(Color.white);


        JLabel label_id = new JLabel("id:");
        label_id.setBounds(20,20,70,20);
        myPanel.add(label_id);
        input_id = new JTextField();
        input_id.setBounds(70,20,70,20);
        myPanel.add(input_id);

        JLabel label_fname = new JLabel("fname:");
        label_fname.setBounds(20,70,70,20);
        myPanel.add(label_fname);
        input_fname = new JTextField();
        input_fname.setBounds(70,70,70,20);
        myPanel.add(input_fname);

        JLabel label_lname = new JLabel("lname:");
        label_lname.setBounds(20,120,70,20);
        myPanel.add(label_lname);
        input_lname = new JTextField();
        input_lname.setBounds(70,120,70,20);
        myPanel.add(input_lname);

        JLabel label_age = new JLabel("age:");
        label_age.setBounds(20,170,70,20);
        myPanel.add(label_age);
        input_age = new JTextField();
        input_age.setBounds(70,170,70,20);
        myPanel.add(input_age);

        JButton okButton = new JButton("Ok");
        okButton.setBounds(20,200,100,20);
        okButton.addActionListener(new okActionButton());
        myPanel.add(okButton);


        add(myPanel);
        setModal(true);
    }

    public void showDialog() {
        setVisible(true);
    }

    public Person getPerson() {
        Person model = new Person();
        model.setId(Integer.parseInt(input_id.getText()));
        model.setFname(input_fname.getText());
        model.setLname(input_lname.getText());
        model.setAge(Integer.parseInt(input_age.getText()));
        return model;
    }

    public void setPerson(Person model) {
        input_id.setText("" + model.id);
        input_fname.setText(model.fname);
        input_lname.setText(model.lname);
        input_age.setText("" + model.age);
    }

    private class okActionButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isResultOk = true;
            setVisible(false);
        }
    }
}
