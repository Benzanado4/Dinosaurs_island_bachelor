package it.polimi.dinosaursisland.dinosauri;

public class CarnivorousType extends Type{

    public CarnivorousType(String name, Carnivorous first){
        dinos.add(first);
        SetName(name);
        setFullVisual();
    }
}
