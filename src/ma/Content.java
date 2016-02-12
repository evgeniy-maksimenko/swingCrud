package ma;

import ui.FileMenu;
import ui.WorkedPanel;

import javax.swing.*;

public class Content extends JFrame{
    public Content(){
        setTitle("App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,640,383);

        setJMenuBar(new FileMenu());
        add(new WorkedPanel());
        setVisible(true);
    }
}
