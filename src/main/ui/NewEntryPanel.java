package ui;

import model.Feeling;
import model.Journal;
import model.JournalEntry;
import model.Sensation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class NewEntryPanel extends JPanel implements ActionListener {
    JPanel inputPanel;
    JPanel createEntryPanel;
    JPanel previewPanel;
    JPanel controlPanel;
    JPanel entryPanel;
    JPanel optionsPanel;
    ReadingPanel readingPanel;
    JournalEntry entry;
    JButton createEntryButton = new JButton();
    JButton addSensationButton = new JButton();
    JButton addFeelingButton = new JButton();
    JButton clearButton = new JButton();
    JButton addEntryButton = new JButton();
    JComboBox bodyPartsBox;
    JComboBox overallStatesList;
    JComboBox sensationTypeList;
    ButtonGroup sensationIntensity = new ButtonGroup();
    JTextArea sensationNote;
    JPanel sensationPanel;
    JTextField feelingName;
    ButtonGroup feelingIntensity = new ButtonGroup();
    JTextArea feelingNote;
    JPanel feelingPanel;
    Map<JButton,Runnable> commands = new HashMap<>();

    NewEntryPanel(ReadingPanel readingPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inputPanel = new JPanel(new GridLayout(0,2));
        inputPanel.add(new JScrollPane(createEntryPanel()));
        inputPanel.add(new JScrollPane(createPreviewPanel()));
        inputPanel.setMaximumSize(new Dimension(750,650));
        this.add(inputPanel);
        this.add(createControlPanel());
        this.readingPanel = readingPanel;
    }

    private JPanel createEntryPanel() {
        createEntryPanel = new JPanel();
        createEntryPanel.setLayout(new BoxLayout(createEntryPanel, BoxLayout.PAGE_AXIS));
        createEntryPanel.add(createStatePanel());
        createEntryPanel.add(Box.createVerticalStrut(30));
        createEntryPanel.add(createSensationPanel());
        createEntryPanel.add(Box.createVerticalStrut(30));
        createEntryPanel.add(createFeelingPanel());
        return createEntryPanel;
    }

    private JPanel createPreviewPanel() {
        previewPanel = new JPanel();
        entryPanel = new JPanel();
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.PAGE_AXIS));
        previewPanel.setBorder(BorderFactory.createTitledBorder("Preview current entry"));
        return previewPanel;
    }

    private JPanel createControlPanel() {
        controlPanel = new JPanel(new GridLayout(1,2));
        controlPanel.add(createButton("Clear entry",clearButton,this::handleClearEntry));
        controlPanel.add(createButton("Add entry to journal",addEntryButton,this::handleAddEntryToJournal));
        controlPanel.setMaximumSize(new Dimension(750,30));
        return controlPanel;
    }

    private JPanel createStatePanel() {
        optionsPanel = new JPanel(new GridLayout(0, 1));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Note your overall state (required)"));
        optionsPanel.add(createLabel("What is the overall state of your well-being today?"));
        optionsPanel.add(createOverallStatesList());
        optionsPanel.add(createButton("Add state",createEntryButton,this::handleCreateEntry));
        return optionsPanel;
    }
    
    private JPanel createSensationPanel() {
        sensationPanel = new JPanel(new GridLayout(0,1));
        sensationPanel.setBorder(BorderFactory.createTitledBorder("Note your sensation"));
        sensationPanel.add(createLabel("Enter body part where you feel a sensation"));
        sensationPanel.add(bodyPartList());
        sensationPanel.add(createLabel("How does the sensation feel?"));
        sensationPanel.add(sensationTypeList());
        sensationPanel.add(createLabel("Specify how strongly you feel this sensation"));
        sensationPanel.add(createRadioPanelForGroup(this.sensationIntensity));
        sensationPanel.add(createLabel("Add any additional note you would like to save"));
        sensationNote = new JTextArea();
        sensationPanel.add(sensationNote);
        sensationPanel.add(createButton("Add sensation",addSensationButton,this::handleAddSensation));
        return sensationPanel;
    }
    
    private JPanel createFeelingPanel() {
        feelingPanel = new JPanel(new GridLayout(0,1));
        feelingPanel.setBorder(BorderFactory.createTitledBorder("Note your feeling"));
        feelingPanel.add(createLabel("How are you feeling right now? (e.g., happy)"));
        feelingName = new JTextField();
        feelingPanel.add(feelingName);
        feelingPanel.add(createLabel("Specify how strongly you are experiencing this feeling"));
        feelingPanel.add(createRadioPanelForGroup(feelingIntensity));
        feelingPanel.add(createLabel("Add any additional note you would like to save"));
        feelingNote = new JTextArea();
        feelingPanel.add(feelingNote);
        feelingPanel.add(createButton("Add feeling",addFeelingButton,this::handleAddFeeling));
        return feelingPanel;
    }

    private JComboBox createOverallStatesList() {
        String[] overallStates = {"amazing","good","ok","bad","terrible"};
        overallStatesList = new JComboBox(overallStates);
        return overallStatesList;
    }

    private JComboBox bodyPartList() {
        String[] bodyParts = {"head","face","neck", "right shoulder", "left shoulder", "right arm", "left arm","chest",
            "upper back", "middle back", "lower back", "stomach", "right hip", "left hip", "right leg", "left leg",
            "right foot", "left foot"};
        bodyPartsBox = new JComboBox(bodyParts);
        return bodyPartsBox;
    }

    private JComboBox sensationTypeList() {
        String[] types = {"pleasant","unpleasant","neutral"};
        sensationTypeList = new JComboBox(types);
        return sensationTypeList;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        return label;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setMnemonic(KeyEvent.VK_P);
        radioButton.setActionCommand(text);
        return radioButton;
    }

    private JPanel createRadioPanelForGroup(ButtonGroup group) {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        for (int i = 1; i < 6; i++) {
            JRadioButton rb = createRadioButton(Integer.toString(i));
            group.add(rb);
            panel.add(rb);
        }
        return panel;
    }

    private JButton createButton(String label, JButton button, Runnable command) {
        button.setText(label);
        button.addActionListener(this);
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        commands.put(button, command);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton button : commands.keySet()) {
            if (e.getSource() == button) {
                commands.get(button).run();
            }
        }
    }

    private void handleCreateEntry() {
        createEntryFromInput();
        createEntryButton.setText("Update overall state");
    }

    private void handleAddSensation() {
        if (!validateSensationEntry()) {
            createInvalidInputDialog();
        } else {
            addSensationFromInput();
            createEntryFromInput();
        }
    }

    private void handleAddFeeling() {
        if (!validateFeelingEntry()) {
            createInvalidInputDialog();
        } else {
            addFeelingFromInput();
            createEntryFromInput();
        }
    }

    private void handleClearEntry() {
        entry = null;
        previewPanel.remove(entryPanel);
        previewPanel.validate();
        previewPanel.repaint();
    }

    private void handleAddEntryToJournal() {
        readingPanel.getJournal().addJournalEntry(entry);
        readingPanel.renderJournal(readingPanel.getJournal());
    }

    private boolean validateSensationEntry() {
        return sensationIntensity.getSelection() != null;
    }

    private boolean validateFeelingEntry() {
        return feelingIntensity.getSelection() != null;
    }

    private void addSensationFromInput() {
        Sensation s = new Sensation(bodyPartsBox.getSelectedItem().toString());
        s.setSensationType(sensationTypeList.getSelectedItem().toString());
        s.setIntensity(Integer.parseInt(sensationIntensity.getSelection().getActionCommand()));
        if (sensationNote.getText().length() > 0) {
            s.setNote(sensationNote.getText());
        }
        entry.addSensation(s);
    }

    private void addFeelingFromInput() {
        Feeling f = new Feeling(feelingName.getText());
        f.setIntensity(Integer.parseInt(feelingIntensity.getSelection().getActionCommand()));
        if (feelingNote.getText().length() > 0) {
            f.setNote(feelingName.getText());
        }
        entry.addFeeling(f);
    }

    private void createInvalidInputDialog() {
        JOptionPane.showConfirmDialog(previewPanel,
                "Please, check your input. Only the note can be left blank","Invalid input",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    private void createEntryFromInput() {
        if (entry == null) {
            entry = new JournalEntry(overallStatesList.getSelectedItem().toString());
        }
        entry.setOverallState(overallStatesList.getSelectedItem().toString());
        previewPanel.remove(entryPanel);
        entryPanel = readingPanel.renderEntry(entry);
        previewPanel.add(entryPanel);
        previewPanel.validate();
    }

}
