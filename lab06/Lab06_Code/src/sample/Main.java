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
import javafx.stage.Stage;

public class Main extends Application {

    private Canvas canvas;
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color. AQUA , Color. GOLD , Color. DARKORANGE ,
            Color. DARKSALMON , Color. LAWNGREEN , Color. PLUM
    };


    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        primaryStage.setTitle("Lab 06");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }

    private void draw(Group root){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Making Housing Price Graph
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(30, 485, 10, (avgHousingPricesByYear[0]+90)-avgHousingPricesByYear[0]);
        gc.fillRect(30,485,10,(avgHousingPricesByYear[0]+90)-avgHousingPricesByYear[0]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(60, 480, 10, (avgHousingPricesByYear[1]+95)-avgHousingPricesByYear[1]);
        gc.fillRect(60,480,10,(avgHousingPricesByYear[1]+95)-avgHousingPricesByYear[1]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(90, 475, 10, (avgHousingPricesByYear[2]+100)-avgHousingPricesByYear[2]);
        gc.fillRect(90,475,10,(avgHousingPricesByYear[2]+100)-avgHousingPricesByYear[2]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(120, 470, 10, (avgHousingPricesByYear[3]+105)-avgHousingPricesByYear[3]);
        gc.fillRect(120,470,10,(avgHousingPricesByYear[3]+105)-avgHousingPricesByYear[3]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(150, 465, 10, (avgHousingPricesByYear[4]+110)-avgHousingPricesByYear[4]);
        gc.fillRect(150,465,10,(avgHousingPricesByYear[4]+110)-avgHousingPricesByYear[4]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(180, 460, 10, (avgHousingPricesByYear[5]+115)-avgHousingPricesByYear[5]);
        gc.fillRect(180,460,10,(avgHousingPricesByYear[5]+115)-avgHousingPricesByYear[5]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(210, 455, 10, (avgHousingPricesByYear[6]+120)-avgHousingPricesByYear[6]);
        gc.fillRect(210,455,10,(avgHousingPricesByYear[6]+120)-avgHousingPricesByYear[6]);

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.strokeRect(240, 450, 10, (avgHousingPricesByYear[7]+125)-avgHousingPricesByYear[7]);
        gc.fillRect(240,450,10,(avgHousingPricesByYear[7]+125)-avgHousingPricesByYear[7]);

        // Making Commerical Pricing Graph
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(40, 160, 10, (avgCommercialPricesByYear[0]+415)-avgCommercialPricesByYear[0]);
        gc.fillRect(40,160,10,(avgCommercialPricesByYear[0]+415)-avgCommercialPricesByYear[0]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(70, 140, 10, (avgCommercialPricesByYear[1]+435)-avgCommercialPricesByYear[1]);
        gc.fillRect(70,140,10,(avgCommercialPricesByYear[1]+435)-avgCommercialPricesByYear[1]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(100, 135, 10, (avgCommercialPricesByYear[2]+440)-avgCommercialPricesByYear[2]);
        gc.fillRect(100,135,10,(avgCommercialPricesByYear[2]+440)-avgCommercialPricesByYear[2]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(130, 125, 10, (avgCommercialPricesByYear[3]+450)-avgCommercialPricesByYear[3]);
        gc.fillRect(130,125,10,(avgCommercialPricesByYear[3]+450)-avgCommercialPricesByYear[3]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(160, 120, 10, (avgCommercialPricesByYear[4]+455)-avgCommercialPricesByYear[4]);
        gc.fillRect(160,120,10,(avgCommercialPricesByYear[4]+455)-avgCommercialPricesByYear[4]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(190, 90, 10, (avgCommercialPricesByYear[5]+485)-avgCommercialPricesByYear[5]);
        gc.fillRect(190,90,10,(avgCommercialPricesByYear[5]+485)-avgCommercialPricesByYear[5]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(220, 65, 10, (avgCommercialPricesByYear[6]+510)-avgCommercialPricesByYear[6]);
        gc.fillRect(220,65,10,(avgCommercialPricesByYear[6]+510)-avgCommercialPricesByYear[6]);

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(250, 60, 10, (avgCommercialPricesByYear[7]+515)-avgCommercialPricesByYear[7]);
        gc.fillRect(250,60,10,(avgCommercialPricesByYear[7]+515)-avgCommercialPricesByYear[7]);

        // Makes Pie Chart
        double total = purchasesByAgeGroup[0] + purchasesByAgeGroup[1] + purchasesByAgeGroup[2] + purchasesByAgeGroup[3] + purchasesByAgeGroup[4] + purchasesByAgeGroup[5];
        // Used to calculate the angle
        double[] purchasesRatio = {purchasesByAgeGroup[0]/total, purchasesByAgeGroup[1]/total, purchasesByAgeGroup[2]/total, purchasesByAgeGroup[3]/total, purchasesByAgeGroup[4]/total, purchasesByAgeGroup[5]/total};

        Arc arc1 = new Arc(600, 300, 100, 100, 0, 360*purchasesRatio[0]);
        arc1.setFill(pieColours[0]);
        arc1.setStroke(pieColours[0]);
        arc1.setType(ArcType.ROUND);
        root.getChildren().add(arc1);

        Arc arc2 = new Arc(600, 300, 100, 100, 360*purchasesRatio[0], 360*purchasesRatio[1]);
        arc2.setFill(pieColours[1]);
        arc2.setStroke(pieColours[1]);
        arc2.setType(ArcType.ROUND);
        root.getChildren().add(arc2);

        Arc arc3 = new Arc(600, 300, 100, 100, 360*purchasesRatio[0]+360*purchasesRatio[1], 360*purchasesRatio[2]);
        arc3.setFill(pieColours[2]);
        arc3.setStroke(pieColours[2]);
        arc3.setType(ArcType.ROUND);
        root.getChildren().add(arc3);


        Arc arc4 = new Arc(600, 300, 100, 100, 360*purchasesRatio[0]+360*purchasesRatio[1]+360*purchasesRatio[2], 360*purchasesRatio[3]);
        arc4.setFill(pieColours[3]);
        arc4.setStroke(pieColours[3]);
        arc4.setType(ArcType.ROUND);
        root.getChildren().add(arc4);

        Arc arc5 = new Arc(600, 300, 100, 100, 360*purchasesRatio[0]+360*purchasesRatio[1]+360*purchasesRatio[2]+360*purchasesRatio[3], 360*purchasesRatio[4]);
        arc5.setFill(pieColours[4]);
        arc5.setStroke(pieColours[4]);
        arc5.setType(ArcType.ROUND);
        root.getChildren().add(arc5);

        Arc arc6 = new Arc(600, 300, 100, 100, 360*purchasesRatio[0]+360*purchasesRatio[1]+360*purchasesRatio[2]+360*purchasesRatio[3]+360*purchasesRatio[4], 360*purchasesRatio[5]);
        arc6.setFill(pieColours[5]);
        arc6.setStroke(pieColours[5]);
        arc6.setType(ArcType.ROUND);
        root.getChildren().add(arc6);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
