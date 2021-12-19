package View.Admin;

import Controller.CovidDAO;
import Model.QuarantineModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class RegisterQuarantine extends JPanel implements ActionListener {
    private JLabel title = new JLabel("Quarantine Management");

    private JButton add = new JButton("Add");
    private JButton modify = new JButton("Modify");
    private JButton delete = new JButton("Delete");

    private AddQuarantine aq = new AddQuarantine();
    private ModifyQuarantine mq = new ModifyQuarantine();
    private DeleteQuarantine dq = new DeleteQuarantine();
    public RegisterQuarantine(){
        setBounds(240, 0, 760, 700);
        setOpaque(false);
        setVisible(true);
        setLayout(null);
        setBackground(Color.WHITE);

        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(250, 30, 300, 30);
        add(title);

        add.setFont(new Font("Arial", Font.PLAIN, 15));
        add.setBounds(100, 80, 100, 20);
        add.addActionListener(this);
        add.setToolTipText("Add new quarantine");
        add(add);

        modify.setFont(new Font("Arial", Font.PLAIN, 15));
        modify.setBounds(300, 80, 100, 20);
        modify.addActionListener(this);
        modify.setToolTipText("Modify quarantine");
        add(modify);

        delete.setFont(new Font("Arial", Font.PLAIN, 15));
        delete.setBounds(500, 80, 100, 20);
        delete.addActionListener(this);
        delete.setToolTipText("Delete a specific quarantine");
        add(delete);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add){
            remove(mq);
            remove(dq);
            add(aq);
            repaint();
            revalidate();
        }

        if (e.getSource() == modify){
            remove(aq);
            remove(dq);
            add(mq);
            repaint();
            revalidate();
        }
        if (e.getSource() == delete){
            remove(mq);
            remove(aq);
            add(dq);
            repaint();
            revalidate();
        }
    }
}

class AddQuarantine extends JPanel implements ActionListener {
    private JLabel title = new JLabel("Add Quarantine Form");
    private JLabel qualabel = new JLabel("Quarantine");
    private JTextField quatx = new JTextField();

    private JButton sub = new JButton("Submit");
    private JButton reset = new JButton("Reset");
    private JTextArea result = new JTextArea();
    private JLabel res = new JLabel("");
    private JTextArea addresult = new JTextArea();
    private JLabel prolabel = new JLabel("Province");
    private JLabel dislabel = new JLabel("District");
    private JLabel caplabel = new JLabel("Capacity");
    private JComboBox province = new JComboBox();
    private JComboBox district = new JComboBox();
    private JTextField captx = new JTextField();

    private int id_pro;
    private int id_dic;
    private int id_vil;

    private QuarantineModel qa = new QuarantineModel();
    public AddQuarantine(){
        setBounds(0, 100, 860, 600);
        setOpaque(true);
        setVisible(true);
        setLayout(null);
        setBackground(Color.WHITE);

        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(100, 30, 300, 30);
        add(title);

        qualabel.setFont(new Font("Arial", Font.PLAIN, 20));
        qualabel.setBounds(100, 100, 190, 20);
        add(qualabel);

        quatx.setFont(new Font("Arial", Font.PLAIN, 15));
        quatx.setBounds(200, 100, 190, 20);
        add(quatx);

        prolabel.setFont(new Font("Arial", Font.PLAIN, 20));
        prolabel.setBounds(100, 150, 190, 20);
        add(prolabel);

        province.setFont(new Font("Arial", Font.PLAIN, 15));
        province.setBounds(200, 150, 190, 20);
        province.addActionListener(this);
        add(province);

        dislabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dislabel.setBounds(100, 200, 100, 20);
        add(dislabel);

        district.setFont(new Font("Arial", Font.PLAIN, 15));
        district.setBounds(200, 200, 190, 20);
        district.addActionListener(this);
        add(district);

        caplabel.setFont(new Font("Arial", Font.PLAIN, 20));
        caplabel.setBounds(100, 250, 100, 20);
        add(caplabel);

        captx.setFont(new Font("Arial", Font.PLAIN, 15));
        captx.setBounds(200, 250, 190, 20);
        add(captx);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150, 300, 100, 20);
        sub.addActionListener(this);
        sub.setToolTipText("submit to create new quarantine");
        add(sub);

        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setBounds(270, 300, 100, 20);
        reset.addActionListener(this);
        reset.setToolTipText("Clear all what you've typed");
        add(reset);

        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setBounds(400, 100, 300, 250);
        result.setLineWrap(true);
        result.setEditable(false);
        add(result);

        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setBounds(270, 350, 500, 25);
        add(res);

        try (Connection conn =new CovidDAO().getConnection(); Statement stmt = conn.createStatement()){
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== province){
            String sql = "select * from PROVINCE WHERE NAME=?";
            try (Connection conn = new CovidDAO().getConnection();PreparedStatement prepStmt = conn.prepareStatement(sql);){
                prepStmt.setString(1, (String) province.getSelectedItem());
                ResultSet rs = prepStmt.executeQuery();

                if(rs.next()){
                    id_pro = rs.getInt(1);
                }

            } catch (SQLException ex){
                ex.printStackTrace();
            }

            qa.setProvince(id_pro);

            sql = "select * from DISTRICT WHERE ID_PROVINCE=?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement prepStmt = conn.prepareStatement(sql);){
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
        if(e.getSource() == district){
            String sql = "select * from DISTRICT WHERE ID_PROVINCE=? AND NAME=?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement prepStmt = conn.prepareStatement(sql);){
                prepStmt.setInt(1, id_pro);
                prepStmt.setString(2, (String) district.getSelectedItem());
                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()){
                    id_dic = rs.getInt(1);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }

            qa.setDistrict(id_dic);
        }
        if(e.getSource()==sub){
            String url = "jdbc:sqlserver://localhost:1433;database=QLC19";
            String username = "sa";
            String password = "123456";
            String sql = "INSERT INTO QUARATINE(NAME, ID_PROVINCE, ID_DISTRICT, CAPABLE, IS_DELETED) VALUES(?, ?, ?, ?,?)";
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pre = conn.prepareStatement(sql);){

                pre.setString(1, quatx.getText());
                pre.setInt(2, id_pro);
                pre.setInt(3, id_dic);
                pre.setInt(4, Integer.parseInt(captx.getText()));
                pre.setInt(5, 1);

                int i = pre.executeUpdate();
                if (i > 0){
                    res.setText("Success");
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }

        }
        if (e.getSource()==reset){
            quatx.setText("");
            captx.setText("");
            res.setText("");
        }
    }
}

class ModifyQuarantine extends  JPanel implements ActionListener {
    private JLabel title = new JLabel("Modify Quarantine Form");

    private JLabel qualabel = new JLabel("Quarantine");
    private JComboBox quatx = new JComboBox();

    private JLabel quanamlabel = new JLabel("Name");
    private JTextField quanamtx = new JTextField();

    private JButton sub = new JButton("Submit");
    private JButton reset = new JButton("Reset");
    private JTextArea result = new JTextArea();
    private JLabel res = new JLabel("");

    private JLabel caplabel = new JLabel("Capacity");
    private JTextField captx = new JTextField();

    private JLabel activatelabel = new JLabel("Activate");
    private JComboBox activatetx = new JComboBox(new String[]{"Closed", "Open"});

    private QuarantineModel qua = new QuarantineModel();
    ModifyQuarantine(){
        setBounds(0, 100, 860, 600);
        setOpaque(true);
        setVisible(true);
        setLayout(null);
        setBackground(Color.WHITE);

        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(100, 30, 400, 30);
        add(title);

        JLabel inform1 = new JLabel("----------- Choose Quarantine");
        inform1.setFont(new Font("Arial", Font.PLAIN, 15));
        inform1.setBounds(100, 70, 190, 20);
        add(inform1);

        qualabel.setFont(new Font("Arial", Font.PLAIN, 20));
        qualabel.setBounds(100, 100, 190, 20);
        add(qualabel);

        quatx.setFont(new Font("Arial", Font.PLAIN, 15));
        quatx.setBounds(200, 100, 190, 20);
        quatx.addActionListener(this);
        add(quatx);

        JLabel inform2 = new JLabel("----------- Change Quarantine");
        inform2.setFont(new Font("Arial", Font.PLAIN, 15));
        inform2.setBounds(100, 150, 190, 20);
        add(inform2);

        quanamlabel.setFont(new Font("Arial", Font.PLAIN, 20));
        quanamlabel.setBounds(100, 200, 190, 20);
        add(quanamlabel);

        quanamtx.setFont(new Font("Arial", Font.PLAIN, 15));
        quanamtx.setBounds(200, 200, 190, 20);
        add(quanamtx);

        caplabel.setFont(new Font("Arial", Font.PLAIN, 20));
        caplabel.setBounds(100, 250, 190, 20);
        add(caplabel);

        captx.setFont(new Font("Arial", Font.PLAIN, 15));
        captx.setBounds(200, 250, 190, 20);
        add(captx);

        activatelabel.setFont(new Font("Arial", Font.PLAIN, 20));
        activatelabel.setBounds(100, 300, 190, 20);
        add(activatelabel);

        activatetx.setFont(new Font("Arial", Font.PLAIN, 15));
        activatetx.setBounds(200, 300, 190, 20);
        add(activatetx);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150, 350, 100, 20);
        sub.addActionListener(this);
        sub.setToolTipText("Submit all what you've typed");
        add(sub);

        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setBounds(270, 350, 100, 20);
        reset.addActionListener(this);
        reset.setToolTipText("Clear all what you've typed");
        add(reset);

        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setBounds(400, 100, 300, 250);
        result.setLineWrap(true);
        result.setEditable(false);
        add(result);

        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setBounds(270, 400, 500, 25);
        add(res);

        String url = "jdbc:sqlserver://localhost:1433;database=QLC19";
        String username = "sa";
        String password = "123456";
        String sql = "select * from QUARATINE";
        try (Connection conn = new CovidDAO().getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery(sql);
            quatx.removeAll();
            while(rs.next()){
                String name = rs.getString(2);
                quatx.addItem(name);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == quatx){
            String url = "jdbc:sqlserver://localhost:1433;database=QLC19";
            String username = "sa";
            String password = "123456";
            String sql = "select * from QUARATINE WHERE NAME=?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)){
                pre.setString(1, (String) quatx.getSelectedItem());
                ResultSet rs = pre.executeQuery();

                if (rs.next()){
                    qua.setId(rs.getInt(1));
                    qua.setName(rs.getString(2));
                    qua.setProvince(rs.getInt(3));
                    qua.setDistrict(rs.getInt(4));
                    qua.setCapicity(rs.getInt(5));
                    qua.setDeleted(rs.getInt(6));
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            quanamtx.setText(qua.getName());
            captx.setText(""+qua.getCapicity());
            if (qua.getDeleted() == 0){
                activatetx.setSelectedItem("Closed");
            }
            else if (qua.getDeleted() == 1){
                activatetx.setSelectedItem("Open");
            }
            sql = "SELECT P.NAME, D.NAME" +
                    " FROM PROVINCE P JOIN DISTRICT D ON P.ID_PROVINCE = D.ID_PROVINCE" +
                    " WHERE P.ID_PROVINCE = ? AND D.ID_DISTRICT = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setInt(1, qua.getProvince());
                pre.setInt(2, qua.getDistrict());
                ResultSet rs = pre.executeQuery();
                if (rs.next()){

                    result.setText(qua.toString(rs.getString(1), rs.getString(2)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == sub) {
            if (!quanamtx.getText().isEmpty() && !captx.getText().isEmpty()) {
                String sql = "UPDATE QUARATINE SET NAME=?, CAPABLE=?, IS_DELETED=? WHERE ID_QUARATINE=?";
                try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql);) {
                    pre.setString(1, (String) quatx.getSelectedItem());
                    pre.setInt(2, Integer.parseInt(captx.getText()));
                    if (activatetx.getSelectedItem().equals("Open")){
                        pre.setInt(3, 0);
                    } else if (activatetx.getSelectedItem().equals("Closed")){
                        pre.setInt(3, 1);
                    }

                    pre.setInt(4, qua.getId());

                    pre.execute();
                    res.setText("Success");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == reset){
            quanamtx.setText(qua.getName());
            captx.setText(""+qua.getCapicity());
        }
    }
}

class DeleteQuarantine extends  JPanel implements ActionListener {
    private JLabel title = new JLabel("Delete Quarantine Form");

    private JLabel qualabel = new JLabel("Quarantine");
    private JComboBox quatx = new JComboBox();

    private JButton sub = new JButton("Delete");
    private JTextArea result = new JTextArea();

    private QuarantineModel qua = new QuarantineModel();
    DeleteQuarantine(){
        setBounds(0, 100, 860, 600);
        setOpaque(true);
        setVisible(true);
        setLayout(null);
        setBackground(Color.WHITE);

        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(100, 30, 500, 30);
        add(title);

        JLabel inform1 = new JLabel("----------- Choose Quarantine");
        inform1.setFont(new Font("Arial", Font.PLAIN, 15));
        inform1.setBounds(100, 70, 190, 20);
        add(inform1);

        qualabel.setFont(new Font("Arial", Font.PLAIN, 20));
        qualabel.setBounds(100, 100, 190, 20);
        add(qualabel);

        quatx.setFont(new Font("Arial", Font.PLAIN, 15));
        quatx.setBounds(200, 100, 190, 20);
        quatx.addActionListener(this);
        add(quatx);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150, 350, 100, 20);
        sub.addActionListener(this);
        sub.setToolTipText("Delete this quarantine");
        add(sub);

        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setBounds(400, 100, 300, 250);
        result.setLineWrap(true);
        result.setEditable(false);
        add(result);

        String sql = "select * from QUARATINE WHERE IS_DELETED=0";
        try (Connection conn = new CovidDAO().getConnection(); Statement stm = conn.createStatement()){
            ResultSet rs = stm.executeQuery(sql);
            quatx.removeAll();
            while(rs.next()){
                String name = rs.getString(2);
                quatx.addItem(name);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == quatx){
            String sql = "select * from QUARATINE WHERE NAME=?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)){
                pre.setString(1, (String) quatx.getSelectedItem());
                ResultSet rs = pre.executeQuery();

                if (rs.next()){
                    qua.setId(rs.getInt(1));
                    qua.setName(rs.getString(2));
                    qua.setProvince(rs.getInt(3));
                    qua.setDistrict(rs.getInt(4));
                    qua.setCapicity(rs.getInt(5));
                    qua.setDeleted(rs.getInt(6));
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }

            sql = "SELECT P.NAME, D.NAME" +
                    " FROM PROVINCE P JOIN DISTRICT D ON P.ID_PROVINCE = D.ID_PROVINCE" +
                    " WHERE P.ID_PROVINCE = ? AND D.ID_DISTRICT = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setInt(1, qua.getProvince());
                pre.setInt(2, qua.getDistrict());
                ResultSet rs = pre.executeQuery();
                if (rs.next()){
                    result.setText(qua.toString(rs.getString(1), rs.getString(2)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == sub) {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to delete", "Just to make sure", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String sql = "UPDATE QUARATINE SET IS_DELETED=0 WHERE ID_QUARATINE=?";
                try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                    pre.setInt(1, qua.getId());

                    pre.execute();
                    JOptionPane.showMessageDialog(null, "Success");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
