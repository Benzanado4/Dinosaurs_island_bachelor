package it.polimi.dinosaursisland.networking;

import it.polimi.dinosaursisland.gui.ClientGui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.net.Socket;

public class PlayerClient {

    private String input;   
    private Socket socket;
    private String token;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ClientGui clientGui = new ClientGui(this);
    private ClientGetNotify notifyClient;
    

    public PlayerClient(String host, int port)
            throws UnknownHostException, IOException {
        
        socket = new Socket(host, port);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connecting to" + host + ":" + port);
        System.out.println("Connected!");
        notifyClient= new ClientGetNotify(socket,clientGui,this);
        notifyClient.start();
        
    }

    public synchronized String sendCommand(String string) {
        try {
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return input;
    }

    public void setToken(String token){
        this.token=token;
    }
    
    public String returnToken() {
        return token;
    }
    
    public ClientGetNotify returnNotify(){
        return notifyClient;
    }
}
