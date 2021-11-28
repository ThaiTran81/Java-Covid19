package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminRegisterView extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel title = new JLabel("Admin Registration");

    private JLabel usernameLabel = new JLabel("Username");
    private JTextField username = new JTextField();
    private JLabel passwordLabel = new JLabel("Password");
    private JPasswordField password = new JPasswordField();
    private JLabel repeatpasswordLabel = new JLabel("Confirm");
    private JPasswordField repeatpassword = new JPasswordField();

    private JCheckBox acept = new JCheckBox("Accept Terms and Conditions");
    private JButton sub = new JButton("Submit");
    private JButton reset = new JButton("Reset");
    private JCheckBox showpassword = new JCheckBox("Show password");

    private JLabel res = new JLabel("");

    AdminRegisterView(){
        setTitle("Admin Register");
        setBounds(300, 90, 500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        container.setLayout(null);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(130, 50, 300, 30);
        container.add(title);

        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setBounds(100, 150, 100, 20);
        container.add(usernameLabel);

        username.setFont(new Font("Arial", Font.PLAIN, 15));
        username.setBounds(200, 150, 190, 20);
        container.add(username);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(100, 200,100,20);
        container.add(passwordLabel);

        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setBounds(200, 200,190,20);
        container.add(password);

        repeatpasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        repeatpasswordLabel.setBounds(100, 250,100,20);
        container.add(repeatpasswordLabel);

        repeatpassword.setFont(new Font("Arial", Font.PLAIN, 20));
        repeatpassword.setBounds(200, 250,190,20);
        container.add(repeatpassword);

        acept.setFont(new Font("Arial", Font.PLAIN, 15));
        acept.setBounds(150, 300, 250, 20);
        container.add(acept);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150, 400, 100, 20);
        sub.addActionListener(this);
        container.add(sub);

        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setBounds(270, 400, 100, 20);
        reset.addActionListener(this);
        container.add(reset);

        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setBounds(100, 450, 500, 25);
        container.add(res);

        showpassword.setBounds(150,350,150,30);
        container.add(showpassword);
        showpassword.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sub){
            if(acept.isSelected()){
                res.setText("Success :)");
            } else {
                res.setText("Accept the terms and conditions please!");
            }

            String temp = String.valueOf(password.getPassword());
            String temp1 = String.valueOf(repeatpassword.getPassword());
            if(temp.equals("")) {
                res.setText("Type password please!");
            }
            if(temp1.equals("")) {
                res.setText("Confirm password please!");
            }
            if(!temp.equals(temp1)){
                res.setText("Password does not match!");
            }
            else{
                Controller.AdminRegisterController.register(username.getText(), temp);
                this.dispose();
                new LoginView();
            }
        }
        if (e.getSource() == reset){
            res.setText("");
            acept.setSelected(false);
            password.setText("");
            repeatpassword.setText("");
            username.setText("");
        }
        if (e.getSource() == showpassword){
            if (showpassword.isSelected()){
                password.setEchoChar((char)0);
            } else {
                password.setEchoChar('*');
            }
            if (showpassword.isSelected()){
                repeatpassword.setEchoChar((char)0);
            } else {
                repeatpassword.setEchoChar('*');
            }
        }
    }
}
