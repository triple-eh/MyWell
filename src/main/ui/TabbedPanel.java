package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedPanel extends JPanel {
    public TabbedPanel() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/logo.png");

        tabbedPane.addTab("Read Entries", icon, new JScrollPane(new ReadingPanel()),
                "Read existing entries");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent newEntryPanel = makePanel();
        tabbedPane.addTab("New Entry", icon, newEntryPanel,
                "Make a new journal entry");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent statsPanel = makePanel();
        tabbedPane.addTab("See stats", icon, statsPanel,
                "See your journaling stats");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makePanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));
        return panel;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
