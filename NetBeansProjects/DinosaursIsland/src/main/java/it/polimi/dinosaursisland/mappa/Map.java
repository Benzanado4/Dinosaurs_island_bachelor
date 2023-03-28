package it.polimi.dinosaursisland.mappa;

import it.polimi.dinosaursisland.dinosauri.Dinosaur;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che rappresenta la Mappa.
 */

public class Map implements Serializable {
    
    /*Attributi*/
    private Box[][] map = new Box[40][40];
    private ArrayList<Box> availableBox = new ArrayList(); //Contiene le caselle ancora disponibili per la costruzione della mappa
    private ArrayList<WaterBox> lakeBox = new ArrayList(0); //Contiene le caselle di un lago in fase di costruzione dello stesso
    private ArrayList<Carrion> carrionList = new ArrayList(0);
    private ArrayList<Vegetation> vegetationList = new ArrayList(0);
    private int i, j; //contatori
    private int ground = 1280;  //numero di caselle di terra disponibili per la creazione della mappa
    private int water = 320;    //numero di caselle di acqua disponibili per la creazione della mappa
    private int vegetation = 512;   //vegetazioni disponibili per la creazione della mappa
    private int carrion = 20;
    private int x, y; //interi per le posizioni dei Box
    private Object movement;

    /**Crea una mappa composta solo da Box, aggiungendo tutte le caselle a availableBox**/
    public void createMap() {
        for (i = 0; i < 40; i++) {
            for (j = 0; j < 40; j++) {
                map[39 - i][j] = new Box(i, j);
                availableBox.add(i * 40 + j, map[39 - i][j]);
            }
        }
        setOcean();
        while (water > 5) {
            createLake();
        }
        setGround();



    }

    /**Crea i bordi della mappa settandoli a WaterBox, cancella i Box corrispondenti e quelli adiacenti da availableBox**/
    public void setOcean() {
        for (i = 0; i < 40; i++) {
            map[0][i] = new WaterBox(39, i);
            map[39][i] = new WaterBox(0, i);
            water = water - 2;
            availableBox.remove(map[0][i]);
            availableBox.remove(map[39][i]);
            availableBox.remove(map[1][i]);
            availableBox.remove(map[38][i]);


        }
        for (i = 1; i < 39; i++) {
            map[i][0] = new WaterBox(39 - i, 0);
            map[i][39] = new WaterBox(39 - i, 39);
            water = water - 2;
            availableBox.remove(map[i][0]);
            availableBox.remove(map[i][39]);
            availableBox.remove(map[i][1]);
            availableBox.remove(map[i][38]);
        }
    }

    /**Crea i laghi e li aggiunge alla mappa**/
    public void createLake() {
        int n = (int) (Math.random() * 10) + 5; //numero di caselle del lago            

        if (water > 15) {

            int first = (int) (Math.random() * availableBox.size());  //Prima Box a random nell availableBox
            WaterBox temp = new WaterBox(availableBox.get(first));

            map[39 - temp.returnX()][temp.returnY()] = new WaterBox(temp);  //Aggiunta della Box alla mappa
            lakeBox.add((WaterBox) map[39 - temp.returnX()][temp.returnY()]);   //e alle caselle del lago

            availableBox.remove(first);  //rimozione dalle box disponibili
            water--;

            for (i = 1; i < n; i++) {

                temp = new WaterBox(randomAdjacent(lakeBox.get((int) (Math.random() * lakeBox.size()))));
                while (temp.x == 0 && temp.y == 0) {
                    temp = new WaterBox(randomAdjacent(lakeBox.get((int) (Math.random() * lakeBox.size()))));

                }
                map[39 - temp.x][temp.y] = temp;
                lakeBox.add(temp);
                availableBox.remove(temp);
                water--;
        }
            freeLakeAdjacent(lakeBox);
        } else {
            int first = (int) (Math.random() * availableBox.size());
            WaterBox temp = new WaterBox(availableBox.get(first));

            map[39 - temp.returnX()][temp.returnY()] = new WaterBox(temp);
            lakeBox.add((WaterBox) map[39 - temp.returnX()][temp.returnY()]);

            availableBox.remove(first);
            water--;
            j = water;

            for (i = 1; i < j + 1; i++) {

                temp = new WaterBox(randomAdjacent(lakeBox.get((int) (Math.random() * lakeBox.size()))));
                while (temp.x == 0 && temp.y == 0) {
                    temp = new WaterBox(randomAdjacent(lakeBox.get((int) (Math.random() * lakeBox.size()))));

                }
                map[39 - temp.x][temp.y] = temp;
                lakeBox.add(temp);
                availableBox.remove(temp);
                water--;


            }
            freeLakeAdjacent(lakeBox);
        }
 }

    /**Rimuove le caselle adiacenti al lago dall'arraylist availableBox**/
    public void freeLakeAdjacent(ArrayList<WaterBox> lake) {
        for (i = 0; i < lake.size(); i++) {
                availableBox.remove(map[39 - lake.get(i).x - 1][lake.get(i).y]);
                availableBox.remove(map[39 - lake.get(i).x][lake.get(i).y + 1]);
                availableBox.remove(map[39 - lake.get(i).x + 1][lake.get(i).y]);
                availableBox.remove(map[39 - lake.get(i).x][lake.get(i).y - 1]);
        }
    }

    /**Ritorna a random una delle caselle adiacenti alla casella passata come argomento in fase di costruzione della mappa**/
    public Box randomAdjacent(WaterBox box) {
        int n = (int) (Math.random() * 3);
        switch (n) {
            case 0: {

                if (availableBox.contains(map[39 - box.x - 1][box.y])) {
                    return map[39 - box.x - 1][box.y];
                } else {
                    return null;
                }
            }

            case 1: {
                if (availableBox.contains(map[39 - box.x][box.y + 1])) {
                    return map[39 - box.x][box.y + 1];
                } else {
                    return null;
                }
            }

            case 2: {
                if (availableBox.contains(map[39 - box.x + 1][box.y])) {
                    return map[39 - box.x + 1][box.y];
                } else {
                    return null;
                }
            }

            case 3: {
                if (availableBox.contains(map[39 - box.x][box.y - 1])) {
                    return map[39 - box.x][box.y - 1];
                } else {
                    return null;
                }
            }

        }
        return null;
    }

    /**Aggiunge alla mappa le caselle di terra**/
    public void setGround() {
        for (i = 0; i < 40; i++) {
            for (j = 0; j < 40; j++) {
                if (map[i][j] instanceof WaterBox) {
                } else {
                    map[i][j] = new GroundBox(map[i][j]);
                    ground--;
                }
            }
        }
        setVegetation();
        setCarrion();

    }

    /**Aggiunge a random la vegetazione*/
    public void setVegetation() {
        while (vegetation != 0) {
            i = (int) (Math.random() * 39);
            j = (int) (Math.random() * 39);

            if (map[i][j] instanceof GroundBox) {

                GroundBox temp = new GroundBox(map[i][j]);
                Vegetation vegeTemp = new Vegetation();
                vegetationList.add(vegeTemp);
                temp.setVegetationPres(vegeTemp);
                map[i][j] = temp;
                temp = null;
                vegetation--;

            }

        }
    }

    /**Aggiunge a random le carogne**/
    public void setCarrion() {
        while (carrion != 0) {
            i = (int) (Math.random() * 39);
            j = (int) (Math.random() * 39);

            if (map[i][j] instanceof GroundBox) {

                GroundBox temp = new GroundBox(map[i][j]);
                Carrion tempCarr = new Carrion(temp.returnX(), temp.returnY());
                temp.setCarrionPres(tempCarr);
                carrionList.add(tempCarr);
                map[i][j] = temp;
                temp = null;
                carrion--;

            }
        }
    }
    
    public int returnCarrion(){
        return this.carrion;
    }
    /**Muove il dinosauro, svuota la casella corrente,assegna il dinosauro alla casella di destinazione
     *
     * @return movemet.
     */
    public Object moveDinosaur(Dinosaur dino, Box box) {
        x = dino.getX();
        y = dino.getY();
        movement = dino.movement(this, box);

        if (movement instanceof Box) {
            ((GroundBox) returnBox(x, y)).moveDinosaurAway();
            ((GroundBox) returnBox(dino.getX(), dino.getY())).setDinosaurPres(dino);
            dino.setVisual(this);

            return movement;
        }
        return movement;
    }
    /**Mette il dinosauro sulla mappa, assegna il dinosauro alla casella**/
    public boolean setDinosaur(Dinosaur dino, int x, int y) {
        if (returnBox(x, y) instanceof GroundBox && ((GroundBox) returnBox(x, y)).isDinosaurPres() == null) {
            ((GroundBox) returnBox(x, y)).setDinosaurPres(dino);
            dino.setPosition(returnBox(x, y));
            return true;
        }
        return false;
    }

    public void removeDinosaur(Dinosaur dino) {
        ((GroundBox) returnBox(dino.getX(), dino.getY())).moveDinosaurAway();
    }

    public void printAvailable() {

        System.out.println();
        System.out.print(availableBox);

    }

    /**Stampa la mappa**/
    public void printMap() {
        for (i = 0; i < 40; i++) {

            System.out.println();
            for (j = 0; j < 40; j++) {
                if (map[i][j] instanceof GroundBox) {
                    GroundBox temp = (GroundBox) map[i][j];
                    if (temp.isVegetationPres() != null) {
                        System.out.printf("H "/*"%d,%d | ",map[i][j].x,map[i][j].y*/);
                    } else if (temp.isCarrionPres() != null) {
                        System.out.printf("A ");
                    } else {
                        System.out.printf(". ");
                    }
                } else if (map[i][j] instanceof WaterBox) {
                    System.out.printf("  "/*"%d,%d | ",map[i][j].x,map[i][j].y*/);
                } else {
                    System.out.printf("  "/*"%d,%d | ",map[i][j].x,map[i][j].y*/);
                }
           }
        }

    }

    
    /**Ritorna il Box corrispondente**/
    public Box returnBox(int x, int y) {

        return map[39 - x][y];

    }

    /**Sceglie una casella random tra quelle percorribili**/
        public Box returnRandomAvailableBox() {
        x = (int) (Math.random() * 39);
        y = (int) (Math.random() * 39);
        while (returnBox(x, y) instanceof WaterBox || ((GroundBox) returnBox(x, y)).isDinosaurPres() != null || ((GroundBox) returnBox(x, y)).isCarrionPres() != null || ((GroundBox) returnBox(x, y)).isVegetationPres() != null) {
            x = (int) (Math.random() * 39);
            y = (int) (Math.random() * 39);
        }

        return returnBox(x, y);

    }

    public ArrayList<Carrion> returnCarrions() {
        return carrionList;
    }
    
    public ArrayList<Carrion> newCarrion(Carrion oldCarrion) {
        carrionList.remove(oldCarrion);
        ((GroundBox) returnBox(oldCarrion.getX(), oldCarrion.getY())).setCarrionPres(null);

        Box tempBox = returnRandomAvailableBox();
        Carrion tempCarrion = new Carrion(tempBox.returnX(), tempBox.returnY());
        ((GroundBox) tempBox).setCarrionPres(tempCarrion);
        carrionList.add(tempCarrion);
        return carrionList;

    }

    public void carrionDegradation() {
        for (i = 0; i < carrionList.size(); i++) {
            Carrion carrion = carrionList.get(i);
            if (!carrion.reduceEnergy()) {
                newCarrion(carrion);
            }
        }
    }

    public void vegetationGrowth() {
        for (i = 0; i < vegetationList.size(); i++) {
            vegetationList.get(i).growEnergy();
        }
    }

    public ArrayList<Box> returnAvailable() {
        return availableBox;

    }

    public int returnVegetation() {
        return vegetation;
    }
    public ArrayList<Vegetation> returnVegeList(){
        return vegetationList;
    }
    
    public int returnWater(){
        return this.water;
    }
    public int retrunGround(){
        return this.ground;
    }
}
