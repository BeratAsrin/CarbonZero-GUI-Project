package com.carbonzero.gui.impl;


import com.carbonzero.gui.services.IColors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class GraphDrawer{

    public GraphDrawer(JPanel panel){

        XYDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createScatterPlot("Price Chart","Last Ten Transactions In Market",
                "Price",dataset,PlotOrientation.VERTICAL,
                false,
                false,
                false );

        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        xyLineAndShapeRenderer.setSeriesPaint(0,Color.WHITE);
        xyLineAndShapeRenderer.setBaseLinesVisible(false);

        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setRenderer(xyLineAndShapeRenderer);
        plot.setBackgroundPaint(IColors.specialGreen);
        plot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }

    private XYDataset createDataset() {
        // TODO DATALAR SQLDEN GELECEK
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries data = new XYSeries("Transactions");
        data.add(1,100);
        data.add(2,20);
        data.add(3,10);
        data.add(4,50);
        data.add(5,20);
        data.add(6,7);
        data.add(7,2);
        data.add(8,3);
        data.add(9,7);
        data.add(10,9);
        dataset.addSeries(data);
        return dataset;
    }

}
