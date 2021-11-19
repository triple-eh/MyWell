package ui.components.tabs;

import model.Feeling;
import model.JournalEntry;
import model.Sensation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

//A panel for creating new journal entries
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

    public NewEntryPanel(ReadingPanel readingPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inputPanel = new JPanel(new GridLayout(0,2));
        inputPanel.add(new JScrollPane(createEntryPanel()));
        inputPanel.add(new JScrollPane(createPreviewPanel()));
        inputPanel.setMaximumSize(new Dimension(750,650));
        this.add(inputPanel);
        this.add(createControlPanel());
        this.readingPanel = readingPanel;
        entry = new JournalEntry(overallStatesList.getSelectedItem().toString());
    }

    // MODIFIES: this
    // EFFECTS creates a holding panel for new journal entry GUI logic
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

    // MODIFIES: this
    // EFFECTS creates panel for holding a preview of an unsubmitted journal entry
    private JPanel createPreviewPanel() {
        previewPanel = new JPanel();
        entryPanel = new JPanel();
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.PAGE_AXIS));
        previewPanel.setBorder(BorderFactory.createTitledBorder("Preview current entry"));
        return previewPanel;
    }

    // MODIFIES this
    // EFFECTS creates a panel for holding buttons to clear or add the entry
    private JPanel createControlPanel() {
        controlPanel = new JPanel(new GridLayout(1,2));
        controlPanel.add(createButton("Clear entry",clearButton,this::handleClearEntry));
        controlPanel.add(createButton("Add entry to journal",addEntryButton,this::handleAddEntryToJournal));
        controlPanel.setMaximumSize(new Dimension(750,30));
        return controlPanel;
    }

    // MODIFIES this
    // EFFECTS creates a panel for entering overall state information
    private JPanel createStatePanel() {
        optionsPanel = new JPanel(new GridLayout(0, 1));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Note your overall state (required)"));
        optionsPanel.add(createLabel("What is the overall state of your well-being today?"));
        optionsPanel.add(createOverallStatesList());
        optionsPanel.add(createButton("Add state",createEntryButton,this::handleCreateEntry));
        return optionsPanel;
    }

    // MODIFIES this
    // EFFECTS creates a panel for adding sensations
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

    // MODIFIES this
    // EFFECTS creates a panel for adding feelings
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

    // MODIFIES this
    // EFFECTS initializes a JComboBox with overall states and returns it
    private JComboBox createOverallStatesList() {
        String[] overallStates = {"amazing","good","ok","bad","terrible"};
        overallStatesList = new JComboBox(overallStates);
        return overallStatesList;
    }

    // MODIFIES this
    // EFFECTS initializes a JComboBox with body parts and returns it
    private JComboBox bodyPartList() {
        String[] bodyParts = {"head","face","neck", "right shoulder", "left shoulder", "right arm", "left arm","chest",
            "upper back", "middle back", "lower back", "stomach", "right hip", "left hip", "right leg", "left leg",
            "right foot", "left foot"};
        bodyPartsBox = new JComboBox(bodyParts);
        return bodyPartsBox;
    }

    // MODIFIES this
    // EFFECTS initializes a JComboBox with sensations types and returns it
    private JComboBox sensationTypeList() {
        String[] types = {"pleasant","unpleasant","neutral"};
        sensationTypeList = new JComboBox(types);
        return sensationTypeList;
    }

    // EFFECTS creates new JLabel with a given text and returns it
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        return label;
    }

    // EFFECTS creates a new JRadioButton with given text and associated action command and returns it;
    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setMnemonic(KeyEvent.VK_P);
        radioButton.setActionCommand(text);
        return radioButton;
    }

    // EFFECTS creates a JPanel to hold a provided ButtonGroup and returns it
    private JPanel createRadioPanelForGroup(ButtonGroup group) {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        for (int i = 1; i < 6; i++) {
            JRadioButton rb = createRadioButton(Integer.toString(i));
            group.add(rb);
            panel.add(rb);
        }
        return panel;
    }

    // MODIFIES this
    // EFFECTS creates a new button with associated action, stores it in a map, and return it
    private JButton createButton(String label, JButton button, Runnable command) {
        button.setText(label);
        button.addActionListener(this);
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        commands.put(button, command);
        return button;
    }

    // EFFECTS listens for actions performed on components and executes appropriate actions
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton button : commands.keySet()) {
            if (e.getSource() == button) {
                commands.get(button).run();
            }
        }
    }

    // EFFECTS creates a new journal entry from corresponding components in this
    private void handleCreateEntry() {
        createEntryFromInput();
        createEntryButton.setText("Update overall state");
    }

    // EFFECTS adds a sensation from corresponding components in this
    private void handleAddSensation() {
        if (!validateSensationEntry()) {
            createInvalidInputDialog();
        } else {
            addSensationFromInput();
            createEntryFromInput();
        }
    }

    // EFFECTS adds a feeling from corresponding components in this
    private void handleAddFeeling() {
        if (!validateFeelingEntry()) {
            createInvalidInputDialog();
        } else {
            addFeelingFromInput();
            createEntryFromInput();
        }
    }

    // MODIFIES this
    // EFFECTS clears the preview of the current entry
    private void handleClearEntry() {
        entry = new JournalEntry(overallStatesList.getSelectedItem().toString());
        previewPanel.remove(entryPanel);
        previewPanel.validate();
        previewPanel.repaint();
    }

    // MODIFIES readingPanel
    // EFFECTS adds current entry to the journal and renders it on the reading panel
    private void handleAddEntryToJournal() {
        readingPanel.getJournal().addJournalEntry(entry);
        readingPanel.renderJournal(readingPanel.getJournal());
    }

    // EFFECTS returns true if input is valid for creating a new sensation
    private boolean validateSensationEntry() {
        return sensationIntensity.getSelection() != null;
    }

    // EFFECTS returns true if input is valid for creating a new feeling
    private boolean validateFeelingEntry() {
        return feelingIntensity.getSelection() != null;
    }

    // MODIFIES this
    // EFFECTS adds a sensation to the current entry
    private void addSensationFromInput() {
        Sensation s = new Sensation(bodyPartsBox.getSelectedItem().toString());
        s.setSensationType(sensationTypeList.getSelectedItem().toString());
        s.setIntensity(Integer.parseInt(sensationIntensity.getSelection().getActionCommand()));
        if (sensationNote.getText().length() > 0) {
            s.setNote(sensationNote.getText());
        }
        entry.addSensation(s);
    }

    // MODIFIES this
    // EFFECTS adds a feeling to the current entry
    private void addFeelingFromInput() {
        Feeling f = new Feeling(feelingName.getText());
        f.setIntensity(Integer.parseInt(feelingIntensity.getSelection().getActionCommand()));
        if (feelingNote.getText().length() > 0) {
            f.setNote(feelingName.getText());
        }
        entry.addFeeling(f);
    }

    // EFFECTS displays a dialogue windows for invalid input
    private void createInvalidInputDialog() {
        JOptionPane.showConfirmDialog(previewPanel,
                "Please, check your input. Only the note can be left blank","Invalid input",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES this, readingPanel
    // EFFECTS renders a preview for a new entry from given inputs of components in this
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
