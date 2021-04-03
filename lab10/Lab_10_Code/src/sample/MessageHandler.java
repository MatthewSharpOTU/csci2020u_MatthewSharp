package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

public class MessageHandler extends Thread{
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Vector message = null;
    private String user = null;

    public MessageHandler(Socket socket, Vector message) throws IOException {
        this.socket = socket;
        this.message = message;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run(){
        Boolean active = false;
        while(!active){
            active = handleMessage();
        }

        try{
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

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

    private Boolean processCommand(String command, String args){
        if (command.equalsIgnoreCase("NAME")){
            user = args;
            out.println("Username set");
            return false;
        } else if (command.equalsIgnoreCase("MSG")) {
            int id = -1;
            synchronized(this) {
                message.addElement(user+": " + args);
                id = message.size()-1;
            }
            out.println("Message set");
            return false;
        } else if (command.equalsIgnoreCase("GETMSG")){
            int id = (new Integer(args)).intValue();
            if (id < message.size()){
                String msg = (String)message.elementAt(id);
                out.println(msg);
            } else {
                out.println("Message does not exist");
            }
            return false;
        } else if (command.equalsIgnoreCase("LEN")){
            out.println(message.size());
            return false;
        } else if (command.equalsIgnoreCase("LOGOUT")){
            out.println("Connection has been disconnected");
            return true;
        } else {
            out.println("Unrecognizable Command");
            return false;
        }
    }
}
