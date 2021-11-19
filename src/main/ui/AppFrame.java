package ui;

import ui.components.TabbedPanel;

import javax.swing.*;
import java.awt.*;

// Main JFrame for MyWell app
public class AppFrame extends JFrame {

    AppFrame() {
        ImageIcon logo = new ImageIcon("./images/logo.png");
        this.setTitle("MyWell");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new TabbedPanel(), BorderLayout.CENTER);
        this.setSize(750,750);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AppFrame();
    }
}
