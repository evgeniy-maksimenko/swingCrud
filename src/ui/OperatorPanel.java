package ui;

import router.Router;
import model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorPanel extends JPanel {
    public Router router = null;
    JTable tbl = null;


    public OperatorPanel(Router router, JTable tbl) {
        this.router = router;
        this.tbl = tbl;
        setLayout(null);
        setBounds(0, 300, 625, 20);

        JButton btnCreate = new JButton("Create");
        btnCreate.addActionListener(new BtnActionCreate());
        btnCreate.setBounds(0, 0, 100, 20);
        add(btnCreate);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new BtnActionUpdate());
        btnUpdate.setBounds(120, 0, 100, 20);
        add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new BtnActionDelete());
        btnDelete.setBounds(240, 0, 100, 20);
        add(btnDelete);
    }

    private class BtnActionCreate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OperatorDialog operatorDialog = new OperatorDialog();
            operatorDialog.showDialog();
            if (operatorDialog.isResultOk) {
                Person model = operatorDialog.getPerson();
                router.grid.add(model);
                router.model.insert(model);
                router.fireTableDataChanged();
            }
        }
    }

    private class BtnActionDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = tbl.getSelectedRow();
            if (id != -1) {
                router.model.delete(router.grid.get(id).getId());
                router.grid.remove(id);
                router.fireTableDataChanged();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Please, you must select the row, before delete",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private class BtnActionUpdate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = tbl.getSelectedRow();
            if (id != -1) {
                OperatorDialog updateDialog = new OperatorDialog();
                updateDialog.setPerson(router.grid.get(id));
                updateDialog.showDialog();
                if (updateDialog.isResultOk) {
                    Person model = updateDialog.getPerson();
                    router.grid.remove(id);
                    router.grid.add(model);
                    router.model.update(model);
                    router.fireTableDataChanged();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Please, you must select the row, before update",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
