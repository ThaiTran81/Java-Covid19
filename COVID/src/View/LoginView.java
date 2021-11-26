package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import Controller.*;

public class LoginView extends JFrame implements ActionListener{
    private Container container = getContentPane();
    private JLabel usernameLabel = new JLabel("User name");
    private JLabel passwordLabel = new JLabel("Password");
    private JTextField username = new JTextField("username");
    private JPasswordField password = new JPasswordField("password");
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");
    private JCheckBox checkShowPassword = new JCheckBox("Show password");
    private JButton register = new JButton("Register");

    public LoginView() {
        setTitle("Login Form");
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        container.setLayout(null);

        usernameLabel.setBounds(50, 150, 100, 30);
        container.add(usernameLabel);

        passwordLabel.setBounds(50, 220, 100, 30);
        container.add(passwordLabel);

        username.setBounds(150, 150, 150, 30);
        username.setForeground(new Color(153, 153, 153));
        container.add(username);
        username.addActionListener(this);
        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (username.getText().trim().toLowerCase().equals("username")) {
                    username.setText("");
                    username.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (username.getText().trim().equals("") || username.getText().trim().toLowerCase().equals("username")) {
                    username.setText("username");
                    username.setForeground(new Color(153, 153, 153));
                }
            }
        });

        password.setBounds(150, 220, 150, 30);
        password.setForeground(new Color(153, 153, 153));
        container.add(password);
        password.addActionListener(this);
        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                String pass = String.valueOf(password.getPassword());
                if (pass.trim().toLowerCase().equals("password")) {
                    password.setText("");
                    password.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String pass = String.valueOf(password.getPassword());
                if (pass.trim().equals("") || pass.trim().toLowerCase().equals("password")) {
                    password.setText("password");
                    password.setForeground(new Color(153, 153, 153));
                }
            }
        });

        loginButton.setBounds(30, 300, 80, 30);
        container.add(loginButton);
        loginButton.addActionListener(this);

        resetButton.setBounds(130, 300, 80, 30);
        container.add(resetButton);
        resetButton.addActionListener(this);

        register.setBounds(230, 300, 100, 30);
        container.add(register);
        register.addActionListener(this);

        checkShowPassword.setBounds(150, 250, 150, 30);
        container.add(checkShowPassword);
        checkShowPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String usernametext = username.getText();
            String passwordtext = String.valueOf(password.getPassword());

            //This need to be rewritten
            boolean check = false;
            try {
                check = LoginController.checkManagerExist();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (check == true){
                //Page transition here
            } else {
                //Create account for admin
            }
        }
        if (e.getSource() == resetButton) {
            username.setText("username");
            username.setForeground(new Color(153, 153, 153));
            password.setText("password");
            password.setForeground(new Color(153, 153, 153));
        }
        if (e.getSource() == checkShowPassword) {
            if (checkShowPassword.isSelected()) {
                password.setEchoChar((char) 0);
            } else {
                password.setEchoChar('*');
            }
        }
        if (e.getSource() == username) {
            username.setText("");
        }
        if (e.getSource() == password) {
            password.setText("");
        }
        if (e.getSource() == register) {

        }
    }
}
