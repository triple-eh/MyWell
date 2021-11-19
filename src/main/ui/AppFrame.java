package ui;

import model.Journal;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    AppFrame() {
        ImageIcon logo = new ImageIcon("src/images/logo.png");
        this.setTitle("MyWell");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        this.add(new TabbedPanel(), BorderLayout.CENTER);
        this.setSize(750,750);
        this.setVisible(true);
    }
}
