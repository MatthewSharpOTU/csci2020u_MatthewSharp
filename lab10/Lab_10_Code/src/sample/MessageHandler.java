package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Thread class to handle the message input
 */
public class MessageHandler extends Thread{
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Vector message = null;
    private String user = null;

    /**
     * Used the instantiate a new connection
     * @param socket - used to connect the client to the server
     * @param message - used to send the message to be stored
     * @throws IOException - Some form of Input/Output Error arises
     */
    public MessageHandler(Socket socket, Vector message) throws IOException {
        this.socket = socket;
        this.message = message;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Used to execute when a command is sent
     */
    public void run(){
        Boolean active = false;
        while(!active){ // Active while the user still requests to send/receive a message
            active = handleMessage();
        }

        try{
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Function to handle execution input, with the corresponding command and arguments
     * @return - Boolean statment whether or not the socket is finished
     */
    private Boolean handleMessage() {
        String line = null;
        try{
            line = in.readLine();
        } catch (IOException e) {
            System.err.println("Error reading command from socket.");
            return true;
        }
        if (null==line){
            return true;
        }
        StringTokenizer tokenizer = new StringTokenizer(line);
        String command = tokenizer.nextToken();
        String args = null;
        if (tokenizer.hasMoreTokens()) {
            args = line.substring(command.length()+1, line.length());
        }
        return processCommand(command, args);
    }

    /**
     * Used to handle the command given by the user and the following argument
     * @param command - The command wanted to be done as desired by the user
     * @param args - The arguments passed to execute this command
     * @return - boolean statment whether or not the socket is finished
     */
    private Boolean processCommand(String command, String args){
        if (command.equalsIgnoreCase("NAME")){ // Sets username
            user = args;
            out.println("Username set");
            return false;
        } else if (command.equalsIgnoreCase("MSG")) { // sets message
            int id = -1;
            synchronized(this) {
                message.addElement(user+": " + args);
                id = message.size()-1;
            }
            out.println("Message set");
            return false;
        } else if (command.equalsIgnoreCase("GETMSG")){ // gets message
            int id = (new Integer(args)).intValue();
            if (id < message.size()){
                String msg = (String)message.elementAt(id);
                out.println(msg);
            } else {
                out.println("Message does not exist");
            }
            return false;
        } else if (command.equalsIgnoreCase("LEN")){ // gets total number of messages
            out.println(message.size());
            return false;
        } else if (command.equalsIgnoreCase("LOGOUT")){ // closes socket
            out.println("Connection has been disconnected");
            return true;
        } else { // if command is not recognized
            out.println("Unrecognizable Command");
            return false;
        }
    }
}
