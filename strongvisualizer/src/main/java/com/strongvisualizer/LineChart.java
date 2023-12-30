package com.strongvisualizer;

import org.jfree.chart.ChartPanel;

import java.awt.Dimension;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
//import org.jfree.chart.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame{

    private List<String[]> data;

    public LineChart(String applicationTitle, String chartTitle, String xAxisTitle,
    String yAxisTitle, List<String[]> dataIN){
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,
        xAxisTitle,
        yAxisTitle,
        createDataset(),
        PlotOrientation.VERTICAL,
        true,
        true,
        true);

        this.data = new ArrayList<String[]>(dataIN);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(560,367));
        setContentPane(chartPanel);


    }

    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(String[] row : this.data){
            Integer tempNumber = Integer.valueOf(row[0]);
            dataset.addValue(tempNumber, row[1], row[2]);
        }

        return dataset;
    }

    public static void main( String[ ] args ) {
        // LineChart chart = new LineChart(
        //    "School Vs Years" ,
        //    "Numer of Schools vs years", 
        //    "Years",
        //    "Number of SChools");
  
        // chart.pack();
        // chart.setVisible(true);
     }


}
