package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class to handle the User Interface
 */
public class MessageClient extends Application {

    public Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 10 - SimpleBBS Client");

        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.CENTER);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new Insets(25,25,25,25));
        Label user = new Label("Username:");
        Label message = new Label("Message:");
        TextField userData = new TextField();
        TextField messageData = new TextField();

        Button sendBtn = new Button("Send");
        HBox hbSendBtn = new HBox(10);
        hbSendBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbSendBtn.getChildren().add(sendBtn);
        final Text sendAction = new Text();

        Button exitBtn = new Button("Exit");
        HBox hbExitBtn = new HBox(10);
        hbSendBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbSendBtn.getChildren().add(exitBtn);
        final Text exitAction = new Text();

        myGrid.add(user, 0, 0);
        myGrid.add(userData, 1, 0);
        myGrid.add(message, 0, 1);
        myGrid.add(messageData, 1, 1);
        myGrid.add(sendBtn, 0, 2);
        myGrid.add(sendAction, 1, 2);
        myGrid.add(exitBtn, 0, 3);
        myGrid.add(exitAction, 1, 3);

        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BoardClient client = new BoardClient();
                client.setMSG(userData.getText(), messageData.getText());
                String[] recordedMessages = client.getMSG();
                client.logout();
                stage.close();
                stage.setTitle("Lab 10 - SimpleBBS Server");
                GridPane myGrid = new GridPane();
                myGrid.setAlignment(Pos.CENTER);
                myGrid.setHgap(10);
                myGrid.setVgap(10);
                myGrid.setPadding(new Insets(25,25,25,25));

                Button exitServer = new Button("Exit");
                HBox hbExitServer = new HBox(10);
                hbExitServer.setAlignment(Pos.BOTTOM_LEFT);
                hbExitServer.getChildren().add(exitServer);
                final Text exitServerAction = new Text();

                exitServer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stage.close();
                    }
                });

                TextArea text = new TextArea();
                String texts = "";
                for (int i = 0; i<recordedMessages.length; i++){
                    texts = texts + recordedMessages[i] + "\n";
                }
                text.setText(texts);
                myGrid.add(text, 0,0);
                myGrid.add(exitServer, 0, 1);
                myGrid.add(exitServerAction, 1, 1);
                stage.setScene(new Scene(myGrid, 500, 500));
                stage.show();
            }
        });

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
                System.exit(0);
            }
        });

        Scene myScene = new Scene(myGrid, 300, 275);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
