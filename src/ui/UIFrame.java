package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by DScoder on 04.04.2016.
 */
public class UIFrame extends JFrame {
    private final float VERSION = .01f;
    TextFrame textArea = new TextFrame(new BorderLayout());
    ButtonPanel buttonPanel = new ButtonPanel(new GridLayout(3,1));


    public UIFrame() {
        setSize(350, 400);
        setLocation(500,200);
        setTitle("SQLite Worker v." + VERSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        add(textArea);
        add(buttonPanel);

        setVisible(true);
    }
}
