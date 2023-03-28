package it.polimi.dinosaursisland.networking;

import it.polimi.dinosaursisland.Gui.MapFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.net.Socket;

public class PlayerClient {
    private String input;
    private String output;
    private Socket socket;
    private String token;
    private BufferedReader keyboardReader;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Encoder encoder = new Encoder();
    private Decoder decoder = new Decoder();
    
    public PlayerClient(String host, int port)
        throws UnknownHostException, IOException{
            socket = new Socket(host,port);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            keyboardReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connecting to" + host +":"+port);
            System.out.println("Connected!");
            
            while(true){
                System.out.println("client:");
                output = keyboardReader.readLine();
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                input = bufferedReader.readLine();
                if(decoder.decodeCommand(input).returnToken()!=null){
                    this.token = decoder.decodeCommand(input).returnToken();
                    System.out.println(token);
                }                
                System.out.println("server:" + input);
                if(input.startsWith("@mappaGenerale")){
                    MapFrame mapFrame = new MapFrame(input);
                }
            }
        }
}