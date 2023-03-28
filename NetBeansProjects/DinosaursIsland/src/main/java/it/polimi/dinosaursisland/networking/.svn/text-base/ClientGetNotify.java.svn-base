
package it.polimi.dinosaursisland.networking;

import it.polimi.dinosaursisland.gui.ClientGui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientGetNotify extends Thread {

    private Socket socket;
    private BufferedReader bufferedReader;
    private String input;
    private ClientGui cg;
    private Decoder decoder;
    private PlayerClient pc;
    
    public ClientGetNotify(Socket socket,ClientGui cg,PlayerClient pc){
        this.socket=socket;
        this.cg=cg;
        this.pc=pc;
        try{
            decoder =new Decoder();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }   
   
    public void run(){
        try{
            while(true){
                
                input = bufferedReader.readLine();
                
                if (decoder.decodeCommand(input).returnToken() != null) {
                    pc.setToken(decoder.decodeCommand(input).returnToken());
                    cg.setToken(pc.returnToken());
                }

                if (input.startsWith("@mappaGenerale")) {
                    cg.updateMap(input);
                }
                if (input.startsWith("@vistaLocale")) {
                    cg.updateLocal(input);
                }
                if (input.startsWith("@listaDinosauri")) {
                    cg.setDinosaurs(input);
                }
                cg.writeLine(input);
            }   
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public String returnInput(){
        return input;
    }
   
}
