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
 * Client class for when messages are sent and received
 */
public class BoardClient{

    protected Socket socket = null; // used to store client socket
    protected PrintWriter networkOut = null; // used to write to socket
    protected BufferedReader networkIn = null; // used to read from socket

    //we can read this from the user too
    public static String SERVER_ADDRESS = "localhost"; // server address
    public static int    SERVER_PORT = 8080; // server port

    /**
     * Constructor used to set up the socket and the input and output to the server
     */
    public BoardClient(){
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the username and message of a socket
     * @param user - inputted name
     * @param message - inputted message
     */
    public void setMSG(String user, String message) {
        networkOut.println("NAME " + user);
        try {
            System.out.println(networkIn.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        networkOut.println("MSG " + message);
        try {
            System.out.println(networkIn.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the active socket
     */
    public void logout(){
        networkOut.println("LOGOUT");
        try {
            System.out.println(networkIn.readLine());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all messages in the server
     * @return - A list of all sent messages
     */
    public String[] getMSG() {
        String[] messages = null;
        networkOut.println("LEN");
        String line = null;
        int id = -1;
        try{
            line = networkIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        id = (new Integer(line.trim())).intValue();
        messages = new String[id];
        for (int i=0; i<id; i++){
            networkOut.println("GETMSG "+ i);
            try {
                messages[i] = networkIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }
}
