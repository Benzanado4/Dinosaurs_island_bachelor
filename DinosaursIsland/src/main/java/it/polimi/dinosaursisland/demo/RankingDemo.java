package it.polimi.dinosaursisland.demo;

import it.polimi.dinosaursisland.partita.*;
import it.polimi.dinosaursisland.dinosauri.*;

public class RankingDemo {
   
   public static void main(String[] args){
      Dinosaur dino = new Erbivorous("dino");
      Type erbivoruos = new ErbivorousType("didd",(Erbivorous) dino); 

      Player player = new Player("io","bella",erbivoruos);
      Player player1 = new Player("tu","bella",erbivoruos);
      Player player2 = new Player("lui","bella",erbivoruos);

      Score score1 = new Score(player);
      Score score2 = new Score(player1);
      Score score3 = new Score(player2);

      score1.setScore(500);
      score2.setScore(100);
      score3.setScore(1200);

      ranking.addScore(score1);
      ranking.printRanking();

      System.out.println(score1.returnScore());
      System.out.println(score2.returnScore());
      System.out.println(score3.returnScore());
      }
}