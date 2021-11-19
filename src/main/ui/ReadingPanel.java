package ui;

import model.Feeling;
import model.Journal;
import model.JournalEntry;
import model.Sensation;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ReadingPanel extends JPanel implements ActionListener {
    JButton loadDataButton;
    JPanel entriesPanel;
    Journal journal;

    ReadingPanel() {
        journal = new Journal();
        this.setLayout(new BorderLayout());
        this.add(loadingPanel(), BorderLayout.NORTH);
        //this.add(entriesPanel(), BorderLayout.CENTER);
    }

    public Journal getJournal() {
        return this.journal;
    }

    public void setJournal(Journal j) {
        this.journal = j;
    }

    private JPanel entriesPanel(Journal journal) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        for (JournalEntry entry : journal.getEntries()) {
            panel.add(renderEntry(entry));
            panel.add(Box.createVerticalStrut(10));
        }
        return panel;
    }

    private JPanel loadingPanel() {
        JPanel panel = new JPanel();
        loadDataButton = new JButton("Load journal from file");
        loadDataButton.addActionListener(this);
        panel.add(loadDataButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadDataButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("./data"));
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                String filePath = new File(fileChooser.getSelectedFile().getAbsolutePath()).toString();
                this.setJournal(getJournalFromStorage(filePath));
                renderJournal(this.journal);
            }
        }
    }

    public void renderJournal(Journal journal) {
        if (entriesPanel != null) {
            this.removeEntries();
        }
        this.entriesPanel = entriesPanel(journal);
        this.add(entriesPanel);
        validate();
        repaint();
    }

    public JPanel renderEntry(JournalEntry e) {
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.PAGE_AXIS));
        JPanel sensationsPanel = createSensationPanel(e.getSensations());
        JPanel feelingsPanel = createFeelingPanel(e.getFeelings());
        entryPanel.add(new JLabel("Date: " + e.getDate().toString()));
        entryPanel.add(new JLabel("Your overall state was: " + e.getOverallState()));
        entryPanel.add(sensationsPanel);
        entryPanel.add(Box.createVerticalStrut(10));
        entryPanel.add(feelingsPanel);
        entryPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return entryPanel;
    }

    public JPanel createSensationPanel(List<Sensation> sensations) {
        JPanel sensationsPanel = new JPanel();
        sensationsPanel.setLayout(new BoxLayout(sensationsPanel, BoxLayout.PAGE_AXIS));
        for (Sensation s : sensations) {
            JLabel sensationLabel = new JLabel(createSensationText(s));
            sensationsPanel.add(sensationLabel);
            sensationsPanel.add(Box.createVerticalStrut(5));
        }
        return sensationsPanel;
    }

    private String createSensationText(Sensation s) {
        return "<html>" + s.print().replaceAll("[\\n]","<br>");
    }

    public JPanel createFeelingPanel(List<Feeling> feelings) {
        JPanel feelingsPanel = new JPanel();
        feelingsPanel.setLayout(new BoxLayout(feelingsPanel, BoxLayout.PAGE_AXIS));
        for (Feeling f : feelings) {
            JLabel feelingLabel = new JLabel(createFeelingText(f));
            feelingsPanel.add(feelingLabel);
            feelingsPanel.add(Box.createVerticalStrut(5));
        }
        return feelingsPanel;
    }

    private String createFeelingText(Feeling f) {
        return "<html>" + f.print().replaceAll("[\\n]","<br>");
    }

    private Journal getJournalFromStorage(String filePath) {
        //final String JSON_STORE = "./data/journal.json";
        try {
            JsonReader jsonReader = new JsonReader(filePath);
            return jsonReader.read();
        } catch (IOException e) {
            System.out.println("Exception");
            return null;
        }
    }

    public void removeEntries() {
        this.remove(entriesPanel);
    }
}
