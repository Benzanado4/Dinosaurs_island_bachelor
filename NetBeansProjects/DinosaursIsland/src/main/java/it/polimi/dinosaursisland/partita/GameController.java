package it.polimi.dinosaursisland.partita;

import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.dinosauri.Dinosaur;
import it.polimi.dinosaursisland.exceptions.*;
import java.io.Serializable;

/**
 * Classe che rappresenta il controllore della partita,che gestisce tutte le azioni di gioco.
 */

public class GameController implements Serializable {

    private Game game;
    private Dinosaur egg = null;
    private boolean booleanEgg = false;
    private Dinosaur tempDino;
    private Object movement;
    private Carrion tempCarr;
    private Vegetation tempVege;
    private int tempEnergy, i;
    private Player player;
    private Box box;
    private String local;

    public GameController(Game game) {

        this.game = game;

    }

    /**Se il dinosauro ha gia delle coordinate lo posiziona sulla mappa e aggiorna la sua visuale, se non le ha le assegna casualmente.*/
    public void setDinosaur(Dinosaur dino) {
        if (dino.getX() == 0 && dino.getY() == 0) {
            box = game.returnMap().returnRandomAvailableBox();
            game.returnMap().setDinosaur(dino, box.returnX(), box.returnY());
            setTypeVisual(findType(dino));
            return;
        }
        game.returnMap().setDinosaur(dino, dino.getX(), dino.getY());
        setTypeVisual(findType(dino));
        return;
    }

    /*Metodi per la ricerca*/
    /**Ritorna la specie del dinosauro passato come argomento, null se non esiste*/
    public Type findType(Dinosaur dino) {
        for (i = 0; i < game.returnPlayerList().size(); i++) {
            if (game.returnPlayerList().get(i).returnType().returnDinosaurs().contains(dino)) {
                return game.returnPlayerList().get(i).returnType();
            }
        }
        return null;
    }

    /**Ritorna la specie con il nome passato come argomento, null se non esiste*/
    public Type findTypeByName(String name) {
        for (i = 0; i < game.returnPlayerList().size(); i++) {
            for (int j = 0; j < game.returnPlayerList().get(i).returnType().returnDinosaurs().size(); j++) {
                if (game.returnPlayerList().get(i).returnType().returnDinosaur(j).returnName().equals(name)) {
                    return game.returnPlayerList().get(i).returnType();
                }
            }
        }
        return null;
    }

    /**Ritorna la specie con il nome passato come argomento, null se non esiste*/
    public Dinosaur findDinoByName(String name) {
        for (i = 0; i < game.returnPlayerList().size(); i++) {
            for (int j = 0; j < game.returnPlayerList().get(i).returnType().returnDinosaurs().size(); j++) {
                if (game.returnPlayerList().get(i).returnType().returnDinosaur(j).returnName().equals(name)) {
                    return game.returnPlayerList().get(i).returnType().returnDinosaur(j);
                }
            }
        }
        return null;
    }

    /**Ritorna il giocatore della specie passata come argomento, null se non esiste*/
    public Player findPlayer(Type type) {
        for (i = 0; i < game.returnPlayerList().size(); i++) {
            if (game.returnPlayerList().get(i).returnType() == type) {
                return game.returnPlayerList().get(i);
            }
        }
        return null;
    }

    /**Ritorna il giocatore con il nome passato come argomento, null se non esiste*/
    public Player findPlayerByName(String name) {
        for (i = 0; i < game.returnPlayerList().size(); i++) {
            if (game.returnPlayerList().get(i).returnId().equals(name)) {
                return game.returnPlayerList().get(i);
            }
        }
        return null;
    }

    /*Metodi per la visuale*/
    /**Setta la visuale per la specie passata come argomento*/
    public void setTypeVisual(Type type) {
        for (i = 0; i < type.returnDinosaurs().size(); i++) {
            type.returnDinosaurs().get(i).setVisual(game.returnMap());
        }
        type.setFullVisual();

    }

    /**Ritorna la stringa della visuale locale per la comunicazione col client*/
    public String localVisual(Dinosaur dino) {
        local=new String();
        int j = -1;

        local = local.concat("@vistaLocale,{");
        local += String.valueOf(dino.returnVisual().get(0).returnY());
        local = local.concat(",");
        local += String.valueOf(dino.returnVisual().get(0).returnX());
        local = local.concat("},{");
        local += String.valueOf(dino.returnNumRow());
        local = local.concat(",");
        local += String.valueOf(dino.returnNumColumn());
        local = local.concat("},");


        for (i = 0; i < dino.returnNumRow(); i++) {
            for (j = 0; j < dino.returnNumColumn(); j++) {
                Box temp = dino.returnVisual().get(i * dino.returnNumColumn() + j);
                if (temp instanceof WaterBox) {
                    local = local.concat("[").concat("a").concat("]");

                } else {

                    if (((GroundBox) temp).isCarrionPres() != null && ((GroundBox) temp).isDinosaurPres() != null) {
                        local = local.concat("[").concat("c,").concat(String.valueOf(((GroundBox) temp).isCarrionPres().returnEnergy())).concat(",d,").concat(String.valueOf(((GroundBox) temp).isDinosaurPres().returnEnergy())).concat("]");
                    }
                    if (((GroundBox) temp).isVegetationPres() != null && ((GroundBox) temp).isDinosaurPres() != null) {
                        local = local.concat("[").concat("v,").concat(String.valueOf(((GroundBox) temp).isVegetationPres().returnEnergy())).concat(",d,").concat(String.valueOf(((GroundBox) temp).isDinosaurPres().returnEnergy())).concat("]");

                    }
                    if (((GroundBox) temp).isCarrionPres() != null && ((GroundBox) temp).isDinosaurPres() == null) {
                        local = local.concat("[").concat("c,").concat(String.valueOf(((GroundBox) temp).isCarrionPres().returnEnergy())).concat("]");

                    }
                    if (((GroundBox) temp).isVegetationPres() != null && ((GroundBox) temp).isDinosaurPres() == null) {
                        local = local.concat("[").concat("v,").concat(String.valueOf(((GroundBox) temp).isVegetationPres().returnEnergy())).concat("]");

                    }
                    if (((GroundBox) temp).isDinosaurPres() != null && ((GroundBox) temp).isVegetationPres() == null && ((GroundBox) temp).isVegetationPres() == null) {
                        local = local.concat("[").concat("d,").concat(String.valueOf(((GroundBox) temp).isDinosaurPres().returnEnergy())).concat("]");

                    }
                }
                if (temp instanceof GroundBox && ((GroundBox) temp).isCarrionPres() == null && ((GroundBox) temp).isDinosaurPres() == null && ((GroundBox) temp).isVegetationPres() == null) {
                    local = local.concat("[").concat("t").concat("]");
                }
            }
            local = local.concat(";");
        }

        return local;
    }

    /**Setta le visuali di tutte le speci*/
    public void setAllVisual() {

        for (i = 0; i < game.returnPlayerList().size(); i++) {
            if (game.returnPlayerList().get(i).returnType() != null && game.returnPlayerList().get(i).returnType().exist()) {
                setTypeVisual(game.returnPlayerList().get(i).returnType());
            }
        }

    }

    /*Metodi per i comandi in partita*/
    /**Compie le azioni per il movimento e ritorna l'eccezione corrispondente all'evento verificatosi*/
    public void moveDinosaur(Dinosaur dino, Box destinationBox) throws NotMovedException, YetMovedException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException, FightException {

        destinationBox = game.returnMap().returnBox(destinationBox.returnX(), destinationBox.returnY());

        if (!dino.returnMovementAction()) {
            throw new YetMovedException();
        }

        if (destinationBox instanceof WaterBox) {
            throw new NotMovedException();
        }

        tempDino = ((GroundBox) destinationBox).isDinosaurPres();
        movement = game.returnMap().moveDinosaur(dino, destinationBox);
        if (movement instanceof Box) {
            tempCarr = ((GroundBox) destinationBox).isCarrionPres();
            tempVege = ((GroundBox) destinationBox).isVegetationPres();

            if (tempDino != null) {;
                Dinosaur deadDino = dino.fight(tempDino);
                if (deadDino.equals(tempDino)) {   //perde l'altro dinosauro
                    dino.incrementEnergy(deadDino.returnEnergy());
                    setDinosaur(dino);
                    try {
                        findType(dino).doneAction();
                        dino.doneMovementAction();
                        killDinosaur(tempDino);
                        checkTurn();
                        throw new FightException(tempDino);
                    } catch (TypeDeathException tde) {
                        throw new TypeDeathException(findType(tempDino));
                    }

                }

                if (deadDino.equals(dino)) {  //perde il dinosauro che si è mosso
                    tempDino.incrementEnergy(dino.returnEnergy());
                    setDinosaur(tempDino);
                    try {
                        killDinosaur(dino);
                        throw new FightException(dino);
                    } catch (TypeDeathException tde) {//la specie muore
                        throw new TypeDeathException(findType(dino));
                    }
                }
            }
            if (dino instanceof Carnivorous && tempCarr != null) {
                tempEnergy = dino.incrementEnergy(tempCarr.returnEnergy());
                if (tempEnergy != 0) {
                    tempCarr.setEnergy(tempEnergy);
                }
                if (tempEnergy == 0) {
                    game.returnMap().newCarrion(tempCarr);
                }
                setTypeVisual(game.returnCurrentPlayer().returnType());
                findType(dino).doneAction();
                dino.doneMovementAction();
                checkTurn();
                throw new EatException(dino.returnEnergy());

            }
            if (dino instanceof Erbivorous && tempVege != null) {
                tempVege.setEnergy(dino.incrementEnergy(tempVege.returnEnergy()));
                setTypeVisual(game.returnCurrentPlayer().returnType());
                findType(dino).doneAction();
                dino.doneMovementAction();
                checkTurn();
                throw new EatException(dino.returnEnergy());

            }
            setTypeVisual(findType(dino));
            findType(dino).doneAction();
            dino.doneMovementAction();
            checkTurn();
            throw new MovedException(dino.returnPos());
        }

        if (movement instanceof Carnivorous || movement instanceof Erbivorous) {
            killDinosaur((Dinosaur)movement);
            throw new MoveDeathException();
        }
    }

    /**Compie le azioni per la deposizione di un uovo e ritorna l'eccezione corrispondente all'evento verificatosi*/
    public void layEgg(Dinosaur dino) throws TypeDeathException, DinoDeathException, ActionNotPermittedException, EggDeposedException {
        if (!dino.returnOtherAction()) {
            throw new ActionNotPermittedException();
        }
        if (dino.layEgg()) {
            Dinosaur temp = new Dinosaur(findType(dino).returnName().concat(String.valueOf(findType(dino).returnDinosaurs().size())+1));
            if (temp != null) {
                temp.setPosition(dino.returnAdjacentPosition(game.returnMap()));
                game.returnCurrentPlayer().setEgg(temp);
                findType(dino).doneAction();
                checkTurn();
                throw new EggDeposedException(temp);
            } else {
                throw new ActionNotPermittedException();
            }
        } else {
            killDinosaur(dino);
            throw new DinoDeathException(dino);
        }

    }

    /**Compie le azioni per la crescita di un dinosauro e ritorna l'eccezione corrispondente all'evento verificatosi*/
    public void growDinosaur(Dinosaur dino) throws DinoDeathException, TypeDeathException, ActionNotPermittedException, GrowDinosaurException {
        if (!dino.returnOtherAction()) {
            throw new ActionNotPermittedException();
        }
        int newD = dino.growth();
        if (newD != 0) {
            setTypeVisual(findType(dino));
            findType(dino).doneAction();
            dino.doneOtherAction();
            checkTurn();
            throw new GrowDinosaurException(newD);
        }
        killDinosaur(dino);
        throw new DinoDeathException(dino);

    }

    /**Rimuove il dinosauro e tutti i suoi riferimenti, lo rimuove dalla mappa e lancia un eccezione se il dinosauro era l'ultimo della specie*/
    public void killDinosaur(Dinosaur dino) throws TypeDeathException {
        if (((GroundBox) game.returnMap().returnBox(dino.getX(), dino.getY())).isDinosaurPres() == dino) {
            game.returnMap().removeDinosaur(dino);
        }
        Type type = findType(dino);
        if (findType(dino).dinosaursDead(dino)) {
            setTypeVisual(player.returnType());
            return;
        }
        extinguishType(type);
        throw new TypeDeathException(type);
    }

    /**Decremente il numero di turni vivibile dai dinosauri della specie passata come argomento*/
    public void decrementDinosaursLife(Type type) {
        for (i = 0; i < type.returnDinosaurs().size(); i++) {
            type.returnDinosaurs().get(i).decrementLife();
            if (type.returnDinosaurs().get(i).returnLifeSpan() == 0) {
                try {
                    killDinosaur(type.returnDinosaurs().get(i));
                } catch (TypeDeathException tde) {
                    tde.returnDeadType();
                }
            }
        }
    }

    /**Rimuove tutti i dinosauri della specie e i riferimenti della stessa*/
    public void extinguishType(Type type) {
        type.extinguish();
        for (int i = 0; i < type.returnDinosaurs().size(); i++) {
            try {
                killDinosaur(type.returnDinosaurs().get(i));
            } catch (TypeDeathException tde) {
            }
            game.gameOut(findPlayer(type));
            findPlayer(type).typeDead();
        }
    }

    /**Ritorna true se si è schiuso un uovo*/
    public boolean hatchEgg() {
        egg = game.returnCurrentPlayer().returnEgg();
        if (egg != null && booleanEgg) {
            Dinosaur tempEgg = game.returnCurrentPlayer().returnType().newDinosaur(egg.returnName(), findPlayer(game.returnCurrentPlayer().returnType()));
            tempEgg.setPosition(new Box(egg.getX(), egg.getY()));
            setDinosaur(tempEgg);
            egg = null;
            booleanEgg = false;
            game.returnCurrentPlayer().setEgg(null);
            return true;
        }
        return false;
    }

    /*Metodi per la gestione della partita*/
    /**Setta il nuovo giocatore corrente, fa crescere le piante e degradare le carogne*/
    public void nextTurn() throws TypeDeathException {
        Player tempPlayer = game.returnCurrentPlayer();

        tempPlayer.returnType().decrementTime();
        tempPlayer.returnType().setAction();

        decrementDinosaursLife(tempPlayer.returnType());

        game.setNextPlayer();

        if (game.returnCurrentPlayer().returnEgg() != null) {
            game.returnCurrentPlayer().returnType().newDinosaur2(game.returnCurrentPlayer().returnEgg(), game.returnCurrentPlayer());
            setDinosaur(game.returnCurrentPlayer().returnEgg());
            booleanEgg = true;
        }
        game.returnMap().carrionDegradation();
        game.returnMap().vegetationGrowth();
        game.updateRanking();


        if (tempPlayer.returnType().leftTime() == 0) {
            extinguishType(tempPlayer.returnType());
            throw new TypeDeathException(tempPlayer.returnType());
        }

        setAllVisual();

    }

    /**Ritorna true se il turno è finito per esaurimento delle mosse disponibili per il giocatori*/
    public boolean checkTurn() {
        if (game.returnCurrentPlayer().returnType().returnAvailableAction() == 0) {
            return true;
        }
        return false;
    }

    public Ranking returnRanking() {
        return game.updateRanking();

    }
    
    
    
    public Game returnGame(){
        return this.game;
    }
}
