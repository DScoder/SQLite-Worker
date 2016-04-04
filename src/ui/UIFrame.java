package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DScoder on 04.04.2016.
 */
public class UIFrame extends JFrame {
    private final float VERSION = .01f;
    DBWorker worker = new DBWorker();
    protected String dbName = "PhoneList";


    public UIFrame() {
        setSize(350, 400);
        setLocation(500, 200);
        setTitle("SQLite Worker v." + VERSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        final JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.pink);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JPanel dbPanel = new JPanel(new GridLayout());
        JComboBox dbBox = new JComboBox();
        JButton createTable = new JButton("CREATE DATA BASE");
        createTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("create Table button touch!");
                textArea.setText("");
                if (worker.createDB(dbName))
                    textArea.append("DB created \n");
                else
                    textArea.append("Error of creating DB\n");
            }
        });
        dbPanel.add(dbBox);
        dbPanel.add(createTable);

        JButton infoButton = new JButton("SHOW DATA OF TABLE");
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info button touch!");
                worker.reedDB(dbName);
                textArea.setText("");
                if (worker.info.size() > 0) {
                    for (int i = 0; i < worker.info.size(); i++) {
                        textArea.append(worker.info.get(i) + "\n");
                    }
                    worker.info.clear();
                } else
                    textArea.append("Table is void!");
            }
        });

        JPanel userPanel = new JPanel(new GridLayout());
        JButton addUser = new JButton("ADD USER");
        JButton deleteUser = new JButton("DELETE USER");
        userPanel.add(addUser);
        userPanel.add(deleteUser);

        buttonPanel.add(dbPanel);
        buttonPanel.add(infoButton);
        buttonPanel.add(userPanel);

        add(textArea);
        add(buttonPanel);


        setVisible(true);
    }
}
