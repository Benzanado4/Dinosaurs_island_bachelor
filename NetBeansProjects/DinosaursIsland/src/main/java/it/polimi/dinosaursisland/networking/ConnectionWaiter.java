package it.polimi.dinosaursisland.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionWaiter extends Thread {

    private int port;
    private GameServer gameServer;
    private Socket socket;

    public ConnectionWaiter(int port, GameServer gameServer) {
        this.port = port;
        this.gameServer = gameServer;
    }

    /**Gestisce le nuove connessioni di Client associandogli un ClientHandler*/
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {

                System.out.println("In attesa su" + port);
                socket = (serverSocket.accept());

                System.out.println("Connessione ricevuta:" + socket.getInetAddress() + socket.getPort());
                gameServer.createClientHandler(socket);

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
