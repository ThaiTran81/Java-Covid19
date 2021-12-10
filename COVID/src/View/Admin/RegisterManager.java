package View.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterManager extends JPanel implements ActionListener {
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
    private JLabel doblabel = new JLabel("DoB");
    private JTextField dob = new JTextField();

    private int id_pro;
    private int id_dic;
    private int id_vil;

    public RegisterManager(){
        setBounds(240, 0, 760, 700);
        setOpaque(false);
        setLayout(null);
        setBackground(Color.WHITE);

        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(250, 30, 300, 30);
        add(title);

        fullnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        fullnameLabel.setBounds(50, 100, 100, 20);
        add(fullnameLabel);

        fullname.setFont(new Font("Arial", Font.PLAIN, 15));
        fullname.setBounds(150, 100, 190, 20);
        add(fullname);

        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setBounds(50, 150, 100, 20);
        add(usernameLabel);

        username.setFont(new Font("Arial", Font.PLAIN, 15));
        username.setBounds(150, 150, 190, 20);
        add(username);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 200,100,20);
        add(passwordLabel);

        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setBounds(150, 200,190,20);
        add(password);

        repeatpasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        repeatpasswordLabel.setBounds(50, 250,100,20);
        add(repeatpasswordLabel);

        repeatpassword.setFont(new Font("Arial", Font.PLAIN, 20));
        repeatpassword.setBounds(150, 250,190,20);
        add(repeatpassword);

        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneLabel.setBounds(50, 300,100,20);
        add(phoneLabel);

        phone.setFont(new Font("Arial", Font.PLAIN, 20));
        phone.setBounds(150, 300,190,20);
        add(phone);

        doblabel.setFont(new Font("Arial", Font.PLAIN, 20));
        doblabel.setBounds(50, 350,100,20);
        add(doblabel);

        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setBounds(150, 350,190,20);
        add(dob);

        genderLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        genderLabel.setBounds(50, 400, 100,20);
        add(genderLabel);

        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setBounds(150, 400, 75,20);
        add(male);

        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setBounds(225, 400, 80,20);
        add(female);

        gendergroup.add(male);
        gendergroup.add(female);

        acept.setFont(new Font("Arial", Font.PLAIN, 15));
        acept.setBounds(100, 600, 250, 20);
        add(acept);

        prolabel.setFont(new Font("Arial", Font.PLAIN, 20));
        prolabel.setBounds(50, 450, 190, 20);
        add(prolabel);

        province.setFont(new Font("Arial", Font.PLAIN, 15));
        province.setBounds(150, 450, 190, 20);
        province.addActionListener(this);
        add(province);

        dislabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dislabel.setBounds(50, 500, 100, 20);
        add(dislabel);

        district.setFont(new Font("Arial", Font.PLAIN, 15));
        district.setBounds(150, 500, 190, 20);
        district.addActionListener(this);
        add(district);

        villabel.setFont(new Font("Arial", Font.PLAIN, 20));
        villabel.setBounds(50, 550, 100, 20);
        add(villabel);

        village.setFont(new Font("Arial", Font.PLAIN, 15));
        village.setBounds(150, 550, 190, 20);
        village.addActionListener(this);
        add(village);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(400, 600, 100, 20);
        sub.addActionListener(this);
        add(sub);

        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setBounds(550, 600, 100, 20);
        reset.addActionListener(this);
        add(reset);

        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setBounds(400, 100, 300, 400);
        result.setLineWrap(true);
        result.setEditable(false);
        add(result);

        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setBounds(450, 550, 500, 25);
        add(res);

        String url = "jdbc:sqlserver://localhost:1433;database=QLC19";
        String username = "sa";
        String password = "123456";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement()){
            String sql = "select * from PROVINCE";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString(2);
                province.addItem(name);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sub){
            boolean check = true;
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
                data += "DoB: " + dob.getText() + "\n";
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
                check = false;
            }
            if(username.getText().equals("")){
                result.setText("");
                addresult.setText("");
                res.setText("Type username please!");
                check = false;
            }
            if(fullname.getText().equals("")){
                result.setText("");
                addresult.setText("");
                res.setText("Type full name please!");
                check = false;
            }
            String temp = String.valueOf(password.getPassword());
            String temp1 = String.valueOf(repeatpassword.getPassword());
            if(temp.equals("")) {
                result.setText("");
                addresult.setText("");
                res.setText("Type password please!");
                check = false;
            }
            if(temp1.equals("")) {
                result.setText("");
                addresult.setText("");
                res.setText("Confirm password please!");
                check = false;
            }
            if(phone.getText().equals("")) {
                result.setText("");
                addresult.setText("");
                res.setText("Type phone number please!");
                check = false;
            }
            if (!temp1.equals(temp)){
                result.setText("");
                addresult.setText("");
                res.setText("Password does not match!");
                check = false;
            }
            if (check ==true){
                String sql = "INSERT INTO PROFILE(ID, FULLNAME, PHONENUMBER, DOB, GENDER, PROVINE, DISTRICT, VILLAGE) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                try (Connection conn = Controller.ConnectToDBController.getSqlConnection();PreparedStatement prepStmt = conn.prepareStatement(sql);){
                    prepStmt.setString(1, this.username.getText());
                    prepStmt.setString(2, fullname.getText());
                    prepStmt.setString(3, phone.getText());
                    prepStmt.setDate(4, Date.valueOf(dob.getText()));
                    if (male.isSelected())
                        prepStmt.setString(5, "Nam");
                    else
                        prepStmt.setString(5, "Nữ");
                    prepStmt.setString(6, (String) province.getSelectedItem());
                    prepStmt.setString(7, (String) district.getSelectedItem());
                    prepStmt.setString(8, (String) village.getSelectedItem());
                    prepStmt.execute();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

                sql = "INSERT INTO ACCOUNT(USERNAME, PASSWORD, TYPE, BLOCK) VALUES(?,?,?,?)";
                try (Connection conn = Controller.ConnectToDBController.getSqlConnection();PreparedStatement prepStmt = conn.prepareStatement(sql);) {
                    prepStmt.setString(1, this.username.getText());
                    prepStmt.setString(2, temp);
                    prepStmt.setInt(3, 1);
                    prepStmt.setInt(4, 0);

                    int i = prepStmt.executeUpdate();
                    if (i > 0){
                        res.setText("Success");
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
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
            String sql = "select * from PROVINCE WHERE NAME=?";
            try (Connection conn = Controller.ConnectToDBController.getSqlConnection();PreparedStatement prepStmt = conn.prepareStatement(sql);){
                prepStmt.setString(1, (String) province.getSelectedItem());
                ResultSet rs = prepStmt.executeQuery();

                if(rs.next()){
                    id_pro = rs.getInt(1);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            sql = "select * from DISTRICT WHERE ID_PROVINCE=?";
            try (Connection conn = Controller.ConnectToDBController.getSqlConnection(); PreparedStatement prepStmt = conn.prepareStatement(sql);){
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
            String sql = "select * from DISTRICT WHERE ID_PROVINCE=? AND NAME=?";
            try (Connection conn = Controller.ConnectToDBController.getSqlConnection();PreparedStatement prepStmt = conn.prepareStatement(sql);){
                prepStmt.setInt(1, id_pro);
                prepStmt.setString(2, (String) district.getSelectedItem());
                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()){
                    id_dic = rs.getInt(1);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            sql = "select * from VILLAGE WHERE ID_PROVINCE=? AND ID_DISTRICT=?";
            try (Connection conn = Controller.ConnectToDBController.getSqlConnection();PreparedStatement prepStmt = conn.prepareStatement(sql);){
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
