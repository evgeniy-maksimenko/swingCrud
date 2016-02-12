package ui;

import router.Router;

import javax.swing.*;

public class WorkedPanel extends JPanel {
    public WorkedPanel() {
        setLayout(null);

        Router router = new Router();
        JTable tbl = new JTable(router);
        JScrollPane pnl = new JScrollPane(tbl);
        pnl.setBounds(0, 0, 625, 300);
        add(pnl);
        OperatorPanel operPanel = new OperatorPanel(router, tbl);
        add(operPanel);
    }
}
