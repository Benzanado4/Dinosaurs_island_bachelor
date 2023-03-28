package it.polimi.dinosaursisland.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket socket;
    private GameServer gameServer;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private Decoder decoder = new Decoder();
    private String input;
    private String output;
    private Command command;
    private Encoder encoder = new Encoder();
    
    public ClientHandler(Socket socket,GameServer gameServer){
        this.socket = socket;
        this.gameServer = gameServer;
    }
    
    public void run(){
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            while(true){
                input = bufferedReader.readLine();
                System.out.println(input);
                command = decoder.decodeCommand(input);
                output = encoder.encodeCommand(gameServer.doCommand(command));
                System.out.println(output);
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}