package ui.components.tabs;


import model.Journal;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.HashMap;

//A statistics panel displaying information about past entries
public class StatsPanel extends JPanel {
    ReadingPanel readingPanel;
    JPanel defaultPanel;
    JPanel chartPanel;

    public StatsPanel(ReadingPanel panel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        defaultPanel = new JPanel();
        chartPanel = new JPanel();
        defaultPanel.add(new JLabel("Import a journal or add an entry to see something here"));
        this.add(defaultPanel);
        this.add(chartPanel);
        this.validate();
        this.readingPanel = panel;
    }

    //REQUIRES: readingPanel.journal is not null
    //MODIFIES: this
    //EFFECTS: renders chart on this panel
    public void renderChart() {
        defaultPanel.setVisible(false);
        this.remove(chartPanel);
        chartPanel = createChart(createDataset(readingPanel.journal));
        this.add(chartPanel);
        this.validate();
        this.repaint();
    }

    //EFFECTS: crates a new bar chart with statistics from given dataset
    private ChartPanel createChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Frequency of entries by overall state - last year",
                "",
                "# of entries",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        return chartPanel;
    }

    //REQUIRES: journal is not null
    //EFFECTS: creates a new dataset with usage by overall state from given journal for the last year
    private CategoryDataset createDataset(Journal journal) {
        HashMap<String,Integer> entriesByState365 = journal.countEntriesForAllStates(365);
        String excellent = "Amazing";
        String good = "Good";
        String ok = "Ok";
        String bad = "Bad";
        String terrible = "Terrible";
        String rowKey = "entries";

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        dataset.addValue(entriesByState365.get(excellent.toLowerCase()), rowKey, excellent);
        dataset.addValue(entriesByState365.get(good.toLowerCase()), rowKey, good);
        dataset.addValue(entriesByState365.get(ok.toLowerCase()), rowKey, ok);
        dataset.addValue(entriesByState365.get(bad.toLowerCase()), rowKey, bad);
        dataset.addValue(entriesByState365.get(terrible.toLowerCase()), rowKey, terrible);

        return dataset;
    }

}
