package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by DScoder on 04.04.2016.
 */
public class ButtonPanel extends JPanel {

    public ButtonPanel(LayoutManager layout) {
        super(layout);

//        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JPanel dbPanel = new JPanel(new GridLayout());
        JComboBox dbBox = new JComboBox();
        JButton createTable = new JButton("CREATE TABLE");
        dbPanel.add(dbBox);
        dbPanel.add(createTable);

        JButton sendButton = new JButton("SHOW DATA OF TABLE");

        JPanel userPanel = new JPanel(new GridLayout());
        JButton addUser = new JButton("ADD USER");
        JButton deleteUser = new JButton("DELETE USER");
        userPanel.add(addUser);
        userPanel.add(deleteUser);

        add(dbPanel);
        add(sendButton);
        add(userPanel);
    }
}
