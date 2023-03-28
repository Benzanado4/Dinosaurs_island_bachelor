package it.polimi.dinosaursisland.networking;

public class Encoder {

    private String commandString;

    /**Codifica un comando ritornando la stringa corrispondente**/
    public String encodeCommand(Command command) {


        commandString = command.returnCommand();

        if (!command.returnParameters().isEmpty()) {
            for (int i = 0; i < command.returnParameters().size(); i++) {
                commandString = commandString.concat(",");
                commandString = commandString.concat(command.returnParameter(i));

            }
        }
        return commandString;
    }
}
