package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/*
* Matthew Sharp - 100748071
* Software Development and Integration Lab 09
* This program will read a URL to retrieve stock data from two different companies and display their
* data accordingly onto a graphically created graph
 */


public class Main extends Application {
    private Canvas canvas; // This will be used to draw each of the stocks

    /**
     * start function that will set the stage and call other functions to retrieve data and display them onto a graph
     * @param primaryStage - The Stage variable that will be displayed
     * @throws Exception - Exception thrown incase some error in data retrieval or display is incorrect
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group(); // root that will store the canvas

        // Prepares the canvas to be drawn
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        // Sets and displays the stage
        primaryStage.setTitle("Lab 09: Stock Performance");
        primaryStage.setScene(new Scene(root, 1125, 1000));
        primaryStage.show();

        // Retrieves Google and Apple stock data
        Map<String, ArrayList<Float>> stockPrices = downloadStockPrices();

        // Calls the drawLinePlot() function to draw the x and y axis
        drawLinePlot(stockPrices.get("GOOG"), stockPrices.get("AAPL"));
    }

    /**
     * This program will retrieve the CSV files of the two companies stocks from equal lengths in time,
     * store the appropriate data and save them into a Map<> function
     * @return - Will return the Map<> containing stock data
     */
    public Map<String, ArrayList<Float>> downloadStockPrices(){
        Map<String, ArrayList<Float>> stocks = new TreeMap<>(); // Instantiates the Map<> function
        try{
            // Retrieves URLs and establishes their connection to retrieve data
            String firstURL = "https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
            String secondURL = "https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
            URL fNetURL = new URL(firstURL);
            URL sNetURL = new URL(secondURL);
            URLConnection conn = fNetURL.openConnection();
            URLConnection conn2 = sNetURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn2.setDoOutput(false);
            conn2.setDoInput(true);

            // Establishes the CSV file data
            InputStreamReader inStream = new InputStreamReader((conn).getInputStream());
            BufferedReader br = new BufferedReader(inStream);
            InputStreamReader inStream2 = new InputStreamReader((conn2).getInputStream());
            BufferedReader br2 = new BufferedReader(inStream2);

            // Instantiates the ArrayLists for both of the stocks
            ArrayList<Float> fStockVal = new ArrayList<Float>();
            ArrayList<Float> sStockVal = new ArrayList<Float>();

            // Retrieves the header for each of the CSV files
            String line = br.readLine();
            String line2 = br2.readLine();
            String[] vals = line.split(",");

            // For loops which identifies the appropriate column index to read from
            int adjCol = 0;
            for (int i=0; i<vals.length; i++){
                if (vals[i].equals("Close")){
                    adjCol = i;
                    break;
                }
            }

            // reads line by line the appropriate column value for both of the CSV files
            while((line= br.readLine()) != null){
                vals = line.split(",");
                fStockVal.add(Float.parseFloat(vals[adjCol]));
            }
            vals = line2.split(",");
            while((line2 = br2.readLine()) != null){
                vals = line2.split(",");
                sStockVal.add(Float.parseFloat(vals[adjCol]));
            }

            // Adds to the Map funciton
            stocks.put("GOOG", fStockVal);
            stocks.put("AAPL", sStockVal);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks; // Returns the Map of the stock valuess
    }

    /**
     * drawLinePlot function will take the two lists of stock values and create the axis of the plot
     * @param firstStock - Stock data in form of a list
     * @param secondStock - Stock data in form of a list
     */
    public void drawLinePlot(ArrayList<Float> firstStock, ArrayList<Float> secondStock){
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Used to draw and display plot axis'

        // Draws the axis on the canvas with a 50 pixel buffer on each side
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,50,50, 950); // y-axis
        gc.strokeLine(50, 950, 1075, 950); // x-axis

        // Calls plotLine() passing the two lists of stock values to be displayed on the graph
        plotLine(firstStock, secondStock);
    }

    /**
     * This plotLine function will display the two lists of stock data as long as both lists are of equal length
     * @param firstStock - Stock data in form of a list
     * @param secondStock - Stock data in form of a list
     */
    public void plotLine(ArrayList<Float> firstStock, ArrayList<Float> secondStock) {
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Used to draw and display canvas

        // Conditional statement if the lists are of unequal length
        if (firstStock.size() != secondStock.size()){
            System.out.println("The stocks are not equal in length, please run program again");
            System.exit(0);
        }
        else{ // Else display both data accordingly with equal intervals and appropriate heights
            float interval = 1025/firstStock.size();
            for (int i = 0; i< firstStock.size()-1; i++){
                gc.setStroke(Color.RED); // Draws the first stock from one point to the next
                gc.strokeLine(50+interval*i,950-firstStock.get(i),50+interval*(i+1), 950- firstStock.get(i+1));
                gc.setStroke(Color.BLUE); // Draws the second stock from one point to the next
                gc.strokeLine(50+interval*i,950-secondStock.get(i),50+interval*(i+1), 950- secondStock.get(i+1));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
