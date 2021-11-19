package ui.components;


import ui.components.tabs.NewEntryPanel;
import ui.components.tabs.ReadingPanel;
import ui.components.tabs.StatsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// A tabbed panel for accessing components of the journaling app
public class TabbedPanel extends JPanel {
    ReadingPanel readingPanel;
    NewEntryPanel newEntryPanel;
    StatsPanel statsPanel;

    public TabbedPanel() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        addReadingPanel(tabbedPane);
        addNewEntryPanel(tabbedPane);
        addStatsPanel(tabbedPane);
        readingPanel.setStatsPanel(statsPanel);
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    // MODIFIES this
    // EFFECTS adds the reading panel JPanel
    private void addReadingPanel(JTabbedPane tabbedPane) {
        ImageIcon readIcon = new ImageIcon("src/images/read.png");
        readingPanel = new ReadingPanel();
        tabbedPane.addTab("Read Entries", readIcon, new JScrollPane(readingPanel),
                "Read existing entries");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    }

    // MODIFIES this
    // EFFECTS adds the new entry panel JPanel
    private void addNewEntryPanel(JTabbedPane tabbedPane) {
        ImageIcon writeIcon = new ImageIcon("src/images/write.png");
        newEntryPanel = new NewEntryPanel(readingPanel);
        tabbedPane.addTab("New Entry", writeIcon, newEntryPanel,
                "Make a new journal entry");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    }

    // MODIFIES this
    // EFFECTS adds the stats panel JPanel
    private void addStatsPanel(JTabbedPane tabbedPane) {
        ImageIcon statsIcon = new ImageIcon("src/images/stats.png");
        statsPanel = new StatsPanel(readingPanel);
        tabbedPane.addTab("See stats", statsIcon, statsPanel,
                "See your journaling stats");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }
}
