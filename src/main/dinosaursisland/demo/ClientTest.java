package it.polimi.dinosaursisland.demo;
import it.polimi.dinosaursisland.networking.PlayerClient;
import java.io.IOException;


public class ClientTest {
	public static void main(String[] args){
		try{
			PlayerClient clientTest = new PlayerClient("localhost",4444);
			}
		catch(IOException e){
		e.printStackTrace();
		}
	}
}
