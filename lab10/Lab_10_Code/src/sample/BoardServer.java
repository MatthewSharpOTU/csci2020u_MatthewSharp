package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Server Class to handle the bulletin board
 */
public class BoardServer extends Application{
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected int numClients = 0; // used to keep track of number of clients
    protected MessageHandler[] threads = null; // used for each new connection
    protected Vector message = new Vector();

    protected BufferedReader networkIn = null; // used to read from socket
    protected PrintWriter networkOut = null;
    protected Socket socket = null;

    public String[] recordedMessages;
    public boolean sent = false;
    public boolean first = true;
    public static int port = 8080;
    public static int max_client = 100;

    /**
     * Sets up and maintains the server
     * @param primaryStage - Used to display the Server side board
     * @throws Exception - Incase some form of error arises
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("---------------------------");
            System.out.println("Bulletin Board Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+port);
            threads = new MessageHandler[max_client];
            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client Connected");
                threads[numClients] = new MessageHandler(clientSocket, message);
                threads[numClients].start();
                numClients++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
