package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by DScoder on 04.04.2016.
 */
public class TextFrame extends JPanel {

    public TextFrame(LayoutManager layout) {
        super(layout);
        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.pink);
        add(textArea);
    }
}
