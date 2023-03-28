package it.polimi.dinosaursisland.partita;

import it.polimi.dinosaursisland.dinosauri.*;
import java.io.Serializable;

/**
 * La classe del giocatore.
 */


public class Player implements Serializable {

    private String userId;
    private String password;
    private Type userType;
    private Dinosaur egg = null;
    private Score score;
    private String gif;
    private String token;

    public Player(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Player(String userId, String password, Type userType) {
        this.userId = userId;
        this.password = password;
        this.userType = userType;
        setScore();
    }

    public Dinosaur returnEgg() {
        return egg;
    }

    public void setEgg(Dinosaur dino) {
        egg = dino;
    }

    public String returnId() {
        return userId;
    }

    public void setType(Type type) {
        this.userType = type;
    }

    public Type returnType() {
        return userType;
    }

    public boolean passwordCorrect(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public void typeDead() {
        userType = null;
    }

    public Score returnScore() {
        setScore();
        return score;
    }

    public int setScore() {
        score = new Score(this);

        return score.assignScore();
    }

    public void setGif(String string) {
        gif = string;

    }

    public String returnGif() {
        return gif;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String returnToken() {
        return token;
    }

    public void revokeToken() {
        token = null;
    }
}
