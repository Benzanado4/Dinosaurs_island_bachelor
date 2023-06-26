package it.polimi.dinosaursisland.gui;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateTypeBox extends JFrame {
    private JPanel jPanel = new JPanel();
    private JLabel nameLabel = new JLabel("Nome Razza:");
    private JLabel typeLabel = new JLabel("Tipo Razza(c:Carnivora,e:Erbivora):");
    private JTextField typeNameField = new JTextField(10);
    private JTextField typeField = new JTextField(1);
    private JButton confirmButton = new JButton("OK");
    private String string;
    final private ClientGui cg;

    public CreateTypeBox(ClientGui clientGui){
        cg = clientGui;
        this.add(jPanel);
        jPanel.add(nameLabel);
        jPanel.add(typeNameField);
        jPanel.add(typeLabel);
        jPanel.add(typeField);
        jPanel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (cg.returnToken() != null){
                    string = "@creaRazza,".concat(cg.returnToken()).concat(",").concat(typeNameField.getText()).concat(",").concat(typeField.getText());
                    cg.sendCommandString(string);
                    hideIt();
                }
            }
        });
    }

    public void showIt(){
        this.setTitle("Crea Razza");
        this.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void hideIt(){
        this.setVisible(false);
    }
}
