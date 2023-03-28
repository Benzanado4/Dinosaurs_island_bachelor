package it.polimi.dinosaursisland.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionWaiter extends Thread {
    private int port;
    private GameServer gameServer;
    private ArrayList<Socket> sockets = new ArrayList(0);
    private int connection;
    
    public ConnectionWaiter(int port,GameServer gameServer){
        this.port=port;
        this.gameServer=gameServer;
    }
    public void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(port);

            while(connection<8){         
                System.out.println("In attesa su" + port);
                sockets.add(serverSocket.accept());
                connection++;
                System.out.println("Connessione ricevuta:" + sockets.get(connection-1).getInetAddress() + sockets.get(connection-1).getPort());
                (new ClientHandler(sockets.get(connection-1),gameServer)).start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
