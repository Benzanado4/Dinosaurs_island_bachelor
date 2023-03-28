    
package it.polimi.dinosaursisland.demo;

import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.mappa.*;
import java.util.Scanner;

public class DinoDemo {

    public static void main(String[] args) {
        Map map1 = new Map();
        int i,j;
        int vegetationCont = 0;
        map1.createMap();
        
        System.out.println(map1.returnVegetation());
        
        
         
         System.out.println(vegetationCont);
         System.out.println(map1.returnVegeList().size());
    }
}
    
