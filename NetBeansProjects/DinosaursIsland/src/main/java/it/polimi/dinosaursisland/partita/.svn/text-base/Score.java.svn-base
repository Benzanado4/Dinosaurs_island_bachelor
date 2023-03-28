package it.polimi.dinosaursisland.partita;


import java.io.Serializable;
/**
 * Classe che rappresenta il punteggio da assegnare ai giocatori.
 */

public class Score implements Serializable {

    private int score;
    private Player player;

    public Score(Player player) {
        this.player = player;
        assignScore();
    }

    public int returnScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String returnPlayerId() {
        return player.returnId();
    }

    public Player returnPlayer() {
        return player;
    }

    public int assignScore() {
        score = 0;
        for (int i = 0; i < player.returnType().returnDinosaurs().size(); i++) {
            score = score + (1 + player.returnType().returnDinosaurs().get(i).returnSize());
        }

        return score;
    }
}
