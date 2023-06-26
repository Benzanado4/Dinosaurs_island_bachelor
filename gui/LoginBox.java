package it.polimi.dinosaursisland.gui;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginBox extends JFrame {
    private JPanel jPanel = new JPanel();
    private JLabel idLabel = new JLabel("Nome Utente:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JTextField idField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JButton confirmButton = new JButton("OK");
    private String string;
    final private ClientGui cg;

    public LoginBox(ClientGui clientGui){
        cg = clientGui;
        this.add(jPanel);
        jPanel.add(idLabel);
        jPanel.add(idField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);
        jPanel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                string = "@login,".concat(idField.getText()).concat(",").concat(passwordField.getText());
                cg.sendCommandString(string);
                hideIt();
            }
        });
    }

    public void showIt(){
        this.setTitle("Login");
        this.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void hideIt(){
        this.setVisible(false);
    }
}