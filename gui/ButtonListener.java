package it.polimi.dinosaursisland.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private ClientGui cg;
    private String string;

    public ButtonListener(ClientGui cg){
        this.cg = cg;
    }

    public synchronized void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Crea Utente")){
            (new NewUserBox(cg)).showIt();
        }
        if (e.getActionCommand().equals("Login")){
            (new LoginBox(cg)).showIt();
        }
        if (e.getActionCommand().equals("Logout")){
            string = "@logout,".concat(cg.returnToken());
            cg.sendCommandString(string);
        }
        if (e.getActionCommand().equals("Crea razza")){
            (new CreateTypeBox(cg)).showIt();
        }
        if (e.getActionCommand().equals("Accedi alla partita") && cg.returnToken() != null){
            string = "@accessoPartita,".concat(cg.returnToken());
            cg.sendCommandString(string);
            string = "@mappaGenerale,".concat(cg.returnToken());
            cg.sendCommandString(string);   
            string = "@listaDinosauri,".concat(cg.returnToken());
            cg.sendCommandString(string);
        }
        if (e.getActionCommand().equals("Esci dalla partita") && cg.returnToken() != null){
            string = "@uscitaPartita,".concat(cg.returnToken());
            cg.sendCommandString(string);
        }
        if (e.getActionCommand().equals("Lista dinosauri")){
            string = "@listaDinosauri,".concat(cg.returnToken());
            cg.sendCommandString(string);
            (new DinoChoiceBox(cg, "", cg.returnDinosaurs())).showIt();
        }
        if (e.getActionCommand().equals("Lista giocatori")){
            string = "@listaGiocatori,".concat(cg.returnToken());
            cg.sendCommandString(string);
        }
        if (e.getActionCommand().equals("Classifica")){
            string = "@classifica,".concat(cg.returnToken());
            cg.sendCommandString(string);
        }
        if (e.getActionCommand().equals("Mappa partita")){
            string = "@mappaGenerale,".concat(cg.returnToken());
            cg.sendCommandString(string);
        }
        if (e.getActionCommand().equals("Vista locale")){
            (new DinoChoiceBox(cg, "@vistaLocale", cg.returnDinosaurs())).showIt();
            //(new DinosaurChoiceBox(cg, "@vistaLocale")).showIt();
        }
        if (e.getActionCommand().equals("Stato dinosauro")){
            cg.sendCommandString("@listaDinosauri,".concat(cg.returnToken()));
            (new DinoChoiceBox(cg, "@statoDinosauro", cg.returnDinosaurs())).showIt();
            //(new DinosaurChoiceBox(cg, "@statoDinosauro")).showIt();
        }
        if (e.getActionCommand().equals("Cresci dinosauro")){
            cg.sendCommandString("@listaDinosauri,".concat(cg.returnToken()));
            (new DinoChoiceBox(cg, "@cresciDinosauro", cg.returnDinosaurs())).showIt();
            //(new DinosaurChoiceBox(cg, "@statoDinosauro")).showIt();
        }
        if (e.getActionCommand().equals("Deponi uovo")){
            cg.sendCommandString("@listaDinosauri,".concat(cg.returnToken()));
            (new DinoChoiceBox(cg, "@deponiUovo", cg.returnDinosaurs())).showIt();
            //(new DinosaurChoiceBox(cg, "@statoDinosauro")).showIt();
        }
        if (e.getActionCommand().equals("Muovi dinosauro")){
            cg.sendCommandString("@listaDinosauri,".concat(cg.returnToken()));
            (new DinosaurMoveBox(cg, cg.returnDinosaurs())).showIt();
        }
        if (e.getActionCommand().equals("Passa turno")){
            cg.sendCommandString("@passaTurno,".concat(cg.returnToken()));
        }
        if (e.getActionCommand().equals("Accetta turno")){  
            cg.sendCommandString("@confermaTurno,".concat(cg.returnToken()));
        }
    }
}
