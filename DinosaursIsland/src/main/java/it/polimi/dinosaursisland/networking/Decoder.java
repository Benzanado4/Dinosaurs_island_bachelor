package it.polimi.dinosaursisland.networking;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.jdesktop.el.impl.parser.Token;
import sun.awt.NullComponentPeer;

public class Decoder {
    private String commandString;
    private ArrayList<String> parameters = new ArrayList(0);
    private Command command;
    private String token;
    private Integer x,y;

    public Command decodeCommand(String stringa){
        token=null;
        parameters.clear();
        x=null;
        y=null;
        if(stringa==null){
            return null;
        }
        if (!stringa.startsWith("@") || stringa.isEmpty()){
            return new Command("@comandoErrato");
        }
        if (!stringa.contains(",")){
            return new Command(stringa);
        }
        String[] split = stringa.split(",");
        commandString = split[0];
        for (int i = 1; i < split.length; i++){    
            parameters.add(split[i]);
            if (split[i].startsWith("T=")){
                token = split[i];
            }
        }
        if(parameters.size()==4 && parameters.get(2).contains("{")){
            x=Integer.valueOf(split[3].replace("{",""));
            y=Integer.valueOf(split[4].replace("}",""));
            
        }
        if(token!=null){
            command=new Command(commandString, token, parameters);
            command.setX(x);
            command.setY(y);
            return command;
        }
        return new Command(commandString, parameters);
    }
}
