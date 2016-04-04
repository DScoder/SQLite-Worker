import javax.swing.*;
import java.awt.*;

/**
 * Created by DScoder on 04.04.2016.
 */
public class UIFrame extends JFrame {
    private final float VERSION = .01f;

    public UIFrame() {
        setSize(350, 400);
        setLocation(500,200);
        setTitle("SQLite Worker v." + VERSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));


        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.pink);
        add(textArea);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
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

        buttonPanel.add(dbPanel);
        buttonPanel.add(sendButton);
        buttonPanel.add(userPanel);

        add(buttonPanel);


        setVisible(true);
    }
}
