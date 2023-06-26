package it.polimi.dinosaursisland.dinosauri;

public class ErbivorousType extends Type {

    public ErbivorousType(String name, Erbivorous first){
        dinos.add(first);
        SetName(name);
        setFullVisual();

    }
}
