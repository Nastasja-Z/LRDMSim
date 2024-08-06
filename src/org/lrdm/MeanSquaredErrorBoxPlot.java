package org.lrdm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MeanSquaredErrorBoxPlot {
    private static final String ROW_KEY = "Mean Squared Error";

    public static void display(Map<Integer, List<Double>> meanSquaredErrorMap) {
        JFrame f = new JFrame("BoxPlot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        for (Map.Entry<Integer, List<Double>> entry : meanSquaredErrorMap.entrySet()) {
            Integer key = entry.getKey();
            List<Double> value = entry.getValue();
            dataset.add(value, ROW_KEY, key.toString());
        }

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Box and Whisker Chart", ROW_KEY, "MSE", dataset, false);
        f.add(new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 480);
            }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

