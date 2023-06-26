package it.polimi.dinosaursisland.networking;

import java.util.ArrayList;

public class Command {
    private String command;
    private ArrayList<String> parameters = new ArrayList(0);
    private String token;
    private Integer x,y;

    public Command(String command, ArrayList<String> parameters){
        this.command = command;
        this.parameters.addAll(parameters);
    }
    
    public Command(String command,String token, ArrayList<String> parameters){
        this.command = command;
        this.parameters.addAll(parameters);
        this.token=token;
    }
    
    public Command(String command){
        this.command = command;
    }
    public Command(){
    }
    public String returnCommand(){
        return command;
    }
    public String returnParameter(int i){
        return parameters.get(i);
    }
    public ArrayList<String> returnParameters(){
        return parameters;
    }
    public void setToken(String token){
       this.token=token;
    }
    public String returnToken(){
       return token;
    }
    public void setX(Integer x){
        this.x=x;
    }
    public void setY(Integer y){
        this.y=y;
    }
    public Integer getX(){
        return x;
    }
    public Integer getY(){
        return y;
    }
}
