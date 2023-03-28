package it.polimi.dinosaursisland.Gui;

import it.polimi.dinosaursisland.mappa.*;
import java.awt.event.*;
import javax.swing.JFrame.*;
import javax.swing.*;
import javax.swing.Action;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

class guidino extends JFrame{
    private JPanel map;
    private Map m = new Map();
    private JButton b[] [] = new JButton[40][40];
    MyActionListener guidino;
    
    public guidino(){
        super("DINOSAURS ISLAND");
        setSize(40,40);
        setLocationRelativeTo(null);
        setResizable(false);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        map = new JPanel();
        
        MyActionListener listener = new MyActionListener();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        map.setLayout(layout);
        
        for (int i=0;i<39;i++){
            for(int j=0;j<39;j++){
                b[i][j] = new JButton();    
                b[i][j].addActionListener(listener);
                map.add(b[i][j]);
            }
        }    
        add(map);
        pack();
        setVisible(true);
    }
    //Jbutton MyButton = new JButton(new ImageIcon("image.png"));
    public static void main(String[] args){
        new guidino();
    }
}

