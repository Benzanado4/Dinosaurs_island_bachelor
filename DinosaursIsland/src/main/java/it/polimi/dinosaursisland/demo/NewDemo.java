package it.polimi.dinosaursisland.demo;
        
import it.polimi.dinosaursisland.partita.*;
import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.Gui.*;

public class NewDemo {

    public static void main(String[] args){
        Map map = new Map();
        Game bella = new Game();

        bella.Register("bella", "pass", 1,"bellaaa" , "io");
        GameController game = bella.returnController();
        System.out.println(game.findDinoByName("io").returnVisual().size());
        System.out.println(game.findDinoByName("io").returnNumRow());
        System.out.println(game.findDinoByName("io").returnNumColumn());
        System.out.println(game.localVisual(game.findDinoByName("io")));

        MapFrame mapframe = new MapFrame(bella.returnMap());
        mapframe.initComponents();
        mapframe.setVisibility(game.findType(game.findDinoByName("io")));
    }
}