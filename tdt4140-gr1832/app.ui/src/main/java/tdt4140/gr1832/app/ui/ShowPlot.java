package tdt4140.gr1832.app.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowPlot extends FxApp {

    @SuppressWarnings({ "unchecked", "rawtypes" })

    public void start(Stage stage) throws ParseException {
        stage.setTitle("Line Chart Sample");
        final DateAxis xAxis = new DateAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Events");

        final LineChart<Date,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Events");

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");


        XYChart.Series<Date,Number> series = new XYChart.Series<>();
        series.setName("Events this Year");
        series.getData().add(new XYChart.Data(dateFormat.parse("Jan 11, 2014"), 23));
        series.getData().add(new XYChart.Data(dateFormat.parse("Feb 09, 2014"), 14));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/Mar/2014"), 15));
//        series.getData().add(new XYChart.Data(dateFormat.parse("14/Apr/2014"), 24));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/May/2014"), 34));
//        series.getData().add(new XYChart.Data(dateFormat.parse("07/Jun/2014"), 36));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/Jul/2014"), 22));
//        series.getData().add(new XYChart.Data(dateFormat.parse("21/Aug/2014"), 45));
//        series.getData().add(new XYChart.Data(dateFormat.parse("04/Sep/2014"), 43));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/Oct/2014"), 17));
//        series.getData().add(new XYChart.Data(dateFormat.parse("30/Nov/2014"), 29));
//        series.getData().add(new XYChart.Data(dateFormat.parse("10/Dec/2014"), 25));


        Scene scene  = new Scene(lineChart,800,600);
        stage.setScene(scene);
        stage.show();
        //scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        
        
        
        lineChart.getData().add(series);
        
        /**
         * Browsing through the Data and applying ToolTip
         * as well as the class on hover
         */
        
    }
    /*
        for (XYChart.Series<Date, Number> s : lineChart.getData()) {
            for (XYChart.Data<Date, Number> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        d.getXValue().toString() + "\n" +
                                "Number Of Events : " + d.getYValue()));

                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));

                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
            }
        }
    }
     */
    public static void main(String[] args) {
        launch(args);
    }
}
