package View;

import Controller.*;
import Model.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

public class ConnectToDBView extends JFrame implements ActionListener{
    private Container c;
    private JLabel title;
    private JLabel server;
    private JTextField tserver;
    private JLabel db;
    private JTextField tdb;
    private JLabel usr;
    private JTextField tusr;
    private JLabel pwd;
    private JTextField tpwd;
    private JLabel port;
    private JTextField tport;
    private JCheckBox term;
    private JButton login;
    private JButton reset;
    private JTextArea monitor;
    public ConnectToDBView() {
        this.setTitle("Connect to database");
        this.setBounds(300, 90, 900, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        c = this.getContentPane();
        c.setLayout(null);

        title = new JLabel("Connect to database");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        server = new JLabel("server");
        server.setFont(new Font("Arial", Font.PLAIN, 20));
        server.setSize(100, 20);
        server.setLocation(100, 100);
        c.add(server);

        tserver = new JTextField("localhost");
        tserver.setFont(new Font("Arial", Font.PLAIN, 15));
        tserver.setSize(250, 30);
        tserver.setLocation(200, 100);
        c.add(tserver);

        db = new JLabel("Name db");
        db.setFont(new Font("Arial", Font.PLAIN, 20));
        db.setSize(100, 20);
        db.setLocation(100, 150);
        c.add(db);

        tdb = new JTextField("QLC19");
        tdb.setFont(new Font("Arial", Font.PLAIN, 15));
        tdb.setSize(150, 30);
        tdb.setLocation(200, 150);
        c.add(tdb);

        usr = new JLabel("username");
        usr.setFont(new Font("Arial", Font.PLAIN, 20));
        usr.setSize(100, 20);
        usr.setLocation(100, 200);
        c.add(usr);

        tusr = new JTextField("sa");
        tusr.setFont(new Font("Arial", Font.PLAIN, 15));
        tusr.setSize(150, 30);
        tusr.setLocation(200, 200);
        c.add(tusr);

        pwd = new JLabel("password");
        pwd.setFont(new Font("Arial", Font.PLAIN, 20));
        pwd.setSize(100, 20);
        pwd.setLocation(100, 250);
        c.add(pwd);

        tpwd = new JTextField("123456");
        tpwd.setFont(new Font("Arial", Font.PLAIN, 15));
        tpwd.setSize(150, 30);
        tpwd.setLocation(200, 250);
        c.add(tpwd);

        port = new JLabel("Port");
        port.setFont(new Font("Arial", Font.PLAIN, 20));
        port.setSize(100, 20);
        port.setLocation(100, 300);
        c.add(port);

        tport = new JTextField("1433");
        tport.setFont(new Font("Arial", Font.PLAIN, 15));
        tport.setSize(150, 30);
        tport.setLocation(200, 300);
        c.add(tport);

        login = new JButton("Connect");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(150, 350);
        login.addActionListener(this);
        c.add(login);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 350);
        reset.addActionListener(this);
        c.add(reset);

        monitor = new JTextArea();
        monitor.setFont(new Font("Arial", Font.PLAIN, 15));
        monitor.setSize(300, 400);
        monitor.setLocation(500, 100);
        monitor.setLineWrap(true);
        monitor.setEditable(false);
        c.add(monitor);

        this.setVisible(true);
        // connect server
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.login){
            try {
                String server =this.tserver.getText();
                String data = this.tdb.getText();
                String user = this.tusr.getText();
                String pass = this.tpwd.getText();
                String prt = this.tport.getText();


                ConnectToDBController.getSqlConnection(server, data, user, pass, Integer.parseInt(prt));
                this.monitor.setText("Connect successfully!!!!!");
                this.dispose();

                //This need to be rewritten
                boolean check = false;
                try {
                    check = LoginController.checkManagerExist();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (check == true){
                    //Page transition here
                    new LoginView();
                } else {
                    //Create account for admin
                    new AdminRegisterView();
                }
            } catch (SQLException | NumberFormatException ex) {
                this.monitor.setText("Connection failed!!! try again");
            }
        }
        else if(e.getSource()== this.reset){
            this.tserver.setText("");
            this.tdb.setText("");
            this.tusr.setText("");
            this.tpwd.setText("");
            this.tport.setText("");
        }
    }
}