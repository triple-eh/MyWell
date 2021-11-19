package ui;


import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class TabbedPanel extends JPanel {
    ReadingPanel readingPanel;
    NewEntryPanel newEntryPanel;
    StatsPanel statsPanel;

    public TabbedPanel() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon readIcon = new ImageIcon("src/images/read.png");
        readingPanel = new ReadingPanel();
        tabbedPane.addTab("Read Entries", readIcon, new JScrollPane(readingPanel),
                "Read existing entries");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        ImageIcon writeIcon = new ImageIcon("src/images/write.png");
        newEntryPanel = new NewEntryPanel(readingPanel);
        tabbedPane.addTab("New Entry", writeIcon, newEntryPanel,
                "Make a new journal entry");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        ImageIcon statsIcon = new ImageIcon("src/images/stats.png");
        statsPanel = new StatsPanel(readingPanel);
        tabbedPane.addTab("See stats", statsIcon, statsPanel,
                "See your journaling stats");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        readingPanel.setStatsPanel(statsPanel);
        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        System.out.println("success");
    }
}
