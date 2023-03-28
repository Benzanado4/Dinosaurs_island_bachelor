package it.polimi.dinosaursisland.partita;

import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.dinosauri.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che rappresenta la partita.
 */

public class Game implements Serializable {

    private ArrayList<Player> playerList = new ArrayList(0);
    private ArrayList<Player> activeList = new ArrayList(0);
    private Player currentPlayer;
    private Map map;
    private GameController gameController;
    private Ranking ranking = new Ranking(playerList);
    private String[] gif = {"dinosaur1.gif", "dinosaur2.gif", "dinosaur3.gif", "dinosaur4.gif", "dinosaur5.gif", "dinosaur6.gif", "dinosaur7.gif", "dinosaur8.gif", "dinosaurBear.gif"};

    /*Costruttore partita*/
    
    /**Crea una nuova mappa e un nuovo GameController**/
    public Game() {
        map = new Map();
        map.createMap();
        gameController = new GameController(this);
    }
    
    public Game(Map map){
        this.map = map;
         gameController = new GameController(this);
    }
    
    /*Metodi di controllo*/
    
    /**Ritorna il giocatore con il token passato come argomento**/
    public Player tokenPlayer(String token) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).returnToken()!=null && playerList.get(i).returnToken().equals(token)) {
                return playerList.get(i);
            }
        }
        return null;
    }

    /**Ritorna il riferimento alla mappa
    *
    * @return Map
    */
    public Map returnMap() {
        return map;
    }

    /**Ritorna il riferimento al giocatore corrente**/
    public Player returnCurrentPlayer() {
        return currentPlayer;
    }

    /**Ritorna la lista dei giocatori creati in partita**/
    public ArrayList<Player> returnPlayerList() {
        return playerList;
    }

    /**Ritorna la lista dei giocatori in partita*/
    public ArrayList<Player> returnActiveList() {
        return activeList;
    }
    
    /**Ritorna il riferimento al controller della partita*/
    public GameController returnController(){
        return gameController;
    }
    
    /**Ritorna il giocatore con il nome passato come argomento, null se non esiste*/
    public Player isPlayerPres(String id) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).returnId().equals(id)) {
                return playerList.get(i);
            }
        }
        return null;
    }
    
    /**Aggiorna la classifica aggiornando i punteggi di tutti i giocatori*/
    public Ranking updateRanking() {
        ranking.updateRanking(playerList);
        return ranking;
    }
    
    /**Ritorna true se il nome della specie è disponibile, false altrimenti*/
    public boolean isTypeNameAvailable(String type) {;
        if (!playerList.isEmpty()) {
            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).returnType() != null && playerList.get(i).returnType().returnName().equals(type)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**Ritorna true se il giocatore con il token passato con argomento è in partita, false altrimenti*/
    public boolean playerConnected(String token) {
        if (activeList.contains(tokenPlayer(token))) {
            return true;
        }
        return false;
    }
    
    
    
    /*Metodi di gestione della partita*/
    
    /**Registra il giocatore aggiungendolo a playerList. Ritorna true se la registrazione è andata a buon fine, false altrimenti*/
    public boolean newRegister(String userId, String password) {
        if (isPlayerPres(userId) == null) {

            Player tempPlayer = new Player(userId, password);
            playerList.add(tempPlayer);
            playerList.get(playerList.size() - 1).setGif(gif[playerList.size() - 1]);
            if (tempPlayer.returnId().equals("bear")) {
                tempPlayer.setGif("dinosaurBear.gif");
            }
            return true;

        }
        return false;
    }
    
    /**Controlla se il login è corretto. Ritorna true se il login è andato a buon fine, false altrimenti*/
    public boolean newLogin(String userId, String password) {
        Player login = isPlayerPres(userId);
        if (login != null && login.passwordCorrect(password)) {
            return true;
        }
        return false;
    }

    /**Crea la specie.Ritorna true se la creazione è andata a buon fine, false altrimenti*/
    public boolean createType(Player player, String name, int type) {
        Box temp = map.returnRandomAvailableBox();
        if (type == 1) {
            player.setType(new CarnivorousType(name, new Carnivorous(temp.returnX(), temp.returnY(), name.concat("1"))));
            player.returnType().returnDinosaur(0).setPlayer(player);
            gameController.setTypeVisual(player.returnType());
            return true;

        }
        if (type == 2) {
            player.setType(new ErbivorousType(name, new Erbivorous(temp.returnX(), temp.returnY(), name.concat("1"))));
            player.returnType().returnDinosaur(0).setPlayer(player);
            gameController.setTypeVisual(player.returnType());
            return true;
        }

        return false;
    }
    
    /**Inserisce il giocatori in partita aggiuncendolo ad activeList e posizionando i dinosauri sulla mappa.Ritorna true se l'accesso è andato a buon fine, false altrimenti*/
    public boolean gameAccess(Player player) {
        if (activeList.size() < 8 && !activeList.contains(player)) {
            if (activeList.isEmpty()) {
                currentPlayer = player;
            }
            activeList.add(player);
            for (int i = 0; i < player.returnType().returnDinosaurs().size(); i++) {
                Dinosaur dinoTemp = player.returnType().returnDinosaurs().get(i);
                map.setDinosaur(dinoTemp, dinoTemp.getX(), dinoTemp.getY());    //ottimizzare parametri setDinosaur           
                gameController.setTypeVisual(player.returnType());
            }
            return true;
        }
        return false;
    }
    
    /**Rimuove il giocatore dalla partita rimuovendolo da activeList e rimuovendo i dinosauri dalla mappa. Ritorna true se l'uscita è andata a buon fine, false altrimenti*/
    public boolean gameOut(Player player){
        if(activeList.contains(player)){
            activeList.remove(player);
            for (int i = 0; i < player.returnType().returnDinosaurs().size(); i++) {
                Dinosaur dinoTemp = player.returnType().returnDinosaurs().get(i);
                map.removeDinosaur(dinoTemp);       

            }
            return true;
        }
        return true;
    }
    
    /**Setta il giocatore successivo*/
    public void setNextPlayer() {
        if (activeList.isEmpty()) {
            return;
        }
        if (activeList.indexOf(currentPlayer) == (activeList.size() - 1)) {
            currentPlayer = activeList.get(0);
        } else {
            currentPlayer = activeList.get(activeList.indexOf(currentPlayer) + 1);
        }
    }
    
    /**Setta il giocatore passato come argomento come il giocatore corrente*/
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    /**Rimuove tutti i giocatori dalla partita*/
    public void endGame(){
        int activePlayers = activeList.size();
        for(int i=0;i<activePlayers;i++){
            gameOut(activeList.get(0));
        }
    }
    
    /*Metodi deprecati*/
     //registra il giocatore 
    public boolean register(String userId, String password, int type, String name, String firstName) {

        if (type == 1) {
            if (isPlayerPres(userId) == null) {
                Box temp = map.returnRandomAvailableBox();
                CarnivorousType tempType = new CarnivorousType(name, new Carnivorous(temp.returnX(), temp.returnY(), firstName));
                Player tempPlayer = new Player(userId, password, tempType);
                tempPlayer.returnType().returnDinosaur(0).setPlayer(tempPlayer);
                playerList.add(tempPlayer);
                gameController.setTypeVisual(tempType);
                playerList.get(playerList.size() - 1).setGif(gif[playerList.size() - 1]);
                if (tempPlayer.returnId().equals("bear")) {
                    tempPlayer.setGif("dinosaurBear.gif");
                }
                return true;

            }
            return false;
        }
        if (type == 2) {
            if (isPlayerPres(userId) == null) {
                Box temp = map.returnRandomAvailableBox();
                ErbivorousType tempType = new ErbivorousType(name, new Erbivorous(temp.returnX(), temp.returnY(), firstName));
                Player tempPlayer = new Player(userId, password, tempType);
                tempPlayer.returnType().returnDinosaur(0).setPlayer(tempPlayer);
                playerList.add(tempPlayer);
                gameController.setTypeVisual(tempType);
                playerList.get(playerList.size() - 1).setGif(gif[playerList.size() - 1]);
                if (tempPlayer.returnId().equals("bear")) {
                    tempPlayer.setGif("dinosaurBear.gif");
                }
                return true;
            }
            return false;
        }
        return false;
    }

    //fa il login del giocatore
    public boolean login(String userId, String password) {
        Player login = isPlayerPres(userId);
        if (login != null && login.passwordCorrect(password) && activeList.size() < 8) {
            if (activeList.isEmpty()) {
                currentPlayer = login;
            }
            activeList.add(login);
            for (int i = 0; i < login.returnType().returnDinosaurs().size(); i++) {
                Dinosaur dinoTemp = login.returnType().returnDinosaurs().get(i);
                map.setDinosaur(dinoTemp, dinoTemp.getX(), dinoTemp.getY());    //ottimizzare parametri setDinosaur           

            }
            return true;

        }
        return false;
    }
    
}
