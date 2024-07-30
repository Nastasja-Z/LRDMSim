package org.lrdm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MeanSquaredErrorBoxPlot {
    private static final String ROW_KEY = "Mean Squared Error";

    public static void display(List<Double> listOfMeanSquaredErrors){
        JFrame f = new JFrame("BoxPlot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        dataset.add(listOfMeanSquaredErrors, ROW_KEY, "Test");
        dataset.add(listOfMeanSquaredErrors, ROW_KEY, "Test2");
        dataset.add(listOfMeanSquaredErrors, ROW_KEY, "Test3");
        dataset.add(listOfMeanSquaredErrors, ROW_KEY, "Test4");
        dataset.add(listOfMeanSquaredErrors, ROW_KEY, "Test5");
        dataset.add(listOfMeanSquaredErrors, ROW_KEY, "Test6");

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Box and Whisker Chart", ROW_KEY, "MSE", dataset, false);
        f.add(new ChartPanel(chart){
            @Override
           public Dimension getPreferredSize(){
               return new Dimension(320, 480);
           }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}






//extends ApplicationFrame {
//    public MeanSquaredErrorBoxPlot(String title, List<Integer> listOfMeanSquaredErrors) {
//        super(title);
//
//        final BoxAndWhiskerXYDataset dataset =createDataset(listOfMeanSquaredErrors);
//    }
//
//    private BoxAndWhiskerXYDataset createDataset(List<Integer> listOfMeanSquaredErrors){
////        final int ENTITY_COUNT = 20;
//
//        DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("Mean squared error Boxplot");
//
//        dataset.a;
//
//    }
//}
