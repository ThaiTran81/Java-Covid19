package View;

import Controller.CovidDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddPatientView extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JLabel title = new JLabel("Registration Form");
    private JLabel fullnameLabel = new JLabel("Full name");
    private JTextField fullname = new JTextField();
    private JLabel usernameLabel = new JLabel("Username");
    private JTextField username = new JTextField();
    private JLabel passwordLabel = new JLabel("Password");
    private JPasswordField password = new JPasswordField();
    private JLabel repeatpasswordLabel = new JLabel("Confirm");
    private JPasswordField repeatpassword = new JPasswordField();
    private JLabel phoneLabel = new JLabel("Phone");
    private JTextField phone = new JTextField();
    private JLabel genderLabel = new JLabel("Gender");
    private JRadioButton male = new JRadioButton("Male");
    private JRadioButton female = new JRadioButton("Female");
    private ButtonGroup gendergroup = new ButtonGroup();
    private JCheckBox acept = new JCheckBox("Accept Terms and Conditions");
    private JButton sub = new JButton("Submit");
    private JButton reset = new JButton("Reset");
    private JTextArea result = new JTextArea();
    private JLabel res = new JLabel("");
    private JTextArea addresult = new JTextArea();
    private JLabel prolabel = new JLabel("Province");
    private JLabel dislabel = new JLabel("District");
    private JLabel villabel = new JLabel("Village");
    private JComboBox province = new JComboBox();
    private JComboBox district = new JComboBox();
    private JComboBox village = new JComboBox();

    private JLabel qualabel = new JLabel("Quarantine");
    private JTextField quatext = new JTextField();
    private JLabel banklabel = new JLabel("BANK ID");
    private JTextField banktx = new JTextField();
//    private JLabel

    private int id_pro;
    private int id_dic;
    private int id_vil;
    public AddPatientView(){
        setTitle("Add Patient View");
        setBounds(300, 90, 900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        container.setLayout(null);
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(300, 30, 300, 30);
        container.add(title);

        fullnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        fullnameLabel.setBounds(100, 100, 100, 20);
        container.add(fullnameLabel);

        fullname.setFont(new Font("Arial", Font.PLAIN, 15));
        fullname.setBounds(200, 100, 190, 20);
        container.add(fullname);

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

        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneLabel.setBounds(100, 300,100,20);
        container.add(phoneLabel);

        phone.setFont(new Font("Arial", Font.PLAIN, 20));
        phone.setBounds(200, 300,190,20);
        container.add(phone);

        genderLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        genderLabel.setBounds(100, 350, 100,20);
        container.add(genderLabel);

        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setBounds(200, 350, 75,20);
        container.add(male);

        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setBounds(275, 350, 80,20);
        container.add(female);

        gendergroup.add(male);
        gendergroup.add(female);

        acept.setFont(new Font("Arial", Font.PLAIN, 15));
        acept.setBounds(150, 550, 250, 20);
        container.add(acept);

        prolabel.setFont(new Font("Arial", Font.PLAIN, 20));
        prolabel.setBounds(100, 400, 190, 20);
        container.add(prolabel);

        province.setFont(new Font("Arial", Font.PLAIN, 15));
        province.setBounds(200, 400, 190, 20);
        province.addActionListener(this);
        container.add(province);

        dislabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dislabel.setBounds(100, 450, 100, 20);
        container.add(dislabel);

        district.setFont(new Font("Arial", Font.PLAIN, 15));
        district.setBounds(200, 450, 190, 20);
        district.addActionListener(this);
        container.add(district);

        villabel.setFont(new Font("Arial", Font.PLAIN, 20));
        villabel.setBounds(100, 500, 100, 20);
        container.add(villabel);

        village.setFont(new Font("Arial", Font.PLAIN, 15));
        village.setBounds(200, 500, 190, 20);
        village.addActionListener(this);
        container.add(village);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150, 600, 100, 20);
        sub.addActionListener(this);
        container.add(sub);

        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setBounds(270, 600, 100, 20);
        reset.addActionListener(this);
        container.add(reset);

        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setBounds(500, 100, 300, 400);
        result.setLineWrap(true);
        result.setEditable(false);
        container.add(result);

        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setBounds(500, 550, 500, 25);
        container.add(res);

        try (Connection conn = new CovidDAO().getConnection(); Statement stmt = conn.createStatement()){
            String sql = "select * from PROVINCE";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString(2);
                province.addItem(name);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sub){
            if(acept.isSelected()){
                String data
                        = "Name : "
                        + fullname.getText() + "\n"
                        + "Mobile : "
                        + phone.getText() + "\n";
                if (male.isSelected())
                    data += "Gender: " + male.getText() + "\n";
                else
                    data += "Gender: "+ male.getText() +"\n";

                data += "Village: " + village.getSelectedItem() + "\n";
                data += "District: " + district.getSelectedItem() + "\n";
                data += "Province: " + province.getSelectedItem() + "\n";

                result.setText(data);
                result.setEditable(false);
                res.setText("Success :)");
            } else {
                result.setText("");
                addresult.setText("");
                res.setText("Accept the terms and conditions please!");
            }
            if(username.getText().equals("")){
                result.setText("");
                addresult.setText("");
                res.setText("Type username please!");
            }
            if(fullname.getText().equals("")){
                result.setText("");
                addresult.setText("");
                res.setText("Type full name please!");
            }
            String temp = String.valueOf(password.getPassword());
            String temp1 = String.valueOf(repeatpassword.getPassword());
            if(temp.equals("")) {
                result.setText("");
                addresult.setText("");
                res.setText("Type password please!");
            }
            if(temp1.equals("")) {
                result.setText("");
                addresult.setText("");
                res.setText("Confirm password please!");
            }
            if(phone.getText().equals("")) {
                result.setText("");
                addresult.setText("");
                res.setText("Type phone number please!");
            }
            if (!temp1.equals(temp)){
                result.setText("");
                addresult.setText("");
                res.setText("Password does not match!");
            }

        }
        if (e.getSource() == reset){
            fullname.setText("");
            phone.setText("");
            res.setText("");
            addresult.setText("");
            acept.setSelected(false);
            password.setText("");
            repeatpassword.setText("");
            username.setText("");
        }
        if (e.getSource() == province){
            try (Connection conn = new CovidDAO().getConnection()){
                String sql = "select * from PROVINCE WHERE NAME=?";
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                prepStmt.setString(1, (String) province.getSelectedItem());
                ResultSet rs = prepStmt.executeQuery();

                if(rs.next()){
                    id_pro = rs.getInt(1);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }

            try (Connection conn = new CovidDAO().getConnection()){
                String sql = "select * from DISTRICT WHERE ID_PROVINCE=?";
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                prepStmt.setInt(1, id_pro);
                ResultSet rs = prepStmt.executeQuery();
                district.removeAllItems();

                while(rs.next()){
                    String dis = rs.getString(3);
                    district.addItem(dis);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        if (e.getSource() == district){
            try (Connection conn = new CovidDAO().getConnection()){
                String sql = "select * from DISTRICT WHERE ID_PROVINCE=? AND NAME=?";
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                prepStmt.setInt(1, id_pro);
                prepStmt.setString(2, (String) district.getSelectedItem());
                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()){
                    id_dic = rs.getInt(1);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }

            try (Connection conn = new CovidDAO().getConnection()){
                String sql = "select * from VILLAGE WHERE ID_PROVINCE=? AND ID_DISTRICT=?";
                PreparedStatement prepStmt = conn.prepareStatement(sql);
                prepStmt.setInt(1, id_pro);
                prepStmt.setInt(2, id_dic);
                ResultSet rs = prepStmt.executeQuery();
                village.removeAllItems();
                while(rs.next()){
                    String vil = rs.getString(4);
                    village.addItem(vil);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
