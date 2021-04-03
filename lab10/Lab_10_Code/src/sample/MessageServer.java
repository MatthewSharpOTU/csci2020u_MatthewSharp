package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MessageServer extends Application {

    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected int numClients = 0; // used to keep track of number of clients
    protected MessageHandler[] threads = null; // used for each new connection
    protected Vector message = new Vector();

    public static int port = 8080;
    public static int max_client = 50;

    public MessageServer(){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("---------------------------");
            System.out.println("Bulletin Board Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+port);
            threads = new MessageHandler[max_client];
            while(true) {
                clientSocket = serverSocket.accept();
                threads[numClients] = new MessageHandler(clientSocket, message);
                threads[numClients].start();
                numClients++;
            }
        } catch (IOException e){
            System.err.println("IOException while creating server connection");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 10 - SimpleBBS Server");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) { MessageServer server = new MessageServer(); }
}
