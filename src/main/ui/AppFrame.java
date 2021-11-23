package ui;

import ui.components.TabbedPanel;
import model.EventLog;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Main JFrame for MyWell app
public class AppFrame extends JFrame {

    AppFrame() {
        ImageIcon logo = new ImageIcon("./images/logo.png");
        this.setTitle("MyWell");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCloseAction();
        this.add(new TabbedPanel(), BorderLayout.CENTER);
        this.setSize(750,750);
        this.setVisible(true);
    }

    public void setCloseAction() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                super.windowClosed(e);
            }
        });
    }

    public static void main(String[] args) {
        new AppFrame();
    }
}
