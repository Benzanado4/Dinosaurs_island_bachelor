package it.polimi.dinosaursisland.gui;

import it.polimi.dinosaursisland.networking.PlayerClient;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientGui extends JFrame {
    private JPanel mapPanel = new JPanel();
    private JPanel northPanel= new JPanel();
    private JScrollPane northPane = new JScrollPane(northPanel);
    private JPanel southPanel = new JPanel(new BorderLayout());
    private JScrollPane southPane = new JScrollPane(southPanel);
    private JScrollPane mapPane = new JScrollPane(mapPanel);
    private JPanel localMapPanel = new JPanel();
    private JScrollPane localMapPane = new JScrollPane(localMapPanel);
    private JTextArea textArea = new JTextArea();
    private JScrollPane textAreaPane = new JScrollPane(textArea);
    private JPanel buttonsPanel = new JPanel(new GridLayout(5,4));
    
    private int x, y;
    private ArrayList<JButton> buttons = new ArrayList(0);
    private PlayerClient pc;
    private String token;
    private String input;
    private String dinosaurs;

    public ClientGui(PlayerClient pc){
        this.pc = pc;
        initComponent();
    }

    public ClientGui(){
        initComponent();
    }

    public String generateString(String string){
        string = string.replace("@mappaGenerale,{40,40},", "");
        string = string.replace("[a]", "a");
        string = string.replace("[t]", "t");
        string = string.replace("[v]", "v");
        string = string.replace("[b]", "b");
        string = string.replace(",", "");
        string = string.replace(";", "");
        return string;
    }

    public String[] generateLocalString(String string){
        String[] split = string.split(",");

        x = Integer.valueOf(split[3].replace("{", ""));
        y = Integer.valueOf(split[4].replace("}", ""));
        string = string.replace("0", "");
        string = string.replace("1", "");
        string = string.replace("2", "");
        string = string.replace("3", "");
        string = string.replace("4", "");
        string = string.replace("5", "");
        string = string.replace("6", "");
        string = string.replace("7", "");
        string = string.replace("8", "");
        string = string.replace("9", "");
        string = string.replace(",", "");
        string = string.replace(".", "");
        string = string.replace("@vistaLocale{}{}[", "");

        String newString = "";
        String[] split2 = string.split(";");

        for (int i = x - 1; i >= 0; i--){
            newString = newString.concat(split2[i]);
        }
        split2 = newString.split("\\]");
        return split2;
    }
    
    public void initComponent(){
        this.setResizable(false);
        this.setTitle("L'isola dei Dinosauri");
        this.setLayout(new BorderLayout());
        this.add(northPanel,BorderLayout.NORTH);
        this.add(southPanel,BorderLayout.SOUTH);
        southPanel.add(textAreaPane,BorderLayout.NORTH);
        southPanel.add(buttonsPanel,BorderLayout.SOUTH);
        northPanel.add(mapPane,BorderLayout.EAST);
        northPanel.add(localMapPane);
        mapPane.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));
        mapPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        mapPanel.setPreferredSize(new Dimension(1200, 1200));
        mapPane.setPreferredSize(new Dimension(900, 450));
        textArea.setRows(5);
        
        buttons.add(new JButton("Crea Utente"));
        buttons.add(new JButton("Login"));
        buttons.add(new JButton("Logout"));
        buttons.add(new JButton("Crea razza"));
        buttons.add(new JButton("Accedi alla partita"));
        buttons.add(new JButton("Esci dalla partita"));
        buttons.add(new JButton("Lista giocatori"));
        buttons.add(new JButton("Classifica"));
        buttons.add(new JButton("Mappa partita"));
        buttons.add(new JButton("Vista locale"));
        buttons.add(new JButton("Lista dinosauri"));
        buttons.add(new JButton("Stato dinosauro"));
        buttons.add(new JButton("Muovi dinosauro"));
        buttons.add(new JButton("Cresci dinosauro"));
        buttons.add(new JButton("Deponi uovo"));
        buttons.add(new JButton("Passa turno"));
        buttons.add(new JButton("Accetta turno"));

        ButtonListener buttonListener = new ButtonListener(this);

        for (int i = 0; i < 17; i++){
            buttonsPanel.add(buttons.get(i));
            buttons.get(i).addActionListener(buttonListener);
        }
        mapPanel.setLayout(new GridLayout(40, 40));
        for (int i = 39; i >= 0; i--){
            for (int j = 0; j < 40; j++){
                mapPanel.add(new JLabel(new ImageIcon("notVisible.gif")));
            }
        }
        localMapPanel.setLayout(new GridLayout(9, 9));
        for (int i = 9; i > 0; i--){
            for (int j = 0; j < 9; j++){
                localMapPanel.add(new JLabel(new ImageIcon("notVisible.gif")));
            }
        }
        this.pack();
        this.setVisible(true);
    }

    public void updateLocal(String string){
        localMapPanel.removeAll();
        String[] boxes = generateLocalString(string);
        int k = 0;

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (i < ((9 - x) / 2) || i >= (9 - ((9 - x) / 2)) || j < ((9 - y) / 2) || j >= (9 - ((9 - y) / 2))){
                    localMapPanel.add(new JLabel(new ImageIcon("notVisible.gif")));
                } else {
                    if (k < x * y){
                        if (boxes[k].contains("v") && !boxes[k].contains("d")){
                            localMapPanel.add(new JLabel(new ImageIcon("vegetation.gif")));
                        }
                        if (boxes[k].contains("t")){
                            localMapPanel.add(new JLabel(new ImageIcon("ground.gif")));
                        }
                        if (boxes[k].contains("a")){
                            localMapPanel.add(new JLabel(new ImageIcon("water.gif")));
                        }
                        if (boxes[k].contains("b")){
                            localMapPanel.add(new JLabel(new ImageIcon("notVisible.gif")));
                        }
                        if (boxes[k].contains("c") && !boxes[k].contains("d")){
                            localMapPanel.add(new JLabel(new ImageIcon("carrion.gif")));
                        }
                        if (boxes[k].contains("d")){
                            localMapPanel.add(new JLabel(new ImageIcon("dinosaurold2.gif")));
                        }
                        k++;
                    }
                }
            }
        }
        localMapPanel.invalidate();
        localMapPanel.validate();
        localMapPanel.repaint();
    }

    public void updateMap(String string){
        mapPanel.removeAll();
        string = generateString(string);
        mapPanel.setLayout(new GridLayout(40, 40));

        for (int i = 39; i >= 0; i--){
            for (int j = 0; j < 40; j++){
                if (!(string.charAt(i * 40 + j) == 'b')){
                    if (string.charAt(i * 40 + j) == 'v'){
                        mapPanel.add(new JLabel(new ImageIcon("vegetation.gif")));
                    }
                    if (string.charAt(i * 40 + j) == 't'){
                        mapPanel.add(new JLabel(new ImageIcon("ground.gif")));
                    }
                    if (string.charAt(i * 40 + j) == 'a'){
                        mapPanel.add(new JLabel(new ImageIcon("water.gif")));
                    }
                }
                else{
                    mapPanel.add(new JLabel(new ImageIcon("notVisible.gif")));
                }
            }
            mapPanel.invalidate();
            mapPanel.validate();
            mapPanel.repaint();
        }
    }

    public String readLine(){
        return "";
    }
    public void writeLine(String string){
        textArea.append(string);
        textArea.append("\n");
    }
    public synchronized String sendCommandString(String string){
        String stringTemp =pc.sendCommand(string);
        return stringTemp;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String returnToken(){
        return token;
    }
    public JTextArea returnaJTextArea(){
        return textArea;
    }
    public synchronized void resetInput(){
        input=null;
    }
    public String getInput(){
        return input;
    }
    public void setDinosaurs(String string){
        this.dinosaurs=string;
    }
    public String returnDinosaurs(){
        return dinosaurs;
    }
}
