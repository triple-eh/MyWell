package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedPanel extends JPanel {
    public TabbedPanel() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon readIcon = new ImageIcon("src/images/read.png");
        ReadingPanel readingPanel = new ReadingPanel();
        tabbedPane.addTab("Read Entries", readIcon, new JScrollPane(readingPanel),
                "Read existing entries");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        ImageIcon writeIcon = new ImageIcon("src/images/write.png");
        tabbedPane.addTab("New Entry", writeIcon, new NewEntryPanel(readingPanel),
                "Make a new journal entry");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent statsPanel = makePanel();
        ImageIcon statsIcon = new ImageIcon("src/images/stats.png");
        tabbedPane.addTab("See stats", statsIcon, statsPanel,
                "See your journaling stats");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makePanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));
        return panel;
    }
}
