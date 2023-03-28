package it.polimi.dinosaursisland.partita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe della classfica della partita.
 */

public class Ranking implements Serializable {

    private ArrayList<Score> scoreRanking = new ArrayList<Score>(0);

    public Ranking(ArrayList<Player> players) {
        updateRanking(players);

    }

    public void updateRanking(ArrayList<Player> players) {

        scoreRanking.clear();

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).returnType() != null && players.get(i).returnType().exist()) {
                scoreRanking.add(players.get(i).returnScore());
            }
        }

        for (int i = 0; i < scoreRanking.size() - 1; i++) {
            for (int j = i + 1; j < scoreRanking.size(); j++) {
                if (scoreRanking.get(i).assignScore() < scoreRanking.get(j).assignScore()) {
                    Collections.swap(scoreRanking, i, j);
                }
            }
        }



    }

    public ArrayList<Score> returnRanking() {
        return scoreRanking;
    }

    public void printRanking() {
        for (int i = 0; i < scoreRanking.size(); i++) {
            System.out.print(scoreRanking.get(i).returnPlayerId() + " - ");
            System.out.println(scoreRanking.get(i).returnScore());
        }
    }

    public String returnStringRanking() {
        String string = "@classifica";
        for (int i = 0; i < scoreRanking.size(); i++) {
            if (scoreRanking.get(i).returnPlayer().returnType() == null) {
                string = string.concat(",{").concat(scoreRanking.get(i).returnPlayerId()).concat(",").concat(",n").concat("}");
            } else {
                string = string.concat(",{").concat(scoreRanking.get(i).returnPlayerId()).concat(",").concat(scoreRanking.get(i).returnPlayer().returnType().returnName()).concat(",").concat(String.valueOf(scoreRanking.get(i).returnScore())).concat(",s").concat("}");
            }
        }
        return string;
    }
}
