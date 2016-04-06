package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (worker.isOpen()) {
                    worker.closeDB();
                }
            }
        });


        JPanel infoPanel = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.pink);

        final JTable table = new JTable();
        final DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        table.setModel(tableModel);
        infoPanel.add(textArea, BorderLayout.NORTH);
        infoPanel.add(table, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JPanel dbPanel = new JPanel(new GridLayout());
        JComboBox dbBox = new JComboBox();

        JButton openDB = new JButton("OPEN DB");
        openDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("open DB button touch!");
                textArea.setText("");
                if (!worker.isOpen()) {
                    worker.openDB(dbName);
                    textArea.append("DB " + dbName + " Opened");
                } else
                    textArea.append("This DB is already open!");
            }
        });
        JButton closeDB = new JButton("CLOSE DB");
        closeDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("close DB button touch!");
                textArea.setText("");
                if (worker.isOpen()) {
                    worker.closeDB();
                    textArea.append("DB " + dbName + " was close");
                    tableModel.setRowCount(0);
                } else
                    textArea.append("All DB already closed!");
            }
        });
        dbPanel.add(dbBox);
        dbPanel.add(openDB);
        dbPanel.add(closeDB);


        JButton infoButton = new JButton("SHOW DATA OF TABLE");
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info button touch!");
                textArea.setText("");
                if (worker.isOpen()) {
                    worker.reedDB(dbName);
                    if (worker.infoList.size() > 0) {
                        tableModel.setRowCount(0);
                        tableModel.setColumnIdentifiers(worker.header);
                        for (int i = 0; i < worker.infoList.size(); i++) {
                            tableModel.addRow(worker.infoList.get(i));
                        }
                        textArea.append("Table from " + dbName + " DB was read!");
                    } else
                        textArea.append("Table is void!");
                } else
                    textArea.append("DB closed!");
            }
        });


        JPanel userPanel = new JPanel(new GridLayout());
        JButton addUser = new JButton("ADD USER");
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add data button touch!");
                textArea.setText("");
                if (worker.isOpen()) {
                    worker.addData(dbName);
                    worker.reedDB(dbName);
                    tableModel.setRowCount(0);
                    tableModel.setColumnIdentifiers(worker.header);
                    for (int i = 0; i < worker.infoList.size(); i++) {
                        tableModel.addRow(worker.infoList.get(i));
                    }
                    textArea.append("Data added!");
                } else
                    textArea.append("DB closed!");
            }
        });
        JButton deleteUser = new JButton("DELETE USER");
        userPanel.add(addUser);
        userPanel.add(deleteUser);

        buttonPanel.add(dbPanel);
        buttonPanel.add(infoButton);
        buttonPanel.add(userPanel);

        add(infoPanel);
        add(buttonPanel);


        setVisible(true);
    }
}
