package ui;

import javax.swing.*;

public class FileMenu extends JMenuBar {
    public FileMenu() {
        JMenu menu = new JMenu("File");
        add(menu);

        JMenuItem exit = new JMenuItem("Exit");
        menu.add(exit);
    }
}
