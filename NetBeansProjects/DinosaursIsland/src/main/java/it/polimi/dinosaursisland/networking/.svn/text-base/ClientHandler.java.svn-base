package it.polimi.dinosaursisland.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private GameServer gameServer;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private Decoder decoder = new Decoder();
    private String input;
    private String output;
    private Command command;
    private Encoder encoder = new Encoder();
    private boolean stopThread = false;
    private String token = null;
    private PrintWriter printWriter;


    public ClientHandler(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
        try{
            printWriter = new PrintWriter(socket.getOutputStream());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (!stopThread) {
                input = bufferedReader.readLine();

                if (input == null) {
                    gameServer.removeClientHandler(this);
                } else {
                    if (token != null && input.startsWith("@logout") && decoder.decodeCommand(input).returnToken().equals(token)) {
                        token = null;
                    }
                    
                }
                System.out.println("Comando ricevuto: " + input);
                command = decoder.decodeCommand(input);
                output = encoder.encodeCommand(gameServer.doCommand(command));
                sendMessage(output);
                System.out.println("Comando inviato: " + output);
                if (output.startsWith("@ok,T=")) {
                    token = (decoder.decodeCommand(output)).returnToken();
                }



            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket returnSocket() {
        return socket;
    }

    public String returnToken() {
        return token;
    }

    /**Invia una stringa al client*/
    public synchronized void sendMessage(String string) {
        try{
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }

    /**Ferma l'esecuzione del processo*/
    public void stopRunning() {
        stopThread = true;
    }
}
