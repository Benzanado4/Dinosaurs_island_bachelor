package it.polimi.dinosaursisland.dinosauri;

/**
 * La Specie erbivora.
 */
public class ErbivorousType extends Type {

    public ErbivorousType(String name, Erbivorous first) {
        dinos.add(first);
        setName(name);
        setFullVisual();
        numDinosaur++;
    }
}
