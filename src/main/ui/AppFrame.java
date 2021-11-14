package ui;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    AppFrame() {
        this.setTitle("MyWell");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        this.add(new TabbedPanel(), BorderLayout.CENTER);
        this.setSize(750,750);
        this.setVisible(true);
    }
}
