package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Main extends Application {

    private Canvas canvas; // Used to draw/display graphics
    private Map<String, Double> warningCount; // Used to store the frequency of words
    private static Color[] pieColours = {
            Color. AQUA , Color. GOLD , Color. DARKORANGE , Color. DARKSALMON
    }; // Used to store pie chart colours

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

        // Sets Initial Canvas
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }

    private void draw(Group root){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Gets csv file
        File inputFile = new File("weatherwarnings-2015.csv");
        warningCount = new TreeMap<>();

        // Try - Catch Block to detect file and parse through the entries
        try{
            FileReader fileInput = new FileReader(inputFile);
            BufferedReader input = new BufferedReader(fileInput);

            String header = input.readLine();
            int columnIndex = 5; // Column index of the desired category

            String line; // Gets line of in file
            while((line = input.readLine()) != null){
                String[] data = line.split(","); // Stores each word in the line as a separate entry
                String val = String.valueOf(data[columnIndex]); // Grabs the word at a specific entry in the line
                if (warningCount.containsKey(val)){ // Conditional if the entry exists in the map already
                    double previous = warningCount.get(val);
                    warningCount.put(val, previous + 1.0);
                } else { // Conditional if the entry is new
                    warningCount.put(val, 1.0);
                }
            }

            input.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double total = warningCount.get("FLASH FLOOD") + warningCount.get("TORNADO") + warningCount.get("SPECIAL MARINE") + warningCount.get("SEVERE THUNDERSTORM"); // Represents total amount of entries
        warningCount.put("FLASH FLOOD", warningCount.get("FLASH FLOOD")/total); // Updates value to the percentage of flash flood out of all entries
        warningCount.put("SEVERE THUNDERSTORM", warningCount.get("SEVERE THUNDERSTORM")/total); // Updates value to the percentage of severe thunderstorms out of all entries
        warningCount.put("TORNADO", warningCount.get("TORNADO")/total); // Updates value to the percentage of tornado out of all entries
        warningCount.put("SPECIAL MARINE", warningCount.get("SPECIAL MARINE")/total); // Updates value to the percentage of special marine out of all entries

        // Sets the legend for the flash flood entry
        gc.setFill(pieColours[0]);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(50,175,25,25);
        gc.fillRect(50,175,25,25);

        Font font = new Font("Arial", 12);
        gc.setFont(font);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.strokeText("FLASH FLOOD", 80,190);
        gc.fillText("FLASH FLOOD", 80,190);

        // Sets the legend for the severe thunderstorm entry
        gc.setFill(pieColours[1]);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(50,225,25,25);
        gc.fillRect(50,225,25,25);

        Font font2 = new Font("Arial", 12);
        gc.setFont(font2);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.strokeText("SEVERE THUNDERSTORM", 80,240);
        gc.fillText("SEVERE THUNDERSTORM", 80,240);

        // Sets the legend for the special marine entry
        gc.setFill(pieColours[2]);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(50,275,25,25);
        gc.fillRect(50,275,25,25);

        Font font3 = new Font("Arial", 12);
        gc.setFont(font3);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.strokeText("SPECIAL MARINE", 80,290);
        gc.fillText("SPECIAL MARINE", 80,290);

        // sets the legend for the tornado entry
        gc.setFill(pieColours[3]);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(50,325,25,25);
        gc.fillRect(50,325,25,25);

        Font font4 = new Font("Arial", 12);
        gc.setFont(font4);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.strokeText("TORNADO", 80,340);
        gc.fillText("TORNADO", 80,340);

        // Makes arc for flash flood
        Arc arc1 = new Arc(500, 300, 200, 200, 0, 360*(warningCount.get("FLASH FLOOD")));
        arc1.setFill(pieColours[0]);
        arc1.setStroke(Color.BLACK);
        arc1.setType(ArcType.ROUND);
        root.getChildren().add(arc1);

        // Makes arc for severe thunderstorm
        Arc arc2 = new Arc(500, 300, 200, 200, 360*(warningCount.get("FLASH FLOOD")), 360*(warningCount.get("SEVERE THUNDERSTORM")));
        arc2.setFill(pieColours[1]);
        arc2.setStroke(Color.BLACK);
        arc2.setType(ArcType.ROUND);
        root.getChildren().add(arc2);

        // Makes arc for special marine
        Arc arc3 = new Arc(500, 300, 200, 200, 360*(warningCount.get("FLASH FLOOD") + warningCount.get("SEVERE THUNDERSTORM")),  360*(warningCount.get("SPECIAL MARINE")));
        arc3.setFill(pieColours[2]);
        arc3.setStroke(Color.BLACK);
        arc3.setType(ArcType.ROUND);
        root.getChildren().add(arc3);

        // Makes arc for tornado
        Arc arc4 = new Arc(500, 300, 200, 200, 360*(warningCount.get("FLASH FLOOD") + warningCount.get("SEVERE THUNDERSTORM") + warningCount.get("SPECIAL MARINE")), 360*(warningCount.get("TORNADO")));
        arc4.setFill(pieColours[3]);
        arc4.setStroke(Color.BLACK);
        arc4.setType(ArcType.ROUND);
        root.getChildren().add(arc4);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
