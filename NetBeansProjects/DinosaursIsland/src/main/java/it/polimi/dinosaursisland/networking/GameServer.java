package it.polimi.dinosaursisland.networking;

import it.polimi.dinosaursisland.dinosauri.CarnivorousType;
import it.polimi.dinosaursisland.dinosauri.Dinosaur;
import it.polimi.dinosaursisland.dinosauri.ErbivorousType;
import it.polimi.dinosaursisland.exceptions.*;
import it.polimi.dinosaursisland.partita.Game.*;
import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.partita.*;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {

    private ConnectionWaiter connectionWaiter;
    private ArrayList<Socket> sockets = new ArrayList(0);
    private ArrayList<ClientHandler> clientHandlers = new ArrayList(0);
    private int port;
    private Dinosaur dino;
    private Player player;
    private Game game;
    private GameController gameController;
    private boolean registration;
    private boolean login;
    private boolean type;
    private Decoder decoder = new Decoder();
    private ArrayList<String> tokens = new ArrayList(0);
    private String string;
    private boolean actionTimer = false;
    private boolean turnTimer = true;
    private TurnTimer t120;
    private TurnTimer t30;

    public GameServer(int port) {
        this.port = port;
        game = new Game();
        gameController = game.returnController();
    }

    public GameServer(int port, Game game) {
        this.port = port;
        gameController = game.returnController();
        this.game = game;
    }

    public Game returnGame() {
        return game;
    }

    public void startServer() {
        connectionWaiter = new ConnectionWaiter(port, this);
        connectionWaiter.start();
    }

    public void createClientHandler(Socket socket) {
        sockets.add(socket);
        ClientHandler clientTemp = new ClientHandler(socket, this);
        clientHandlers.add(clientTemp);
        clientTemp.start();
    }

    public ClientHandler findHandlerByPlayer(Player player){
        for(int i = 0;i< clientHandlers.size();i++){
            if((game.tokenPlayer(clientHandlers.get(i).returnToken())).equals(player))
                return clientHandlers.get(i);
        }
        return null;
    }
    
    public String generateToken() {
        double token = (Math.random());
        return String.valueOf(token);
    }

    public int returnNumType(String string) {
        if (string.equals("c")) {
            return 1;
        }
        if (string.equals("e")) {
            return 2;
        }
        return 0;

    }

    public boolean validToken(String token) {
        if (tokens.contains(token)) {
            return true;
        }
        return false;
    }

    /**Esegue il comando passato come parametro*/
    public synchronized Command doCommand(Command command) {
        if (command == null) {
            return new Command("@comandoErrato");
        }

        if (command.returnCommand().equals("@comandoErrato")) {
            return command;
        }


        if (command.returnCommand().equals("@creaUtente") && command.returnParameters().size() == 2) {
            return createUser(command);
        }

        if (command.returnCommand().equals("@login") && command.returnParameters().size() == 2) {
            return login(command);
        }

        if (command.returnCommand().equals("@creaRazza") && command.returnParameters().size() == 3) {
            return createType(command);

        }

        if (command.returnCommand().equals("@accessoPartita") && command.returnParameters().size() == 1) {
            return gameAccess(command);
        }

        if (command.returnCommand().equals("@uscitaPartita") && command.returnParameters().size() == 1) {
            return gameOut(command);
        }

        if (command.returnCommand().equals("@listaGiocatori")) {
            return playerList(command);
        }

        if (command.returnCommand().equals("@classifica") && command.returnParameters().size() == 1) {
            return returnRanking(command);
        }

        if (command.returnCommand().equals("@logout") && command.returnParameters().size() == 1) {
            return logOut(command);
        }

        if (command.returnCommand().equals("@mappaGenerale") && command.returnParameters().size() == 1) {
            return returnMapString(command);
        }

        if (command.returnCommand().equals("@listaDinosauri") && command.returnParameters().size() == 1) {
            return dinosaurList(command);
        }

        if (command.returnCommand().equals("@statoDinosauro") && command.returnParameters().size() == 2) {
            return dinosaurState(command);
        }

        if (command.returnCommand().equals("@vistaLocale") && command.returnParameters().size() == 2) {
            return returnLocalVisual(command);
        }

        if (command.returnCommand().equals("@muoviDinosauro") && command.returnParameters().size() == 4) {
            return dinosaurMovement(command);
        }

        if (command.returnCommand().equals("@cresciDinosauro") && command.returnParameters().size() == 2) {
            return growthDinosaur(command);
        }

        if (command.returnCommand().equals("@deponiUovo") && command.returnParameters().size() == 2) {
            return dinosaurEgg(command);
        }

        if (command.returnCommand().equals("@passaTurno") && command.returnParameters().size() == 1) {
            return passTurn(command);
        }

        if (command.returnCommand().equals("@confermaTurno") && command.returnParameters().size() == 1) {
            return acceptTurn(command);
        }





        return new Command("@no");
    }

    /**Ritorna true se il giocatore e' loggato,false altrimenti*/
    public boolean playerLogged(String id) {
        if (gameController.findPlayerByName(id).returnToken() != null) {
            return true;
        }
        return false;
    }

    /*Comandi*/
    /**Crea una specie con i parametri presenti nel comando passato come argomento*/
    public Command createType(Command command) {
        if (!validToken(command.returnToken())) {
            return decoder.decodeCommand("@no,@tokenNonValido");
        }
        if ((game.tokenPlayer(command.returnToken())).returnType() != null) {
            return decoder.decodeCommand("@no,@razzaGiaCreata");
        }
        if (!game.isTypeNameAvailable(command.returnParameter(1))) {
            return decoder.decodeCommand("@no,@nomeRazzaGiaOccupato");
        }

        type = game.createType(game.tokenPlayer(command.returnToken()), command.returnParameter(1), returnNumType(command.returnParameter(2)));
        if (type) {
            return new Command("@ok");
        }
        return new Command("@no");
    }

    /**Logga il giocatore con i parametri presenti nel comando passato come argomento*/
    public Command login(Command command) {
        login = game.newLogin(command.returnParameter(0), command.returnParameter(1));
        if (login && !playerLogged(command.returnParameter(0))) {
            String token = "T=";
            token = token.concat(generateToken());
            tokens.add(token);
            game.isPlayerPres(command.returnParameter(0)).setToken(token);
            return decoder.decodeCommand("@ok".concat(",").concat(token));
        }
        return decoder.decodeCommand("@no,@autenticazioneFallita");

    }

    /**Fa il logout del giocatore con i parametri presenti nel comando passato come argomento*/
    public Command logOut(Command command) {
        if (!validToken(command.returnToken())) {
            return decoder.decodeCommand("@no,@tokenNonValido");
        }
        if (game.playerConnected(command.returnCommand())) {
            game.gameOut(game.tokenPlayer(command.returnToken()));
        }
        tokens.remove(command.returnToken());
        game.tokenPlayer(command.returnToken()).revokeToken();
        return decoder.decodeCommand("@ok");
    }

    /**Elimina il ClienHandler passato come argomento e tutti i suoi riferimenti*/
    public void removeClientHandler(ClientHandler ch) {
        if (ch.returnToken() == null) {
            clientHandlers.remove(ch);
            ch.stopRunning();
        } else {
            Command cm = new Command();
            cm.setToken(ch.returnToken());
            gameOut(cm);
            tokens.remove(ch.returnToken());
            game.tokenPlayer(ch.returnToken()).revokeToken();
            clientHandlers.remove(ch);
            ch.stopRunning();
        }

    }

    /**Crea un giocatore con i parametri presenti nel comando passato come argomento*/
    public Command createUser(Command command) {
        registration = game.newRegister(command.returnParameter(0), command.returnParameter(1));
        if (registration) {
            return new Command("@ok");
        }
        return decoder.decodeCommand("@no,@usernameOccupato");
    }

    /**Inserisce in partita il giocatore con i parametri presenti nel comando passato come argomento*/
    public Command gameAccess(Command command) {
        if (!validToken(command.returnToken())) {
            return decoder.decodeCommand("@no,@tokenNonValido");
        }

        if (game.returnActiveList().size() == 8) {
            return decoder.decodeCommand("@no,@troppiGiocatori");
        }
        if (game.tokenPlayer(command.returnToken()).returnType() == null) {
            return decoder.decodeCommand("@no,@razzaNonCreata");
        }
        if (game.gameAccess(game.tokenPlayer(command.returnToken()))) {
            if (game.returnCurrentPlayer().equals(game.tokenPlayer(command.returnToken()))) {
                startTurn();
            }
            return decoder.decodeCommand("@ok");

        }
        return decoder.decodeCommand("@no");
    }

    /**Elimina dalla partita il giocatore con i parametri presenti nel comando passato come argomento*/
    public Command gameOut(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }

        if (game.playerConnected(command.returnToken())) {
            if (game.returnCurrentPlayer().equals(game.tokenPlayer(command.returnToken()))) {
                if (game.returnCurrentPlayer().equals(game.tokenPlayer(command.returnToken()))) {
                    if (turnTimer && t120 != null) {
                        turnTimer = false;
                        t120.endTimer();
                    }
                    if (actionTimer) {
                        actionTimer = false;
                        t30.endTimer();
                    }
                }
                game.gameOut(game.tokenPlayer(command.returnToken()));
                nextTurn();

            }
            return decoder.decodeCommand("@ok");
        }

        return decoder.decodeCommand("@no");
    }

    /**Genera il comando per l' invio della lista dei giocatori*/
    public Command playerList(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        if (game.returnActiveList().isEmpty()) {
            return decoder.decodeCommand("@listaGiocatori");
        }
        string = "@listaGiocatori";
        for (int i = 0; i < game.returnActiveList().size(); i++) {
            string = string.concat(",");
            string = string.concat(game.returnActiveList().get(i).returnId());
        }
        return decoder.decodeCommand(string);

    }

    /**Genera il comando per l' invio della classifica*/
    public Command returnRanking(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        if (!game.returnPlayerList().isEmpty()) {
            Ranking ranking = game.updateRanking();
            return decoder.decodeCommand(ranking.returnStringRanking());
        }
        return decoder.decodeCommand("@no");
    }

    /**Genera il comando per l' invio della mappa*/
    public Command returnMapString(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }

        if (!game.returnActiveList().contains(game.tokenPlayer(command.returnToken()))) {
            return decoder.decodeCommand("@nonInPartita");
        }
        if (game.tokenPlayer(command.returnToken()).returnType() != null) {
            String mapString = "@mappaGenerale,{40,40},";
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 40; j++) {
                    if (game.tokenPlayer(command.returnToken()).returnType().isInExploredVisual(game.returnMap().returnBox(i, j))) {
                        if (game.returnMap().returnBox(i, j) instanceof GroundBox && ((GroundBox) game.returnMap().returnBox(i, j)).isVegetationPres() == null) {
                            mapString = mapString.concat("[t]");
                        }
                        if (game.returnMap().returnBox(i, j) instanceof GroundBox && ((GroundBox) game.returnMap().returnBox(i, j)).isVegetationPres() != null) {
                            mapString = mapString.concat("[v]");
                        }
                        if (game.returnMap().returnBox(i, j) instanceof WaterBox) {
                            mapString = mapString.concat("[a]");
                        }
                    } else {
                        mapString = mapString.concat("[b]");
                    }
                }
                mapString = mapString.concat(";");
            }
            return decoder.decodeCommand(mapString);
        }
        return decoder.decodeCommand("@no");
    }

    /**Genera il comando per l' invio della vista locale*/
    public Command returnLocalVisual(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        dino = gameController.findDinoByName(command.returnParameter(1));
        if (dino == null) {
            return decoder.decodeCommand("@idNonValido");
        }
        if (!game.returnActiveList().contains(game.tokenPlayer(command.returnToken()))) {
            return decoder.decodeCommand("@nonInPartita");
        }
        return decoder.decodeCommand(gameController.localVisual(dino));
    }

    /**Genera il comando per l' invio della lista dei dinosauri*/
    public Command dinosaurList(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        if (!game.playerConnected(command.returnToken())) {
            return decoder.decodeCommand("@listaDinosauri,+");
        }
        string = "@listaDinosauri";
        for (int i = 0; i < game.tokenPlayer(command.returnToken()).returnType().returnDinosaurs().size(); i++) {
            string = string.concat(",");
            string = string.concat(game.tokenPlayer(command.returnToken()).returnType().returnDinosaurs().get(i).returnName());
        }
        return decoder.decodeCommand(string);
    }

    /**Genera la stringa per l' invio dello stato di un dinosauro*/
    public String returnDinosaurState(Command command) {
        Dinosaur dinoTemp = gameController.findDinoByName(command.returnParameter(1));
        if (dinoTemp == null) {
            return "";
        }
        String name = gameController.findPlayer(gameController.findType(dinoTemp)).returnId();
        String typeName = gameController.findType(dinoTemp).returnName();
        String typeTemp = null;
        if (gameController.findType(dinoTemp) instanceof ErbivorousType) {
            typeTemp = "e";
        }
        if (gameController.findType(dinoTemp) instanceof CarnivorousType) {
            typeTemp = "c";
        }
        if (game.tokenPlayer(command.returnToken()).returnType().returnDinosaurs().contains(dinoTemp)) {
            return ",".concat(name).concat(",").concat(typeName).concat(",").concat(typeTemp).concat(",{").concat(String.valueOf(dinoTemp.getX())).concat(",").concat(String.valueOf(dinoTemp.getY())).concat("},").concat(String.valueOf(dinoTemp.returnSize())).concat(",").concat(String.valueOf(dinoTemp.returnEnergy())).concat(",").concat(String.valueOf(dinoTemp.returntAge()));
        } else {
            return ",".concat(name).concat(",").concat(typeName).concat(",").concat(typeTemp).concat(",{").concat(String.valueOf(dinoTemp.getX())).concat(",").concat(String.valueOf(dinoTemp.getY())).concat("},").concat(String.valueOf(dinoTemp.returnSize()));
        }
    }

    /**Genera il comando per l' invio dello stato del dinosauro*/
    public Command dinosaurState(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        dino = gameController.findDinoByName(command.returnParameter(1));
        if (dino == null) {
            return decoder.decodeCommand("@idNonValido");
        }
        if (!game.returnActiveList().contains(game.tokenPlayer(command.returnToken()))) {
            return decoder.decodeCommand("@nonInPartita");
        }
        string = "@statoDinosauro";
        return decoder.decodeCommand(string.concat(returnDinosaurState(command)));



    }

    /**Esegue i comandi per la crescita del dinosauro*/
    public Command growthDinosaur(Command command) {

        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        dino = gameController.findDinoByName(command.returnParameter(1));
        if (dino == null) {
            return decoder.decodeCommand("@idNonValido");
        }
        if (!game.returnActiveList().contains(game.tokenPlayer(command.returnToken()))) {
            return decoder.decodeCommand("@nonInPartita");
        }
        if (!game.tokenPlayer(command.returnToken()).equals(game.returnCurrentPlayer())) {
            return decoder.decodeCommand("@nonIlTuoTurno");
        }
        int size = dino.returnSize();
        try {

            gameController.growDinosaur(dino);

        } catch (GrowDinosaurException gde) {

            if (gde.returnNewSize() == size) {
                return decoder.decodeCommand("@raggiuntaDimensioneMax");
            }
            if (gde.returnNewSize() != size) {
                if (gameController.checkTurn()) {
                    nextTurn();
                }
                return decoder.decodeCommand("@ok");
            }
        } catch (ActionNotPermittedException anpe) {
            return decoder.decodeCommand("@raggiuntoLimiteMosseDinosauro");
        } catch (DinoDeathException dde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Il tuo dinosauro è morto!");
            return decoder.decodeCommand("@mortePerInedia");
        } catch (TypeDeathException tde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("La tua specie si è estinta!");
            return decoder.decodeCommand("@mortePerInedia");
        }

        return decoder.decodeCommand("@no");
    }

    /**Esegue i comandi per il movimento del dinosauro*/
    public Command dinosaurMovement(Command command) {
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        dino = gameController.findDinoByName(command.returnParameter(1));
        player = game.tokenPlayer(command.returnToken());
        if (dino == null) {
            return decoder.decodeCommand("@idNonValido");
        }
        if (!game.returnCurrentPlayer().equals(player)) {
            return decoder.decodeCommand("@nonIlTuoTurno");
        }
        if (!game.returnActiveList().contains(player)) {
            return decoder.decodeCommand("@nonInPartita");
        }
        try {
            gameController.moveDinosaur(dino, new Box(command.getX(), command.getY()));
            if (gameController.checkTurn()) {
                nextTurn();
            }
        } catch (NotMovedException nme) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            return decoder.decodeCommand("@destinazioneNonValida");
        } catch (YetMovedException yme) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            return decoder.decodeCommand("@raggiuntoLimiteMosseDinosauro");
        } catch (DinoDeathException dde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Il dinosauro è morto!");
            return decoder.decodeCommand("@mortePerInedia");
        } catch (TypeDeathException tde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(gameController.findPlayer(tde.returnDeadType())).sendMessage("La tua specie è morta!");
            return decoder.decodeCommand("@mortePerInedia");
        } catch (MoveDeathException mde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Il tuo dinosauro è morto spostandosi");
            return decoder.decodeCommand("@mortePerInedia");
        } catch (EatException ee) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Il tuo dinosauro si è cibato.La sua nuova energia è".concat(String.valueOf(gameController.findDinoByName(command.returnParameter(1)).returnEnergy())));
            return decoder.decodeCommand("@ok");
        } catch (MovedException me) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            return decoder.decodeCommand("@ok");
        } catch (FightException fe) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            if (fe.returnDeadDino().equals(dino)) {
                findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Hai combattuto e hai perso!");
                return decoder.decodeCommand("@ok,@combattimento,p");
            }
            if (!fe.returnDeadDino().equals(dino)) {     
                findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Hai combattuto e hai vinto!");
                return decoder.decodeCommand("@ok,@combattimento,v");
            }

        }
        return decoder.decodeCommand("@no");
    }

    /**Esegue i comandi per la deposizione di un uovo*/
    public Command dinosaurEgg(Command command) {

        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        dino = gameController.findDinoByName(command.returnParameter(1));
        if (dino == null) {
            return decoder.decodeCommand("@idNonValido");
        }
        if (!game.returnActiveList().contains(game.tokenPlayer(command.returnToken()))) {
            return decoder.decodeCommand("@nonInPartita");
        }
        if (!game.tokenPlayer(command.returnToken()).equals(game.returnCurrentPlayer())) {
            return decoder.decodeCommand("@nonIlTuoTurno");
        }

        try {
            gameController.layEgg(dino);
            if (gameController.checkTurn()) {
                nextTurn();
            }

        } catch (ActionNotPermittedException anpe) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            return decoder.decodeCommand("@raggiuntoLimiteMosseDinosauro");
        } catch (DinoDeathException dde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("Il dinosauro è morto deponendo l'uovo!");
            return decoder.decodeCommand("@mortePerInedia");
        } catch (TypeDeathException tde) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            findHandlerByPlayer(game.tokenPlayer(command.returnToken())).sendMessage("La tua specie si è estinta!");
            return decoder.decodeCommand("@mortePerInedia");
        } catch (EggDeposedException ede) {
            if (gameController.checkTurn()) {
                nextTurn();
            }
            string = "@ok";
            string = string.concat(",");
            string = string.concat(ede.returnEgg().returnName());

            return decoder.decodeCommand(string);
        }
        return decoder.decodeCommand("@no");
    }

    /**Esegue il comando per il passaggio del turno*/
    public Command passTurn(Command command) {
        player = game.tokenPlayer(command.returnToken());
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        if (!game.returnCurrentPlayer().equals(player)) {
            return decoder.decodeCommand("@nonIlTuoTurno");
        }
        if (!game.returnActiveList().contains(player)) {
            return decoder.decodeCommand("@nonInPartita");
        }
        
        nextTurn();
        return decoder.decodeCommand("@ok");
    }
    
    /**Esegue il comando per la conferma del turno*/
    public Command acceptTurn(Command command) {
        player = game.tokenPlayer(command.returnToken());
        if (!tokens.contains(command.returnToken())) {
            return decoder.decodeCommand("@tokenNonValido");
        }
        if (!game.returnCurrentPlayer().equals(player)) {
            return decoder.decodeCommand("@nonIlTuoTurno");
        }
        if (!game.returnActiveList().contains(player)) {
            return decoder.decodeCommand("@nonInPartita");
        }
        if (actionTimer) {
            actionTimer = false;
            t30.endTimer();
            turnTimer = true;
            t120 = new TurnTimer(120, this);
            System.out.println("Turno accettato da" + game.returnCurrentPlayer().returnId());
            for(int i=0;i<clientHandlers.size();i++){
            if(clientHandlers.get(i).returnToken()!=null && clientHandlers.get(i).returnToken().equals(game.returnCurrentPlayer().returnToken())){
                    clientHandlers.get(i).sendMessage("Turno Accettato,hai 120 secondi per eseguire comandi!");
                }
            }
            return decoder.decodeCommand("@ok");
        }
        return decoder.decodeCommand("@no");

    }

    /**Gestisce il passaggio del turno e i timer associati*/
    public synchronized void nextTurn() {
        try {
            System.out.println("fine " + game.returnCurrentPlayer().returnId());
            
            gameController.nextTurn();
            turnChangeNotity();
        } catch (TypeDeathException tde) {
            if (turnTimer && t120 != null) {
                turnTimer = false;
                t120.endTimer();
                turnChangeNotity();
            }
            if (actionTimer) {
                actionTimer = false;
                t30.endTimer();
                turnChangeNotity();
            }
            startTurn();
        }
        if (turnTimer && t120 != null) {
            turnTimer = false;
            t120.endTimer();
        }
        if (actionTimer) {
            actionTimer = false;
            t30.endTimer();
        }

        startTurn();
    }

    /**Gestisce l'inizio del turno e i timer associati*/
    public synchronized void startTurn() {
        System.out.println("Inizio turno " + game.returnCurrentPlayer().returnId());
        for(int i=0;i<clientHandlers.size();i++){
            if(!clientHandlers.isEmpty()){                
                if(clientHandlers.get(i).returnToken()!=null && clientHandlers.get(i).returnToken().equals(game.returnCurrentPlayer().returnToken())){
                    clientHandlers.get(i).sendMessage("Hai 30 secondi per accettare il turno!");
                }
            }
        }
        t30 = new TurnTimer(30, this);
        System.out.println(game.returnCurrentPlayer().returnId());
        setActionTimer();
        
    }

    /**Setta la presenza di un timer di 30 secondi per l'accettazione del turno*/
    public synchronized void setActionTimer() {
        actionTimer = true;
    }

    /**Setta la presenza di un timer di 120 secondi per lo svolgimento del turno*/
    public synchronized void setTurnTimer() {
        turnTimer = true;
    }

    public void turnChangeNotity() {
        for (int i = 0; i < clientHandlers.size(); i++) {
            clientHandlers.get(i).sendMessage("@cambioTurno,".concat(game.returnCurrentPlayer().returnId()));
        }
    }
}