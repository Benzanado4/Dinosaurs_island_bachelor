package it.polimi.dinosaursisland.demo;

import it.polimi.dinosaursisland.networking.GameServer;
import java.io.IOException;
import java.net.UnknownHostException;

public class ProvaNet {
	public static void main(String[] args){
		GameServer serverTest = new GameServer(4444);
		serverTest.startServer();
	}
}