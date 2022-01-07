/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.View;

import com.cv19.view.event.EventLoginCallBack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author ThaiTran
 */
public class LoginView extends JFrame implements ActionListener {

    private JButton btnLogin;
    private JCheckBox cbShowpass;
    private JPanel container;
    private JLabel lbPass;
    private JLabel lbTilte;
    private JLabel lbUsername;
    private JPasswordField txtPass;
    private JTextField txtUsername;
    private EventLoginCallBack callback;

    public LoginView(EventLoginCallBack callback) {
        this.callback = callback;
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc;

        container = new JPanel();
        lbTilte = new JLabel();
        lbUsername = new JLabel();
        lbPass = new JLabel();
        txtUsername = new JTextField("Enter ID");
        txtPass = new JPasswordField("password");
        cbShowpass = new JCheckBox();
        btnLogin = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        container.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        container.setLayout(new GridBagLayout());

        lbTilte.setFont(new Font("Tahoma", 1, 24)); // NOI18N
        lbTilte.setText("ACCOUNT LOGIN");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 30, 0);
        container.add(lbTilte, gbc);

        lbUsername.setText("USERNAME");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        container.add(lbUsername, gbc);

        lbPass.setText("PASSWORD");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        container.add(lbPass, gbc);

        txtUsername.addFocusListener(eventTxtName());
        txtUsername.setForeground(Color.gray);
        txtUsername.setFont(new Font("Tahoma", 0, 14));
        txtUsername.setMargin(new Insets(2, 10, 2, 10));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 20, 20, 0);
        container.add(txtUsername, gbc);

        txtPass.setEchoChar((char)0);
        txtPass.addFocusListener(eventTxtpass());
        txtPass.setForeground(Color.gray);
        txtPass.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        txtPass.setMargin(new Insets(2, 10, 2, 10));
        txtPass.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(0, 20, 10, 0);
        container.add(txtPass, gbc);

        cbShowpass.addItemListener(eventShowpass());
        cbShowpass.setText("Show password");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 20, 0, 0);
        container.add(cbShowpass, gbc);

        btnLogin.setText("Log in");
        btnLogin.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 20, 10, 0);
        container.add(btnLogin, gbc);

        txtPass.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                loginEvent();
            }
        });

        getContentPane().add(container);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnLogin){
            loginEvent();
        }
    }

    void loginEvent(){
        String username = txtUsername.getText();
        String password  = String.valueOf(txtPass.getPassword());
        if(!username.isBlank() && !password.isBlank()){
            callback.authorize(username, password);
        }
    }
    
    FocusListener eventTxtName() {
        FocusListener event = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsername.getText().equals("Enter ID")) {
                    txtUsername.setText("");
                    txtUsername.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsername.getText().isEmpty()) {
                    txtUsername.setForeground(Color.GRAY);
                    txtUsername.setText("Enter ID");
                }
            }
        };
        return event;

    }

    FocusListener eventTxtpass() {
        FocusListener event = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String password = String.valueOf(txtPass.getPassword());

                if (password.toLowerCase().equals("password")) {
                    txtPass.setText("");
                    txtPass.setForeground(Color.black);
                    txtPass.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                 String password = String.valueOf(txtPass.getPassword());
                if (password.isEmpty()) {
                    txtPass.setEchoChar((char)0);
                    txtPass.setText("password");
                    txtPass.setForeground(Color.GRAY);
                    
                }
            }
        };
        return event;

    }
    
    ItemListener eventShowpass(){
        ItemListener event = new  ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cbShowpass.isSelected()){
                    txtPass.setEchoChar((char)0);
                }
                else{
                    txtPass.setEchoChar('*');
                }
            }
        };
        return event;
    }
}
