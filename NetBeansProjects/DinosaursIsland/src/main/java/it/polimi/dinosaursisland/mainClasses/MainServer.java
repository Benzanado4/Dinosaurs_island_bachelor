
package it.polimi.dinosaursisland.mainClasses;

import it.polimi.dinosaursisland.networking.GameServer;
import it.polimi.dinosaursisland.partita.Game;
import it.polimi.dinosaursisland.serializazion.DeSerialize;
import it.polimi.dinosaursisland.serializazion.Serialize;
import java.io.IOException; 
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Main per il Server.
 */


public class MainServer {

    
    public static void main(String[] args) {
        
        Game game=null;
        Scanner sc = new Scanner(System.in);
        int scelta;
        while(true){
        
            System.out.println("Server Menu:");
            System.out.println("1 - Crea una nuova partita");
            System.out.println("2 - Carica partita");
            System.out.println("3 - Salva partita");


            scelta=sc.nextInt();
        
            switch(scelta){
                case 1:{
                    GameServer gameServer = new GameServer(4444);
                    gameServer.startServer();
                    game=gameServer.returnGame();
                    System.out.println("Parita creata");
                    break;
                }
                case 2:{
                    DeSerialize deSerializer = new DeSerialize();
                    game=(Game)deSerializer.deSerializeIt("partita1");
                    GameServer gameServer = new GameServer(4444,game);
                    gameServer.startServer();
                    game=gameServer.returnGame();
                    System.out.println("Parita caricata");
                    break;                    
                }
                case 3:{
                    Serialize serializer = new Serialize();
                    serializer.serializeIt(game);
                    System.out.println("Parita salvata");
                    break;
                }
            }
        }
    }
}

