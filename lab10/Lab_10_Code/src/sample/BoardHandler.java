package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class BoardHandler extends Thread{
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public BoardHandler(Socket socket) throws IOException {
        this.socket = socket;
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
        return processCommand(line);
    }

    private Boolean processCommand(String command) {
        if (command.equalsIgnoreCase("LOGOUT")){
            out.println("Connection has been closed");
            return true;
        } else {
            out.println("Unrecognized command");
            return false;
        }
    }
}
