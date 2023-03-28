package it.polimi.dinosaursisland.networking;

import java.util.ArrayList;

public class Encoder {
    private String commandString;
    public String encodeCommand(Command command) {
        commandString = command.returnCommand();
        if(!command.returnParameters().isEmpty()){
            for (int i = 0; i < command.returnParameters().size(); i++) {
                commandString=commandString.concat(",");
                commandString=commandString.concat(command.returnParameter(i));
            }   
        }
        return commandString;
    }
}
