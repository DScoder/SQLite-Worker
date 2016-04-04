import javax.swing.*;

/**
 * Created by DScoder on 04.04.2016.
 */
public class UIFrame extends JFrame {
    private float version = .01f;

    public UIFrame() {
        setSize(350, 400);
        setLocation(500,200);
        setTitle("SQLite Worker v." + version);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);






        setVisible(true);
    }
}
